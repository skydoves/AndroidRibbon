/*
 * Copyright (C) 2019 skydoves
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

package com.skydoves.androidribbon

import android.content.Context
import android.os.Build
import android.util.TypedValue

/** Returns a color of the accent of the application. */
internal val Context.accentColor: Int
  get() {
    val colorAttr: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      R.attr.colorAccent
    } else {
      resources.getIdentifier("colorAccent", "attr", packageName)
    }
    val outValue = TypedValue()
    theme.resolveAttribute(colorAttr, outValue, true)
    return outValue.data
  }
