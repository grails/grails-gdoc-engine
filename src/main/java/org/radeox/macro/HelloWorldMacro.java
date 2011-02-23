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

import org.radeox.macro.parameter.MacroParameter;

import java.io.IOException;
import java.io.Writer;

/*
 * Hello world example macro. This Macro displays a hello world string.
 *
 * @author stephan
 * @version $Id: HelloWorldMacro.java,v 1.4 2003/10/07 08:05:33 stephan Exp $
 */

public class HelloWorldMacro extends BaseMacro {
  private String[] paramDescription = {"1: name to print"};

  public String getName() {
    return "hello";
  }

  public String getDescription() {
    return "Say hello example macro.";
  }

  public String[] getParamDescription() {
    return paramDescription;
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {
    if (params.getLength() == 1) {
      writer.write("Hello <b>");
      writer.write(params.get("0"));
      writer.write("</b>");
    } else {
      throw new IllegalArgumentException("Number of arguments does not match");
    }
  }
}
