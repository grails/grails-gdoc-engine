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

package org.radeox.util.i18n;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * A Resource Manager implementation to aid the use of locales.
 * It works similar to what the bundle tag in jstl fmt taglibs does but adapted
 * for use withing a threaded java environment.
 *
 * @author Matthias L. Jugel
 * @version $Id: ResourceManager.java,v 1.5 2004/06/08 07:54:37 leo Exp $
 */
public class ResourceManager {
  private static Log log = LogFactory.getLog(ResourceManager.class);

  private static ThreadLocal instance = new ThreadLocal() {
    protected synchronized Object initialValue() {
      return new ResourceManager();
    }
  };

  /**
   * Thread re-use makes your life hard. If you suspect leakage of locale
   * and bundles into other threads, using forceGet() may be a good choice.
   * @return a new instance of the ResourceManager
   */
  public static ResourceManager forceGet() {
    instance.set(null);
    return get();
  }

  /**
   * Get a new thread-local instance of the ResourceManager
   * If you are having problems with bundles beeing the same for different
   * threads and locales, try forceGet()
   * @return the thread-local ResourceManager
   */
  public static ResourceManager get() {
    ResourceManager resourceManager = (ResourceManager) instance.get();
    if(null == resourceManager) {
      resourceManager = new ResourceManager();
      instance.set(resourceManager);
    }
    return resourceManager;
  }

  /**
   * Get ResourceBundle using the base name provided. The bundle is located
   * using previously given locale settings.
   * @param baseName the bundle base name
   * @return the bundle
   */
  public static ResourceBundle getBundle(String baseName) {
    return ResourceManager.get().getResourceBundle(baseName);
  }

  /**
   * Get the locale of the loaded bundle. This is the actual locale of the
   * bundle, not the locale requested.
   * @param baseName the bundle base name
   * @return the locale of the bundle
   */
  public static Locale getLocale(String baseName) {
    return ResourceManager.get().getResourceBundle(baseName).getLocale();
  }

  public static String getString(String baseName, String key) {
    try {
      ResourceBundle bundle = getBundle(baseName);
      return bundle.getString(key);
    } catch (Exception e) {
      log.warn("missing resource for bundle '"+baseName+"', key '"+key+"'", e);
      return "???"+key+"???";
    }
  }

  private Locale locale = null;
  private Enumeration fallback = null;
  private Map resourceBundles = new HashMap();

  /**
   * Set the locale and potential fallbacks for this thread.
   * @param locale the requested locale
   * @param fallback a set of fallback locales, i.e. request.getLocales()
   */
  public void setLocale(Locale locale, Enumeration fallback) {
    this.locale = locale;
    this.fallback = fallback;
  }

  /**
   * Get the bundle that is active for this thread. This is done by loading
   * either the specified locale based resource bundle and, if that fails, by
   * looping through the fallback locales to locate a usable bundle.
   *
   * @return the resource bundle
   */
  public ResourceBundle getResourceBundle(String baseName) {
//    System.out.println(this + " " + this.locale+" ("+resourceBundles.size()+")");
    ResourceBundle bundle = (ResourceBundle) resourceBundles.get(baseName);
    if(null == bundle) {
      resourceBundles.put(baseName, bundle = findBundle(baseName));
    }
    return bundle;
  }

  /**
   * Find a resource bundle by looking up using the locales. This is done by loading
   * either the specified locale based resource bundle and, if that fails, by
   * looping through the fallback locales to locate a usable bundle.
   */
  private ResourceBundle findBundle(String baseName) {
    ResourceBundle resourceBundle = null;

    // first try to load the resource bundle with the specified locale
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    if (null != locale) {
      try {
        resourceBundle = ResourceBundle.getBundle(baseName, locale, cl);
      } catch (Exception e) {
        log.fatal("unable to load a default bundle: "+baseName+"_"+locale);
      }
      // check that the requested main locale matches the resource bundle's,
      // since we get the system fallback locale if no match is found
      if(!resourceBundle.getLocale().equals(locale)) {
        resourceBundle = null;
      }
    }

    // loop through the fall back locales until a bundle is found
    if (null == resourceBundle) {
      if(null != fallback) {
        while(fallback.hasMoreElements()) {
          Locale testLocale = (Locale) fallback.nextElement();
          log.debug("looking up locale "+testLocale);
          ResourceBundle testBundle = ResourceBundle.getBundle(baseName, testLocale, cl);
          String language = testBundle.getLocale().getLanguage();
          String country = testBundle.getLocale().getCountry();

          if (testBundle.getLocale().equals(testLocale)) {
            resourceBundle = testBundle;
            log.debug("found bundle for locale " +baseName+"_"+ testBundle.getLocale());
            break;
          } else if (testLocale.getLanguage().equals(language)) {
            if (testLocale.getCountry().equals(country)) {
              // language and country match which is good, keep looking for variant too
              resourceBundle = testBundle;
              log.debug("potential bundle: " + baseName + "_" + testBundle.getLocale());
              continue;
            } else {
              // only accept this if there is no better previous lookup
              if (null == resourceBundle) {
                resourceBundle = testBundle;
                log.debug("potential bundle: " + baseName+"_"+testBundle.getLocale());
              }
              continue;
            }
          }
        }
      }

      // make sure the resource bundle is loaded (should not happen)
      if (null == resourceBundle) {
        resourceBundle = ResourceBundle.getBundle(baseName);
        if (null != resourceBundle) {
          log.debug("system locale bundle taken: " + baseName + "_" + resourceBundle.getLocale());
        }
      }
    }

    return resourceBundle;
  }
}
