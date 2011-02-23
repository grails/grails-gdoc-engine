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


package org.radeox.macro.table;

import java.util.StringTokenizer;

/**
 * Built a table from a string
 *
 * @author stephan
 * @version $Id: TableBuilder.java,v 1.3 2003/10/06 08:30:02 stephan Exp $
 */

public class TableBuilder {
  public static Table build(String content) {
    Table table = new Table();
    StringTokenizer tokenizer = new StringTokenizer(content, "|\n", true);
    String lastToken = null;
    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      String linkToken = "";
      if(token.indexOf('[') != -1 && token.indexOf(']') == -1) {
	while(token.indexOf(']') == -1 && tokenizer.hasMoreTokens()) {
	  linkToken += token;
	  token = tokenizer.nextToken();
	}
	token = linkToken + token;
      }
      if ("\n".equals(token)) {
        // Handles "\n" - "|\n"
        if (null == lastToken || "|".equals(lastToken)) {
          table.addCell(" ");
        }
        table.newRow();
      } else if (!"|".equals(token)) {
        table.addCell(token);
      } else if (null == lastToken || "|".equals(lastToken)) {
        // Handles "|" "||"
        table.addCell(" ");
      }
      lastToken = token;
    }
    return table;
  }
}
