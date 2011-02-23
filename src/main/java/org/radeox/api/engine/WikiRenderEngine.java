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


package org.radeox.api.engine;

/**
 * Interface for RenderEngines that know about Wiki pages.
 *
 * @author Stephan J. Schmidt
 * @version $Id: WikiRenderEngine.java,v 1.1 2003/10/07 08:20:24 stephan Exp $
 */

public interface WikiRenderEngine {
  /**
   * Test for the existence of a wiki page
   *
   * @param name Name of an Wiki Page
   * @return result True if wiki page exists
   */

  public boolean exists(String name);

  public boolean showCreate();

  public void appendLink(StringBuffer buffer, String name, String view, String anchor);

  public void appendLink(StringBuffer buffer, String name, String view);

  public void appendCreateLink(StringBuffer buffer, String name, String view);
}
