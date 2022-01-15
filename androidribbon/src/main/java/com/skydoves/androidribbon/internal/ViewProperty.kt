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

package com.skydoves.androidribbon.internal

import com.skydoves.androidribbon.RibbonInterface
import com.skydoves.androidribbon.RibbonView
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * An extension for properties that extends [RibbonInterface] interface to initialize with [ViewPropertyDelegate].
 *
 * @param defaultValue A default value for this property.
 *
 * @return A [ViewPropertyDelegate] which is readable and writable property.
 */
@JvmSynthetic
internal fun <T : Any?> RibbonInterface.viewProperty(
  defaultValue: T,
  invalidate: (value: T) -> Unit = {}
): ViewPropertyDelegate<T> {
  return ViewPropertyDelegate(
    defaultValue = defaultValue,
    viewInvalidator = invalidate,
    ribbonInvalidator = { updateRibbon() }
  )
}

/**
 * A delegate class to invalidate [RibbonView] class if the [propertyValue] has been updated by the setter.
 *
 * @param defaultValue A default value for this property.
 * @param ribbonInvalidator An executable lambda function to invalidate [RibbonView].
 *
 * @return A readable and writable property.
 */
internal class ViewPropertyDelegate<T : Any?>(
  defaultValue: T,
  private val ribbonInvalidator: () -> Unit,
  private val viewInvalidator: (value: T) -> Unit
) : ReadWriteProperty<Any?, T> {

  private var propertyValue: T = defaultValue

  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    return propertyValue
  }

  override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    if (propertyValue != value) {
      propertyValue = value
      ribbonInvalidator.invoke()
      viewInvalidator.invoke(value)
    }
  }
}
