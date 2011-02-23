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

import org.picocontainer.PicoContainer;
import org.picocontainer.defaults.DefaultPicoContainer;
import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.engine.context.BaseInitialRenderContext;
import org.radeox.engine.context.BaseRenderContext;

import java.util.Locale;

/*
 * Example how to use BaseRenderEngine with Pico
 *
 * @author Stephan J. Schmidt
 * @version $Id: PicoExample.java,v 1.3 2003/12/16 10:26:51 leo Exp $
 */

public class PicoExample {
  public static void main(String[] args) {
    String test = "==SnipSnap== {link:Radeox|http://radeox.org}";

    DefaultPicoContainer c = new org.picocontainer.defaults.DefaultPicoContainer();
    try {
      InitialRenderContext initialContext = new BaseInitialRenderContext();
      initialContext.set(RenderContext.INPUT_LOCALE, new Locale("otherwiki", ""));
      c.registerComponentInstance(InitialRenderContext.class, initialContext);
      c.registerComponentImplementation(RenderEngine.class, BaseRenderEngine.class);
      c.getComponentInstances();
    } catch (Exception e) {
      System.err.println("Could not register component: "+e);
    }

    PicoContainer container = c;

    // no only work with container

    // Only ask for RenderEngine, we automatically get an object
    // that implements RenderEngine
    RenderEngine engine = (RenderEngine) container.getComponentInstance(RenderEngine.class);
    RenderContext context = new BaseRenderContext();
    System.out.println(engine.render(test, context));
   }
}
