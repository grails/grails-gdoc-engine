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

import org.radeox.filter.interwiki.InterWiki;
import org.radeox.macro.parameter.MacroParameter;

import java.io.IOException;
import java.io.Writer;

/*
 * Macro that shows all know interwiki mappings.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: InterWikiMacro.java,v 1.5 2004/04/27 19:30:38 leo Exp $
 */

public class InterWikiMacro extends BaseLocaleMacro {
  public String getLocaleKey() {
    return "macro.interwiki";
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {
    InterWiki interWiki = InterWiki.getInstance();
    interWiki.appendTo(writer);
    return;
  }
}
