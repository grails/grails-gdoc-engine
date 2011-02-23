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


package org.radeox.engine.context;

import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.context.RenderContext;

import java.util.HashMap;
import java.util.Map;


/**
 * Base impementation for RenderContext
 *
 * @author Stephan J. Schmidt
 * @version $Id: BaseRenderContext.java,v 1.8 2003/10/07 08:20:24 stephan Exp $
 */

public class BaseRenderContext implements RenderContext {
  private boolean cacheable = true;
  private boolean tempCacheable = false;;

  private RenderEngine engine;
  private Map params;
  private Map values;

  public BaseRenderContext() {
    values = new HashMap();
  }

  public Object get(String key) {
    return values.get(key);
  }

  public void set(String key, Object value) {
    values.put(key, value);
  }

  public Map getParameters() {
    return params;
  }

  public void setParameters(Map parameters) {
    this.params = parameters;
  }

  public RenderEngine getRenderEngine() {
    return engine;
  }

  public void setRenderEngine(RenderEngine engine) {
    this.engine = engine;
  }

  public void setCacheable(boolean cacheable) {
    tempCacheable = cacheable;
  }

  public void commitCache() {
    cacheable = cacheable && tempCacheable;
    tempCacheable = false;
  }

  public boolean isCacheable() {
    return cacheable;
  }
}
