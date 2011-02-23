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
 * SmileyFilter replaces frownies :-( with smileys :-)
 *
 * @author Stephan J. Schmidt
 * @version $Id: SmileyFilter.java,v 1.1 2003/03/05 15:25:08 stephan Exp $
 */

import org.radeox.filter.regex.RegexReplaceFilter;

public class SmileyFilter extends RegexReplaceFilter implements CacheFilter {
  public SmileyFilter() {
    super(":-\\(", ":-)");
  };
}