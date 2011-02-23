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


package org.radeox.filter.regex;

import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * Class that extends RegexReplaceFilter but reads patterns from
 * a locale file instead of hardwired regex
 *
 * @author stephan
 * @team sonicteam
 * @version $Id: LocaleRegexReplaceFilter.java,v 1.6 2003/10/07 08:20:24 stephan Exp $
 */

public abstract class LocaleRegexReplaceFilter extends RegexReplaceFilter {
  private String modifier = null;

  protected abstract String getLocaleKey();

  public void setModifier(String modifier) {
    this.modifier = modifier;
  }

  public String getModifier() {
    return modifier;
  }

  protected boolean isSingleLine() {
    return false;
  }

  protected ResourceBundle getInputBundle() {
    Locale inputLocale = (Locale) initialContext.get(RenderContext.INPUT_LOCALE);
    String inputName = (String) initialContext.get(RenderContext.INPUT_BUNDLE_NAME);
    return ResourceBundle.getBundle(inputName, inputLocale);
  }

  protected ResourceBundle getOutputBundle() {
    String outputName = (String) initialContext.get(RenderContext.OUTPUT_BUNDLE_NAME);
    Locale outputLocale = (Locale) initialContext.get(RenderContext.OUTPUT_LOCALE);
    return ResourceBundle.getBundle(outputName, outputLocale);
  }

  public void setInitialContext(InitialRenderContext context) {
    super.setInitialContext(context);
    clearRegex();

    ResourceBundle outputMessages = getOutputBundle();
    ResourceBundle inputMessages = getInputBundle();

    String match = inputMessages.getString(getLocaleKey() + (modifier != null ? "." + modifier : "") + ".match");
    String print = outputMessages.getString(getLocaleKey() + (modifier != null ? "." + modifier : "") + ".print");
    //System.err.println(getLocaleKey()+": match="+match+" pattern="+print);
    addRegex(match, print, isSingleLine() ? RegexReplaceFilter.SINGLELINE : RegexReplaceFilter.MULTILINE);
  }

  private String getKeyModifier() {
    return modifier;
  }
}