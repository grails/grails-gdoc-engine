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

import org.radeox.macro.parameter.MacroParameter;
import org.radeox.util.i18n.ResourceManager;
import org.radeox.api.engine.context.RenderContext;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * MacroListMacro displays a list of all known macros of the EngineManager
 * with their name, parameters and a description.
 *
 * @author Matthias L. Jugel
 * @version $Id: MacroListMacro.java,v 1.11 2004/05/11 12:17:21 leo Exp $
 */

public class MacroListMacro extends BaseLocaleMacro {
  public String getLocaleKey() {
    return "macro.macrolist";
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {
    if (params.getLength() == 0) {
      appendTo(writer, (String)initialContext.get(RenderContext.LANGUAGE_BUNDLE_NAME));
    } else {
      throw new IllegalArgumentException("MacroListMacro: number of arguments does not match");
    }
  }

  public Writer appendTo(Writer writer, String bundleName) throws IOException {
    List macroList = MacroRepository.getInstance().getPlugins();
    Collections.sort(macroList);
    Iterator iterator = macroList.iterator();
    writer.write("<table class=\"wiki-table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
    writer.write("<tr><th>");
    writer.write(ResourceManager.getString(bundleName, getLocaleKey()+".title.macro"));
    writer.write("</th>");
    writer.write("<th>");
    writer.write(ResourceManager.getString(bundleName, getLocaleKey() + ".title.parameters"));
    writer.write("</th>");
    writer.write("<th>");
    writer.write(ResourceManager.getString(bundleName, getLocaleKey() + ".title.description"));
    writer.write("</th></tr>\n");
    while (iterator.hasNext()) {
      writer.write("<tr>");
      Macro macro = (Macro) iterator.next();
      writer.write("<td>");
      writer.write(macro.getName());
      writer.write("</td><td>");
      String[] params = macro.getParamDescription();
      if (params.length == 0) {
        writer.write("<i>");
        writer.write(ResourceManager.getString(bundleName, getLocaleKey()+".noparams"));
        writer.write("</i>");
      } else {
        for (int i = 0; i < params.length; i++) {
          String parameter = params[i].trim();
          // display missing resources in RED
          if(parameter.startsWith("???") && parameter.endsWith("???")) {
            writer.write("<div class=\"error\">");
            writer.write(parameter);
            writer.write("</div>");
          } else if (parameter.startsWith("?")) {
            writer.write(parameter.substring(1));
            writer.write(" <i>");
            writer.write(ResourceManager.getString(bundleName, getLocaleKey()+".optional"));
            writer.write("</i>");
          } else {
            writer.write(params[i]);
          }
          writer.write("<br/>");
        }
      }
      writer.write("</td><td>");
      writer.write(macro.getDescription());
      writer.write("</td>\n");
    }
    writer.write("</table>");
    return writer;
  }
}
