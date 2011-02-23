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

import org.radeox.api.engine.ImageRenderEngine;
import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.macro.parameter.MacroParameter;
import org.radeox.util.Encoder;

import java.io.IOException;
import java.io.Writer;

/*
 * Macro for displaying external links with a name. The normal UrlFilter
 * takes the url as a name.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: LinkMacro.java,v 1.17 2004/05/03 11:12:37 stephan Exp $
 */

public class LinkMacro extends BaseLocaleMacro {
  public String getLocaleKey() {
    return "macro.link";
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {

    RenderContext context = params.getContext();
    RenderEngine engine = context.getRenderEngine();

    String text = params.get("text", 0);
    String url = params.get("url", 1);
    String img = params.get("img", 2);

    // check for single url argument (text == url)
    if(params.getLength() == 1) {
      url = text;
      text = Encoder.toEntity(text.charAt(0)) + Encoder.escape(text.substring(1));
    }

    if (url != null && text != null) {
      writer.write("<span class=\"nobr\">");
      if (!"none".equals(img) && engine instanceof ImageRenderEngine) {
        writer.write(((ImageRenderEngine) engine).getExternalImageLink());
      }
      writer.write("<a href=\"");
      writer.write(url);
      writer.write("\">");
      writer.write(text);
      writer.write("</a></span>");
    } else {
      throw new IllegalArgumentException("link needs a name and a url as argument: txt="+text+" url="+url);
    }
    return;
  }
}
