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
 * Class that compiles regular expressions to patterns
 * Implementation for regex package in JDK 1.4
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: JdkCompiler.java,v 1.1 2004/04/16 09:10:50 stephan Exp $
 */

public class JdkCompiler extends Compiler {
  private boolean multiline;

  public void setMultiline(boolean multiline) {
    this.multiline = multiline;
  }

  public Pattern compile(String regex) {
    return new JdkPattern(regex, multiline);
  }
}