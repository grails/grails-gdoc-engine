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
import org.radeox.api.engine.context.InitialRenderContext;

import java.util.*;

/**
 * Repository for plugins
 *
 * @author Stephan J. Schmidt
 * @version $Id: MacroRepository.java,v 1.9 2003/12/17 13:35:36 stephan Exp $
 */

public class MacroRepository extends PluginRepository {
  private static Log log = LogFactory.getLog(MacroRepository.class);
  
  protected static MacroRepository instance;
  protected List loaders;

  public synchronized static MacroRepository getInstance() {
    if (null == instance) {
      instance = new MacroRepository();
    }
    return instance;
  }

  private void initialize(InitialRenderContext context) {
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Macro macro = (Macro) iterator.next();
      macro.setInitialContext(context);
    }
    init();
  }

  public void setInitialContext(InitialRenderContext context) {
    initialize(context);
  }

  private void init() {
    Map newPlugins = new HashMap();

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Macro macro = (Macro) iterator.next();
      newPlugins.put(macro.getName(), macro);
    }
    plugins = newPlugins;
  }

  /**
   * Loads macros from all loaders into plugins.
   */
  private void load() {
    Iterator iterator = loaders.iterator();
    while (iterator.hasNext()) {
      MacroLoader loader = (MacroLoader) iterator.next();
      loader.setRepository(this);
      log.debug("Loading from: " + loader.getClass());
      loader.loadPlugins(this);
    }
  }

  public void addLoader(MacroLoader loader) {
    loader.setRepository(this);
    loaders.add(loader);
    plugins = new HashMap();
    list = new ArrayList();
    load();
  }

  private MacroRepository() {
    loaders = new ArrayList();
    loaders.add(new MacroLoader());
    load();
  }
}
