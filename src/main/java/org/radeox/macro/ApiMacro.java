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

import org.radeox.macro.api.ApiDoc;
import org.radeox.macro.parameter.MacroParameter;

import java.io.IOException;
import java.io.Writer;

/*
 * Macro that replaces {api} with external URLS to api
 * documentation
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: ApiMacro.java,v 1.6 2004/04/27 19:30:38 leo Exp $
 */

public class ApiMacro extends BaseLocaleMacro {
  public String getLocaleKey() {
    return "macro.api";
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {
    String mode;
    String klass;

    if (params.getLength() == 1) {
      klass = params.get("0");
      int index = klass.indexOf("@");
      if (index > 0) {
        mode = klass.substring(index + 1);
        klass = klass.substring(0, index);
      } else {
        mode = "java";
      }
    } else if (params.getLength() == 2) {
      mode = params.get("1").toLowerCase();
      klass = params.get("0");
    } else {
      throw new IllegalArgumentException("api macro needs one or two paramaters");
    }

    ApiDoc.getInstance().expand(writer, klass, mode);
    return;
  }
}
