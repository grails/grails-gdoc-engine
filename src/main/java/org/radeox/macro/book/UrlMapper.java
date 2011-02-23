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


package org.radeox.macro.book;

import java.io.IOException;
import java.io.Writer;

/**
 * Manages links to keys
 *
 * @author Stephan J. Schmidt
 * @version $Id: UrlMapper.java,v 1.2 2003/02/06 13:41:42 leo Exp $
 */

public interface UrlMapper {
  public Writer appendTo(Writer writer) throws IOException;

  public Writer appendUrl(Writer writer, String key)
      throws IOException;
}
