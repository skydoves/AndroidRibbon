package com.skydoves.androidribbondemo

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.skydoves.androidribbon.ShimmerRibbonView
import com.skydoves.androidribbon.alphaShimmer
import com.skydoves.androidribbon.ribbonView
import com.skydoves.androidribbon.shimmerRibbonView

/**
 * Developed by skydoves on 2019-01-14.
 * Copyright (c) 2018 skydoves rights reserved.
 */

object RibbonFactory {
    fun getChristmasRibbonHeader(context: Context): ShimmerRibbonView {
        return shimmerRibbonView(context) {
            ribbon = ribbonView(context) {
                ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon_red_header)
            }.apply { alpha = 0.9f }
            shimmer = alphaShimmer {
                setBaseAlpha(1.0f)
                setHighlightAlpha(0.5f)
            }
        }
    }

    fun getChristmasRibbonBottom(context: Context): ShimmerRibbonView {
        return shimmerRibbonView(context) {
            ribbon = ribbonView(context) {
                ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon_red_bottom)
            }.apply { alpha = 0.9f }
            shimmer = alphaShimmer {
                setBaseAlpha(1.0f)
                setHighlightAlpha(0.5f)
            }
        }
    }

    fun getChristmasPinkRibbonHeader(context: Context): ShimmerRibbonView {
        return shimmerRibbonView(context) {
            ribbon = ribbonView(context) {
                ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon02)
                ribbonRotation = -45
                text = "Android-Ribbon"
                textColor = Color.WHITE
                textSize = 13f
                textStyle = Typeface.BOLD
            }
            shimmer = alphaShimmer {
                setBaseAlpha(1.0f)
                setHighlightAlpha(0.5f)
            }
        }
    }

    fun getChristmasPinkRibbonBottom(context: Context): ShimmerRibbonView {
        return shimmerRibbonView(context) {
            ribbon = ribbonView(context) {
                ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon02)
                text = "Android-Ribbon-Bottom"
                textColor = Color.WHITE
                textSize = 13f
                textStyle = Typeface.BOLD
            }
            shimmer = alphaShimmer {
                setBaseAlpha(1.0f)
                setHighlightAlpha(0.5f)
            }
        }
    }

    fun getPresentRibbonHeader(context: Context): ShimmerRibbonView {
        return shimmerRibbonView(context) {
            ribbon = ribbonView(context) {
                ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon_vertical)
            }
            shimmer = alphaShimmer {
                setBaseAlpha(1.0f)
                setHighlightAlpha(0.3f)
            }
        }
    }

    fun getPresentRibbonBottom(context: Context): ShimmerRibbonView {
        return shimmerRibbonView(context) {
            ribbon = ribbonView(context) {
                ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon03)
                paddingBottom = 14f
                paddingTop = 10f
                text = "Jaewoong Eum"
                textColor = Color.WHITE
                textSize = 13f
                textStyle = Typeface.BOLD
            }
            shimmer = alphaShimmer {
                setBaseAlpha(1.0f)
                setHighlightAlpha(0.5f)
            }
        }
    }
}