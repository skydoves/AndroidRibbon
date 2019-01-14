# AndroidRibbon
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
A library that lets you implement ribbon ui and shimmer animation. <br>

![gif0](https://user-images.githubusercontent.com/24237865/51105497-7873e680-182c-11e9-954a-1bf767d15312.gif) ![gif1](https://user-images.githubusercontent.com/24237865/51105671-fb953c80-182c-11e9-8288-7ce97d5474e8.gif)

## Download
### Gradle
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {  
    implementation "com.github.skydoves:androidribbon:1.0.0"
}
```

## Usage
Add following XML namespace inside your XML layout file.

```gradle
xmlns:app="http://schemas.android.com/apk/res-auto"
```

### RibbonView in layout

```gradle
<com.skydoves.androidribbon.RibbonView
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:text="skydoves"
     android:textColor="@android:color/white"
     app:ribbon_rotation="-45" // set rotation
     app:ribbon_background_color="@color/colorPrimary" // set background color
     app:ribbon_ribbonRadius="4dp" // set radius
     app:ribbon_drawable="@drawable/ribbon03" // set drawable, not background color
     app:ribbon_padding_top="4dp"
     app:ribbon_padding_bottom="8dp"/>
```

### create using Builder
This is how to create `RibbonView` instance using `RibbonView.Builder` class.
```java
new RibbonView.Builder(context)
      .setText("Android-Ribbon")
      .setTextColor(Color.WHITE)
      .setTextSize(13f)
      .setRibbonRotation(-45)
      .setRibbonBackgroundColor(ContextCompat.getColor(context, R.color.bright_lavender))
      .setRibbonDrawable(ContextCompat.getDrawable(context, R.drawable.ribbon03))
      .build();
```

### create using kotlin dsl
This is how to create `RibbonView` instance using kotlin dsl.
```kotlin
val ribbonView = ribbonView(context) {
      text = "Android-Ribbon"
      textColor = Color.WHITE
      textSize = 13f
      textStyle = Typeface.BOLD
      ribbonRotation = -45
      ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon02)
}
```

## ShimmerRibbonView
ShimmerRibbonView lets you implement shimmer animation easily.

### RibbonView in layout
```gradle
<com.skydoves.androidribbon.ShimmerRibbonView
      android:layout_width="130dp"
      android:layout_height="wrap_content"
      android:alpha="0.8"
      app:shimmer_base_alpha="1.0"
      app:shimmer_highlight_alpha="0.5"
      app:shimmer_ribbon_text="Android-Ribbon"
      app:shimmer_ribbon_textColor="@android:color/white"
      app:shimmer_ribbon_textStyle="bold"
      app:shimmer_ribbon_padding_top="3dp"
      app:shimmer_ribbon_padding_bottom="3dp"
      app:shimmer_ribbon_rotation="-45"
      app:shimmer_ribbon_background_color="@color/colorPrimary"/>
```

### create using Builder
This is how to create `ShimmerRibbonView` instance using `ShimmerRibbonView.Builder` class.
```java
new ShimmerRibbonView.Builder(context)
      .setRibbonView(ribbonView)
      .setShimmer(shimmer)
      .build();
```

### create using kotlin dsl
This is how to create `ShimmerRibbonView` instance using kotlin dsl.
```kotlin
val shimmerRibbonView = shimmerRibbonView(context) {
      ribbon = ribbonView(context) {
          text = "Android-Ribbon"
          textColor = Color.WHITE
          textSize = 13f
          textStyle = Typeface.BOLD
          ribbonRotation = -45
          ribbonDrawable = ContextCompat.getDrawable(context, R.drawable.ribbon02)
      }
      shimmer = alphaShimmer {
          setBaseAlpha(1.0f)
          setHighlightAlpha(0.5f)
       }
    }
```

## Shimmer
This library using [shimmer-android](https://github.com/facebook/shimmer-android) by Facebook.<br>
So you can customize shimmer following by [shimmer-instruction](http://facebook.github.io/shimmer-android/) or following examples.

### create using Builder
This is how to create `Shimmer` instance using `Shimmer.Builder` class.
```java
new Shimmer.AlphaHighlightBuilder()
      .setBaseAlpha(1.0f)
      .setHighlightAlpha(0.5f)
      .setRepeatDelay(1000)
      .setDuration(1000)
      .setDirection(Shimmer.Direction.RIGHT_TO_LEFT)
      .build();
```

### create using kotlin dsl
This is how to create `Shimmer` instance using kotlin dsl.
```kotlin
val shimmer_alpha = alphaShimmer {
      setBaseAlpha(1.0f)
      (0.5f)
}
val shimmer_color = colorShimmer {
      setBaseColor(ContextCompat.getColor(context, R.color.colorPrimary))
      setHighlightColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
      setBaseAlpha(1.0f)
      setHighlightAlpha(0.5f)
}
```

## ShimmerLayout
ShimmerLayout lets RibbonViews overlap with other child views.



# License
```xml
Copyright 2019 skydoves

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
