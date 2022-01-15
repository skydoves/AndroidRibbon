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
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.skydoves.androidribbon.annotations.Dp
import com.skydoves.androidribbon.annotations.Sp
import com.skydoves.androidribbon.internal.viewProperty

@DslMarker
annotation class RibbonDsl

/** create a [RibbonView] by [RibbonView.Builder] using dsl. */
fun ribbonView(context: Context, block: RibbonView.Builder.() -> Unit): RibbonView =
  RibbonView.Builder(context).apply(block).build()

/** RibbonView is advanced of [AppCompatTextView] for implement ribbon. */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class RibbonView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr), RibbonInterface {

  var ribbonDrawable: Drawable? by viewProperty(null)

  @get:ColorInt
  var ribbonBackgroundColor: Int by viewProperty(context.accentColor)

  var ribbonRotation: Int by viewProperty(0)

  @get:Dp
  var ribbonRadius: Float by viewProperty(10f)

  @get:Dp
  var paddingLeft: Float by viewProperty(8f)

  @get:Dp
  var paddingTop: Float by viewProperty(4f)

  @get:Dp
  var paddingRight: Float by viewProperty(8f)

  @get:Dp
  var paddingBottom: Float by viewProperty(4f)

  init {
    onCreate()
    when {
      attrs != null && defStyleAttr != android.R.attr.textViewStyle ->
        getAttrs(attrs, defStyleAttr)
      attrs != null -> getAttrs(attrs)
    }
  }

  private fun onCreate() {
    this.gravity = Gravity.CENTER
    this.background = GradientDrawable().apply {
      setColor(ribbonBackgroundColor)
      cornerRadius = ribbonRadius
    }
  }

  private fun getAttrs(attributeSet: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RibbonView)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
    val typedArray =
      context.obtainStyledAttributes(attributeSet, R.styleable.RibbonView, defStyleAttr, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(typeArray: TypedArray) {
    ribbonDrawable = typeArray.getDrawable(R.styleable.RibbonView_ribbon_drawable)
    ribbonBackgroundColor =
      typeArray.getColor(R.styleable.RibbonView_ribbon_background_color, ribbonBackgroundColor)
    ribbonRadius =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_ribbonRadius, ribbonRadius)
    ribbonRotation = typeArray.getInt(R.styleable.RibbonView_ribbon_rotation, ribbonRotation)
    paddingLeft =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_left, paddingLeft)
    paddingTop = typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_top, paddingTop)
    paddingRight =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_right, paddingRight)
    paddingBottom =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_bottom, paddingBottom)
  }

  /** initialize attributes by [RibbonView.Builder] */
  private fun onCreateByBuilder(builder: Builder) {
    ribbonDrawable = builder.ribbonDrawable
    ribbonBackgroundColor = builder.ribbonBackgroundColor
    ribbonRadius = builder.ribbonRadius
    ribbonRotation = builder.ribbonRotation
    paddingLeft = builder.paddingLeft
    paddingTop = builder.paddingTop
    paddingRight = builder.paddingRight
    paddingBottom = builder.paddingBottom
    text = builder.text
    textSize = builder.textSize
    setTextColor(builder.textColor)
    setTypeface(typeface, builder.textStyle)
    updateRibbon()
  }

  /** update [RibbonView] after finishing inflate. */
  override fun onFinishInflate() {
    super.onFinishInflate()
    updateRibbon()
  }

  /** update [RibbonView] by attributes. */
  override fun updateRibbon() {
    setPadding(
      paddingLeft.dp2px,
      paddingTop.dp2px,
      paddingRight.dp2px,
      paddingBottom.dp2px
    )
    background = ribbonDrawable ?: GradientDrawable().apply {
      cornerRadius = ribbonRadius.dp2px.toFloat()
      setColor(ribbonBackgroundColor)
    }
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    if (changed) {
      rotation(ribbonRotation)
    }
  }

  /** Builder class for creating [RibbonView]. */
  @RibbonDsl
  class Builder(val context: Context) {

    @set:JvmSynthetic
    var ribbonDrawable: Drawable? = null

    @set:JvmSynthetic @ColorInt
    var ribbonBackgroundColor: Int = context.accentColor

    @set:JvmSynthetic
    var ribbonRotation: Int = 0

    @set:JvmSynthetic @Dp
    var ribbonRadius: Float = 10f

    @set:JvmSynthetic @Dp
    var paddingLeft: Float = 8f

    @set:JvmSynthetic @Dp
    var paddingTop: Float = 4f

    @set:JvmSynthetic @Dp
    var paddingRight: Float = 8f

    @set:JvmSynthetic @Dp
    var paddingBottom: Float = 4f

    @set:JvmSynthetic
    var text: CharSequence = ""

    @set:JvmSynthetic @ColorInt
    var textColor = Color.WHITE

    @set:JvmSynthetic @Sp
    var textSize = 12f

    @set:JvmSynthetic
    var textStyle = Typeface.NORMAL

    fun setRibbonDrawable(RibbonDrawable: Drawable?): Builder = apply {
      this.ribbonDrawable = RibbonDrawable
    }

    fun setRibbonDrawableResource(@DrawableRes drawable: Int): Builder = apply {
      this.ribbonDrawable = ContextCompat.getDrawable(this.context, drawable)
    }

    fun setRibbonBackgroundColor(@ColorInt color: Int): Builder = apply {
      this.ribbonBackgroundColor = color
    }

    fun setRibbonBackgroundColorResource(@ColorRes color: Int): Builder = apply {
      this.ribbonBackgroundColor = ContextCompat.getColor(this.context, color)
    }

    fun setRibbonRotation(value: Int): Builder = apply { this.ribbonRotation = value }
    fun setRibbonRadius(@Dp value: Float): Builder = apply { this.ribbonRadius = value }
    fun setPaddingLeft(@Dp value: Float): Builder = apply { this.paddingLeft = value }
    fun setPaddingTop(@Dp value: Float): Builder = apply { this.paddingTop = value }
    fun setPaddingRight(@Dp value: Float): Builder = apply { this.paddingRight = value }
    fun setPaddingBottom(@Dp value: Float): Builder = apply { this.paddingBottom = value }
    fun setText(value: CharSequence): Builder = apply { this.text = value }
    fun setTextColor(@ColorInt value: Int): Builder = apply { this.textColor = value }
    fun setTextSize(@Sp value: Float): Builder = apply { this.textSize = value }
    fun setTextStyle(value: Int): Builder = apply { this.textStyle = value }
    fun build(): RibbonView = RibbonView(context).apply { onCreateByBuilder(this@Builder) }
  }
}
