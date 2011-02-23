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


package org.radeox.regex;

/*
 * Class which stores compiled regular expressions
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: Pattern.java,v 1.2 2004/04/20 09:47:03 stephan Exp $
 */

public interface Pattern {
  /**
   * Return a string representation of the regular expression
   *
   * @return String representation of the regular expression
   */
  public String getRegex();
  /**
   * Return whether the pattern is multiline or not
   *
   * @return Ture if the pattern is multiline
   */
  public boolean getMultiline();
}