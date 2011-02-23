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
 * Class which stores regular expressions
 * Implementation for Jakarta ORO package
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: OroPattern.java,v 1.1 2004/04/20 12:38:26 stephan Exp $
 */

public class OroPattern implements Pattern {
  private String regex;
  private boolean multiline;
  private org.apache.oro.text.regex.Pattern internPattern;

  public OroPattern(String regex, boolean multiline, org.apache.oro.text.regex.Pattern pattern) {
    this.regex = regex;
    this.multiline = multiline;
    this.internPattern = pattern;
  }

  protected org.apache.oro.text.regex.Pattern getPattern() {
    return internPattern;
  }

  public String getRegex() {
    return regex;
  }

  public boolean getMultiline() {
    return multiline;
  }
}