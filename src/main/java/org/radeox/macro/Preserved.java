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

import org.radeox.util.Encoder;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * A specialized macro that allows to preserve certain special characters
 * by creating character entities. The subclassing macro may decide whether
 * to call replace() before or after executing the actual macro substitution.
 *
 * @author Matthias L. Jugel
 * @version $Id: Preserved.java,v 1.7 2004/06/08 08:46:18 leo Exp $
 */

public abstract class Preserved extends BaseMacro {
  private Map special = new HashMap();
  private String specialString = "";

  /**
   * Encode special character c by replacing with it's hex character entity code.
   */
  protected void addSpecial(char c) {
    addSpecial("" + c, Encoder.toEntity(c));
  }

  /**
   * Add a replacement for the special character c which may be a string
   *
   * @param c the character to replace
   * @param replacement the new string
   */
  protected void addSpecial(String c, String replacement) {
    specialString += c;
    special.put(c, replacement);
  }

  /**
   * Actually replace specials in source.
   * This method can be used by subclassing macros.
   *
   * @param source String to encode
   *
   * @return encoded Encoded string
   */
  protected String replace(String source) {
    StringBuffer tmp = new StringBuffer();
    StringTokenizer stringTokenizer = new StringTokenizer(source, specialString, true);
    String previous = "";
    while (stringTokenizer.hasMoreTokens()) {
      String current = stringTokenizer.nextToken();
      if (special.containsKey(current) && !previous.endsWith("&")) {
        current = (String) special.get(current);
      }
      tmp.append(current);
      previous = current;
    }
    return tmp.toString();
  }
}
