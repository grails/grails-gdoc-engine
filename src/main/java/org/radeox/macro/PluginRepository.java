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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository for plugins
 *
 * @author Stephan J. Schmidt
 * @version $Id: PluginRepository.java,v 1.5 2003/12/17 13:41:54 stephan Exp $
 */

public class PluginRepository implements Repository {
  protected Map plugins;
  protected List list;

  protected static Repository instance;

  public PluginRepository() {
    plugins = new HashMap();
    list = new ArrayList();
  }

  public boolean containsKey(String key) {
    return plugins.containsKey(key);
  }

  public Object get(String key) {
    return plugins.get(key);
  }

  public List getPlugins() {
    return new ArrayList(plugins.values());
  }

  public void put(String key, Object value) {
    plugins.put(key, value);
    list.add(value);
  }
}
