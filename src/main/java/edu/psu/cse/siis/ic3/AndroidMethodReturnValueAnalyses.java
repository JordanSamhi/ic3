/*
 * Copyright (C) 2015 The Pennsylvania State University and the University of Wisconsin
 * Systems and Internet Infrastructure Security Laboratory
 *
 * Author: Damien Octeau
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.psu.cse.siis.ic3;

import java.util.Collections;
import java.util.Set;

import edu.psu.cse.siis.coal.arguments.LanguageConstraints.Call;
import edu.psu.cse.siis.coal.arguments.MethodReturnValueAnalysis;
import edu.psu.cse.siis.coal.arguments.MethodReturnValueManager;
import soot.Scene;

public class AndroidMethodReturnValueAnalyses {
  public static void registerAndroidMethodReturnValueAnalyses(final String appName) {
    MethodReturnValueManager.v().registerMethodReturnValueAnalysis(
        "java.lang.String getPackageName()", new MethodReturnValueAnalysis() {

          @Override
          public Set<Object> computeMethodReturnValues(Call call) {

            if (Scene.v().getActiveHierarchy().isClassSubclassOfIncluding(
                call.stmt.getInvokeExpr().getMethod().getDeclaringClass(),
                Scene.v().getSootClass("android.content.Context"))) {
              return Collections.singleton((Object) appName);
            } else {
              return null;
            }
          }
        });
  }
}
