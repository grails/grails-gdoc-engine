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
import org.radeox.util.Service;

import java.util.Iterator;

/**
 * Plugin loader
 *
 * @author Stephan J. Schmidt
 * @version $Id: PluginLoader.java,v 1.6 2004/01/09 12:27:14 stephan Exp $
 */

public abstract class PluginLoader {
  private static Log log = LogFactory.getLog(PluginLoader.class);

  protected Repository repository;

  public Repository loadPlugins(Repository repository) {
    return loadPlugins(repository, getLoadClass());
  }

  public void setRepository(Repository repository) {
    this.repository = repository;
  }

  public Iterator getPlugins(Class klass) {
    return Service.providers(klass);
  }

  public Repository loadPlugins(Repository repository, Class klass) {
    if (null != repository) {
      /* load all macros found in the services plugin control file */
      Iterator iterator = getPlugins(klass);
      while (iterator.hasNext()) {
        try {
          Object plugin = iterator.next();
          add(repository, plugin);
          log.debug("PluginLoader: Loaded plugin: " + plugin.getClass());
        } catch (Exception e) {
          log.warn("PluginLoader: unable to load plugin", e);
        }
      }
    }
    return repository;
  }

  /**
   * Add a plugin to the known plugin map
   *
   * @param plugin Plugin to add
   */
  public abstract void add(Repository repository, Object plugin);

  public abstract Class getLoadClass();
}
