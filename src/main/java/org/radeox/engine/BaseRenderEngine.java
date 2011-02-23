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


package org.radeox.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.radeox.api.engine.RenderEngine;
import org.radeox.engine.context.BaseInitialRenderContext;
import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.filter.Filter;
import org.radeox.filter.FilterPipe;
import org.radeox.filter.context.BaseFilterContext;
import org.radeox.filter.context.FilterContext;
import org.radeox.util.Service;

import java.io.*;
import java.util.Iterator;

/**
 * Base implementation of RenderEngine
 *
 * @author Stephan J. Schmidt
 * @version $Id: BaseRenderEngine.java,v 1.18 2004/05/26 08:56:20 stephan Exp $
 */

public class BaseRenderEngine implements RenderEngine {
  public static final String NAME = "radeox";

  private static Log log = LogFactory.getLog(BaseRenderEngine.class);

  protected InitialRenderContext initialContext;
  protected FilterPipe fp;

  public BaseRenderEngine(InitialRenderContext context) {
    this.initialContext = context;
    init();
    initialContext.setFilterPipe(fp);
  }

  public BaseRenderEngine() {
    this(new BaseInitialRenderContext());
  }

  public InitialRenderContext getInitialRenderContext() {
    return initialContext;
  }

  protected void init() {
    if (null == fp) {
      fp = new FilterPipe(initialContext);

      Iterator iterator = Service.providers(Filter.class);
      while (iterator.hasNext()) {
        try {
          Filter filter = (Filter) iterator.next();
          fp.addFilter(filter);
          log.debug("Loaded filter: " + filter.getClass().getName());
        } catch (Exception e) {
          log.warn("BaseRenderEngine: unable to load filter", e);
        }
      }

      fp.init();
      //Logger.debug("FilterPipe = "+fp.toString());
    }
  }

  /**
   * Name of the RenderEngine. This is used to get a RenderEngine instance
   * with EngineManager.getInstance(name);
   *
   * @return name Name of the engine
   */
  public String getName() {
    return NAME;
  }

  /**
   * Render an input with text markup and return a String with
   * e.g. HTML
   *
   * @param content String with the input to render
   * @param context Special context for the filter engine, e.g. with
   *                configuration information
   * @return result Output with rendered content
   */
  public String render(String content, RenderContext context) {
    init();
    FilterContext filterContext = new BaseFilterContext();
    filterContext.setRenderContext(context);
    return fp.filter(content, filterContext);
  }

  /**
   * Render an input with text markup from a Reader and write the result to a writer
   *
   * @param in Reader to read the input from
   * @param context Special context for the render engine, e.g. with
   *                configuration information
   */
  public String render(Reader in, RenderContext context) throws IOException {
    StringBuffer buffer = new StringBuffer();
    BufferedReader inputReader = new BufferedReader(in);
    String line;
    while ((line = inputReader.readLine()) != null) {
        buffer.append(line);
    }
    return render(buffer.toString(), context);
 }

  public void render(Writer out, String content, RenderContext context) throws IOException {
    out.write(render(content, context));
  }
}
