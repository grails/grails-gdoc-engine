/*
 *      Copyright 2001-2004 Fraunhofer Gesellschaft, Munich, Germany, for its 
 *      Fraunhofer Institute Computer Architecture and Software Technology
 *      (FIRST), Berlin, Germany
 *      
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package org.radeox.macro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.radeox.filter.context.BaseFilterContext;
import org.radeox.filter.context.FilterContext;
import org.radeox.macro.code.SourceCodeFormatter;
import org.radeox.macro.parameter.MacroParameter;
import org.radeox.util.Service;
import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

/*
 * Macro for displaying programming language source code. CodeMacro knows about
 * different source code formatters which can be plugged into radeox to
 * display more languages. CodeMacro displays Java, Ruby or SQL code.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: CodeMacro.java,v 1.16 2004/04/27 19:30:38 leo Exp $
 */

public class CodeMacro extends LocalePreserved {
  private static Log log = LogFactory.getLog(CodeMacro.class);

  private Map formatters;
  private FilterContext nullContext = new BaseFilterContext();

  private String start;
  private String end;

  public String getLocaleKey() {
    return "macro.code";
  }

  public void setInitialContext(InitialRenderContext context) {
    super.setInitialContext(context);
    Locale outputLocale = (Locale) context.get(RenderContext.OUTPUT_LOCALE);
    String outputName = (String) context.get(RenderContext.OUTPUT_BUNDLE_NAME);
    ResourceBundle outputMessages = ResourceBundle.getBundle(outputName, outputLocale);

    start = outputMessages.getString(getLocaleKey() + ".start");
    end = outputMessages.getString(getLocaleKey() + ".end");
  }

  public CodeMacro() {
    formatters = new HashMap();

    Iterator formatterIt = Service.providers(SourceCodeFormatter.class);
    while (formatterIt.hasNext()) {
      try {
        SourceCodeFormatter formatter = (SourceCodeFormatter) formatterIt.next();
        String name = formatter.getName();
        if (formatters.containsKey(name)) {
          SourceCodeFormatter existing = (SourceCodeFormatter) formatters.get(name);
          if (existing.getPriority() < formatter.getPriority()) {
            formatters.put(name, formatter);
            log.debug("Replacing formatter: " + formatter.getClass() + " (" + name + ")");
          }
        } else {
          formatters.put(name, formatter);
          log.debug("Loaded formatter: " + formatter.getClass() + " (" + name +")");
        }
      } catch (Exception e) {
        log.warn("CodeMacro: unable to load code formatter", e);
      }
    }

    addSpecial('[');
    addSpecial(']');
    addSpecial('{');
    addSpecial('}');
    addSpecial('*');
    addSpecial('-');
    addSpecial('#');
    addSpecial('\\');
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {

    SourceCodeFormatter formatter = null;

    if (params.getLength() == 0 || !formatters.containsKey(params.get("0"))) {
      formatter = (SourceCodeFormatter) formatters.get(initialContext.get(RenderContext.DEFAULT_FORMATTER));
      if (null == formatter) {
        log.warn("Formatter not found.");
        formatter = (SourceCodeFormatter) formatters.get("java");
      }
    } else {
      formatter = (SourceCodeFormatter) formatters.get(params.get("0"));
    }

    String result = formatter.filter(params.getContent(), nullContext);

    writer.write(start);
    writer.write(replace(result.trim()));
    writer.write(end);
    return;
  }
}
