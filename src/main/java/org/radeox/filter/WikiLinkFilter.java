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


package org.radeox.filter;

/*
 * WikiLinkFilter finds WikiLinks in CamelCase in its input and transforms this
 * to <a href="text">...</a> if the wiki page exists. If not
 * it adds a [create text] to the output.
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: WikiLinkFilter.java,v 1.3 2003/08/13 12:37:06 stephan Exp $
 */

public class WikiLinkFilter extends LinkTestFilter {
  /**
   * The regular expression for detecting WikiLinks.
   * This is CamelCase or
   * OldAndUglyWikiLinking :-)
   *
   */
  protected String getLocaleKey() {
    return "filter.wikilink";
  }

  protected String getWikiView(String name) {
    return name;
  }

}
