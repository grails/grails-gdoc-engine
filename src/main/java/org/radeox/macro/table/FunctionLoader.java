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


package org.radeox.macro.table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.radeox.macro.PluginLoader;
import org.radeox.macro.Repository;

/**
 * Plugin loader for table functions
 *
 * @author Stephan J. Schmidt
 * @version $Id: FunctionLoader.java,v 1.4 2003/08/14 07:46:04 stephan Exp $
 */

public class FunctionLoader extends PluginLoader {
  private static Log log = LogFactory.getLog(FunctionLoader.class);

  protected static FunctionLoader instance;

  public static synchronized PluginLoader getInstance() {
    if (null == instance) {
      instance = new FunctionLoader();
    }
    return instance;
  }

  public Class getLoadClass() {
    return Function.class;
  }

  /**
   * Add a plugin to the known plugin map
   *
   * @param plugin Function to add
   */
  public void add(Repository repository, Object plugin) {
    if (plugin instanceof Function) {
      repository.put(((Function) plugin).getName().toLowerCase(), plugin);
    } else {
      log.debug("FunctionLoader: " + plugin.getClass() + " not of Type " + getLoadClass());
    }
  }

}
