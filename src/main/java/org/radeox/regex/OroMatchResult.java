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

/*
 * The result when a Matcher object finds matches in some input
 * Implementation for Jakarta ORO package

 * @author stephan
 * @team sonicteam
 * @version $Id: OroMatchResult.java,v 1.2 2004/04/20 13:16:41 stephan Exp $
 */

public class OroMatchResult extends MatchResult {
  private org.apache.oro.text.regex.MatchResult matchResult;

  public OroMatchResult(org.apache.oro.text.regex.MatchResult matchResult) {
    this.matchResult = matchResult;
  }

  public int groups() {
    return matchResult.groups();
  }

  public String group(int i) {
    return matchResult.group(i);
  }

  public int beginOffset(int i) {
    return matchResult.begin(i);
  }

  public int endOffset(int i) {
    return matchResult.end(i);
  }
}