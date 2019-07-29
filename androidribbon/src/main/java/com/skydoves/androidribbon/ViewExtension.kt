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

@file:Suppress("unused")

package com.skydoves.androidribbon

import android.view.View

/** rotate view between 1 to 90 or -90 to -1. */
internal fun View.rotation(degree: Int) {
  if (degree in 1..90) {
    pivotX = 1.0f
    pivotY = 0.5f
    x += measuredWidth / 2
    y -= measuredWidth / 4 - measuredWidth / 16
  } else if (degree in -90..-1) {
    pivotX = 0.5f
    pivotY = 1.0f
    x -= measuredWidth / 4 - measuredWidth / 16
    y += measuredWidth / 2
  }

  rotation = degree.toFloat()
}
