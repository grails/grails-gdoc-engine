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

import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.MalformedPatternException;

/*
 * Class that compiles regular expressions to patterns
 * Implementation for Jakarta ORO package
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: OroCompiler.java,v 1.1 2004/04/20 12:38:26 stephan Exp $
 */

public class OroCompiler extends Compiler {
  private Perl5Compiler internalCompiler;

  private boolean multiline;

  public OroCompiler() {
    this.internalCompiler = new Perl5Compiler();
  }

  public void setMultiline(boolean multiline) {
    this.multiline = multiline;
  }

  public Pattern compile(String regex) {
    org.apache.oro.text.regex.Pattern pattern = null;
    try {
      pattern = internalCompiler.compile(regex,
         (multiline ? Perl5Compiler.MULTILINE_MASK : Perl5Compiler.SINGLELINE_MASK) | Perl5Compiler.READ_ONLY_MASK);
    } catch (MalformedPatternException e) {
      // handle later
    }

    return new OroPattern(regex, multiline, pattern);
  }
}