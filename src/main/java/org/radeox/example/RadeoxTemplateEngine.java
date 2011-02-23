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

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import groovy.text.TemplateEngine;
import org.codehaus.groovy.control.CompilationFailedException;
import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.engine.context.BaseRenderContext;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


/**
 * Groovy Template Engine which uses Radeox to render text markup
 *
 * @author Stephan J. Schmidt
 * @version $Id: RadeoxTemplateEngine.java,v 1.1 2004/04/14 13:03:49 stephan Exp $
 */

public class RadeoxTemplateEngine extends TemplateEngine {
   public Template createTemplate(Reader reader) throws CompilationFailedException, ClassNotFoundException, IOException {
       RenderContext context = new BaseRenderContext();
       RenderEngine engine = new BaseRenderEngine();
       String renderedText = engine.render(reader , context);

       TemplateEngine templateEngine = new SimpleTemplateEngine();
       return templateEngine.createTemplate(new StringReader(renderedText));
   }
}
