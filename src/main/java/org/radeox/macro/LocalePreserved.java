package org.radeox.macro;

import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.util.i18n.ResourceManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Locale;
import java.util.ResourceBundle;

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


public abstract class LocalePreserved extends Preserved implements LocaleMacro {
  private static Log log = LogFactory.getLog(LocalePreserved.class);

  private String name;

  public String getName() {
    return name;
  }

  public String getDescription() {
    try {
      return ResourceManager.getBundle((String) initialContext.get(RenderContext.LANGUAGE_BUNDLE_NAME))
        .getString(getLocaleKey() + ".description");
    } catch (Exception e) {
      log.warn("missing macro description for " + getLocaleKey());
      return super.getDescription();
    }
  }

  public String[] getParamDescription() {
    try {
      return ResourceManager.getBundle((String) initialContext.get(RenderContext.LANGUAGE_BUNDLE_NAME))
        .getString(getLocaleKey() + ".params").split(";");
    } catch (Exception e) {
      return super.getParamDescription();
    }
  }

  public void setInitialContext(InitialRenderContext context) {
    super.setInitialContext(context);
    Locale languageLocale = (Locale) context.get(RenderContext.LANGUAGE_LOCALE);
    String languageName = (String) context.get(RenderContext.LANGUAGE_BUNDLE_NAME);
    ResourceBundle messages = ResourceBundle.getBundle(languageName, languageLocale);

    Locale inputLocale = (Locale) context.get(RenderContext.INPUT_LOCALE);
    String inputName = (String) context.get(RenderContext.INPUT_BUNDLE_NAME);
    ResourceBundle inputMessages = ResourceBundle.getBundle(inputName, inputLocale);

    name = inputMessages.getString(getLocaleKey() + ".name");

    try {
      description = messages.getString(getLocaleKey() + ".description");
    } catch (Exception e) {
      log.warn("Cannot read description from properties " + inputName + " for " + getLocaleKey());
    }
  }
}
