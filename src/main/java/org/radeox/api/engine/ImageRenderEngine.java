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
 * RenderEngine interface for RenderEngines that know how to handle images,
 * e.g. small arrows for external links in the {link...} macro.
 *
 * @author Stephan J. Schmidt
 * @version $Id: ImageRenderEngine.java,v 1.1 2003/10/07 08:20:24 stephan Exp $
 */

public interface ImageRenderEngine {
  /**
   * Get a link to an image. This can be used by filters or
   * macros to get images for e.g. external links or icons
   * Should be refactored to get other images as well
   *
   * @return result String with an HTML link to an image
   */
  public String getExternalImageLink();
}
