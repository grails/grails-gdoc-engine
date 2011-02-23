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


package org.radeox.macro.api;

import java.io.IOException;
import java.io.Writer;

/**
 * Converts a Ruby class name to an Ruby api doku url
 *
 * @author Stephan J. Schmidt
 * @version $Id: RubyApiConverter.java,v 1.4 2003/05/23 10:47:25 stephan Exp $
 */

public class RubyApiConverter extends BaseApiConverter {

  public void appendUrl(Writer writer, String className) throws IOException {
    writer.write(baseUrl);
    writer.write(className.toLowerCase());
    writer.write(".html");
  }

  public String getName() {
    return "Ruby";
  }
}
