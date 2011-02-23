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
import org.radeox.macro.parameter.MacroParameter;
import org.radeox.util.i18n.ResourceManager;
import org.radeox.api.engine.context.RenderContext;

import java.io.IOException;
import java.io.Writer;

/*
 * Macro to display quotations from other sources. The
 * output is wrapped usually in <blockquote> to look like
 * a quotation.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: QuoteMacro.java,v 1.11 2004/06/08 07:54:36 leo Exp $
 */

public class QuoteMacro extends LocalePreserved {
  private static Log log = LogFactory.getLog(QuoteMacro.class);

  public String getLocaleKey() {
    return "macro.quote";
  }

  public void execute(Writer writer, MacroParameter params)
    throws IllegalArgumentException, IOException {

    writer.write("<blockquote class=\"quote\">");
    writer.write(params.getContent());
    String sourceDesc;
    try {
      sourceDesc = ResourceManager.getString((String) initialContext.get(RenderContext.LANGUAGE_BUNDLE_NAME),
                                         getLocaleKey() + ".source");
    } catch (Exception e) {
      log.warn("missing value for " + getLocaleKey() + ".source");
      sourceDesc = "Source";
    }

    if (params.getLength() > 0) {
      String source = params.get(0);
      boolean isLink = source.toLowerCase().startsWith("http://");  
        
      if (params.getLength() == 2) {
        sourceDesc = params.get(1);
      }
        
      if (isLink) {
        writer.write("<br><a href=\""+source+"\">");
        writer.write(sourceDesc);
        writer.write("</a>");
      } else {
        writer.write("<br><b>"+source+"</b>");
      }
    }
    writer.write("</blockquote>");
    return;
  }
}
