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

package org.radeox.example;

import org.radeox.api.engine.context.RenderContext;
import org.radeox.api.engine.RenderEngine;
import org.radeox.engine.context.BaseRenderContext;
import org.radeox.engine.BaseRenderEngine;

/**
 * An xslt extension function to render snip content using radeox.
 *
 * Example usage:
 * <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 * xmlns:radeox="http://snipsnap.org/org.radeox.example.XSLTExtension">
 * É
 * <xsl:template match="content">
 *   <content><xsl:value-of select="radeox:render(.)" disable-output-escaping="yes"/></content>
 * </xsl:template>
 * É
 * </xsl:stylesheet>
 * @author Micah Dubinko
 * @version $Id: XSLTExtension.java,v 1.3 2004/05/26 08:56:20 stephan Exp $
 */
public class XSLTExtension {
  public XSLTExtension() {
    // needed for recognition as extension class?
  }

  public static String render(String arg) {
    // for maximum robustness don't put any text in that isn't there
    if (arg == null) {
      arg = "";
    }

    RenderContext context = new BaseRenderContext();
    RenderEngine engine = new BaseRenderEngine();
    return (engine.render(arg, context));
  }
}
