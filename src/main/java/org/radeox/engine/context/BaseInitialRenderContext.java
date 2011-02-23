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


package org.radeox.engine.context;

import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.util.i18n.ResourceManager;
import org.radeox.filter.FilterPipe;

import java.util.Locale;

/**
 * Base impementation for InitialRenderContext
 *
 * @author Stephan J. Schmidt
 * @version $Id: BaseInitialRenderContext.java,v 1.6 2004/04/27 19:30:38 leo Exp $
 */

public class BaseInitialRenderContext extends BaseRenderContext implements InitialRenderContext {

  public BaseInitialRenderContext() {
    Locale locale = new Locale("Basic", "basic");
    set(RenderContext.INPUT_LOCALE, locale);
    set(RenderContext.OUTPUT_LOCALE, locale);
    set(RenderContext.INPUT_BUNDLE_NAME, "radeox_markup");
    set(RenderContext.OUTPUT_BUNDLE_NAME, "radeox_markup");
    set(RenderContext.LANGUAGE_BUNDLE_NAME, "radeox_messages");

    Locale languageLocale = Locale.getDefault();
    set(RenderContext.LANGUAGE_LOCALE, languageLocale);
    ResourceManager.get().setLocale(languageLocale, null);

    set(RenderContext.DEFAULT_FORMATTER, "java");
  }

  public void setFilterPipe(FilterPipe fp) {
    set(InitialRenderContext.FILTER_PIPE, fp);
  }

  public FilterPipe getFilterPipe() {
    return (FilterPipe) get(InitialRenderContext.FILTER_PIPE);
  }

}
