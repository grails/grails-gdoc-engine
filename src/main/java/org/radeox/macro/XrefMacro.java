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
import org.radeox.macro.xref.XrefMapper;

import java.io.IOException;
import java.io.Writer;

/*
 * Macro that replaces {xref} with external URLS to xref
 * source code
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: XrefMacro.java,v 1.6 2004/04/27 19:30:38 leo Exp $
 */

public class XrefMacro extends BaseLocaleMacro {
  public String getLocaleKey() {
    return "macro.xref";
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {
    String project;
    String klass;
    int lineNumber = 0;

    if (params.getLength() >= 1) {
      klass = params.get("0");

      int index = klass.indexOf("@");
      if (index > 0) {
        project = klass.substring(index + 1);
        klass = klass.substring(0, index);
      } else {
        project = "SnipSnap";
      }
      if (params.getLength() == 2) {
        lineNumber = Integer.parseInt(params.get("1"));
      }
    } else {
      throw new IllegalArgumentException("xref macro needs one or two paramaters");
    }

    XrefMapper.getInstance().expand(writer, klass, project, lineNumber);
    return;
  }
}
