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
 * Displays a file path. This is used to store a filepath in an
 * OS independent way and then display the file path as needed.
 * This macro also solves the problems with to many backslashes
 * in Windows filepaths when they are entered in Snipsnap.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: FilePathMacro.java,v 1.9 2004/04/27 19:30:38 leo Exp $
 */

public class FilePathMacro extends LocalePreserved {
   public String getLocaleKey() {
    return "macro.filepath";
  }

  public FilePathMacro() {
    addSpecial('\\');
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {

    if (params.getLength() == 1) {
      String path = params.get("0").replace('/', '\\');
      writer.write(replace(path));
    }
    return;
  }
}
