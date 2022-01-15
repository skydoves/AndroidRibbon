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

/** An interface to define functions that collects for List of [RibbonView]-s. */
internal interface IRibbonList {

  fun addRibbon(ribbonView: RibbonView)
  fun addRibbon(position: Int, ribbonView: RibbonView)
  fun addRibbonList(ribbonViewList: List<RibbonView>)

  fun getRibbonView(position: Int): RibbonView
  fun getRibbonViews(): List<RibbonView>

  fun removeRibbon(ribbonView: RibbonView)
  fun removeRibbon(position: Int)
  fun clear()
}
