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

/**
 * Plugin loader for macros
 *
 * @author Stephan J. Schmidt
 * @version $Id: MacroLoader.java,v 1.5 2004/05/03 11:12:37 stephan Exp $
 */

public class MacroLoader extends PluginLoader {
  private static Log log = LogFactory.getLog(MacroLoader.class);

  public Class getLoadClass() {
    return Macro.class;
  }

  /**
   *  Add a plugin to the known plugin map
   *
   * @param repository a Repository to add the plugin to
   * @param plugin a Macro to add to the repository
   */
  public void add(Repository repository, Object plugin) {
    if (plugin instanceof Macro) {
      repository.put(((Macro) plugin).getName(), plugin);
    } else {
      log.debug("MacroLoader: " + plugin.getClass() + " not of Type " + getLoadClass());
    }
  }

}
