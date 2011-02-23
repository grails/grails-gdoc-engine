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
 * Macro to easily link to RFCs. The website to link to
 * is currently hard wired.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: RfcMacro.java,v 1.6 2003/08/21 08:50:07 stephan Exp $
 */

public class RfcMacro extends BaseLocaleMacro {

  public String getLocaleKey() {
    return "macro.rfc";
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {

    if (params.getLength() == 1) {
      String number = params.get("0");
      String view = "RFC" + number;
      // ftp://ftp.rfc-editor.org/in-notes/rfc3300.txt
      // http://zvon.org/tmRFC/RFC3300/Output/index.html
      appendRfc(writer, number, view);
      return;
    } else if (params.getLength() == 2) {
      String number = params.get(0);
      String view = params.get(1);
      appendRfc(writer, number, view);
    } else {
      throw new IllegalArgumentException("needs an RFC numer as argument");
    }
  }

  public void appendRfc(Writer writer, String number, String view) throws IOException, IllegalArgumentException {
    //writer.write("<a href=\"ftp://ftp.rfc-editor.org/in-notes/rfc");
    try {
      Integer.getInteger(number);
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
    writer.write("<a href=\"http://zvon.org/tmRFC/RFC");
    writer.write(number);
    writer.write("/Output/index.html\">");
    writer.write(view);
    writer.write("</a>");
    return;
  }
}
