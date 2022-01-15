<h1 align="center">AndroidRibbon</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=16"><img alt="API" src="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/skydoves/AndroidRibbon/actions"><img alt="Build Status" src="https://github.com/skydoves/AndroidRibbon/workflows/Android%20CI/badge.svg"/></a> 
  <a href="https://skydoves.github.io/libraries/androidribbon/javadoc/androidribbon/"><img alt="Javadoc" src="https://img.shields.io/badge/Javadoc-AndroidRibbon-yellow.svg"/></a> 
  <a href="https://github.com/skydoves"><img alt="Profile" src="https://skydoves.github.io/badges/skydoves.svg"/></a> 
</p>

<p align="center">  
:ribbon: The simple way to implement a  beautiful ribbon with the shimmering on Android.
</p>
</br>

<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/51105497-7873e680-182c-11e9-954a-1bf767d15312.gif" width="33%"/>
<img src="https://user-images.githubusercontent.com/24237865/51105671-fb953c80-182c-11e9-8288-7ce97d5474e8.gif" width="33%"/>
</p>

## Download
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/androidribbon.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.skydoves%22%20AND%20a:%22androidribbon%22)
[![Jitpack](https://jitpack.io/v/skydoves/AndroidRibbon.svg)](https://jitpack.io/#skydoves/AndroidRibbon)
### Gradle
Add the codes below to your **root** `build.gradle` file (not your module build.gradle file):
```gradle
allprojects {
    repositories {
        mavenCentral()
    }
}
```
Next, add the dependency below to your **module**'s `build.gradle` file:
```gradle
dependencies {  
    implementation "com.github.skydoves:androidribbon:1.0.3"
}
```

## Usage
Add XML namespace inside your XML layout file as in the following:

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
This is how to create `RibbonView`'s instance using `RibbonView.Builder` class.
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
This is how to create `RibbonView`'s instance using kotlin dsl.
```kotlin
val ribbonView = ribbonView(this) {
      setText("Android-Ribbon")
      setTextColor(Color.WHITE)
      setTextSize(13f)
      setTextStyle(Typeface.BOLD)
      setRibbonRotation(-45)
      setRibbonDrawableResource(R.drawable.ribbon02)
    }
```

## ShimmerRibbonView
ShimmerRibbonView lets you implement shimmer animation easily.

### ShimmerRibbonView in layout
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
This is how to create `ShimmerRibbonView`'s instance using `ShimmerRibbonView.Builder` class.
```java
new ShimmerRibbonView.Builder(context)
      .setRibbonView(ribbonView)
      .setShimmer(shimmer)
      .build();
```

### create using kotlin dsl
This is how to create `ShimmerRibbonView`'s instance using kotlin dsl.
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
Here are the detail [shimmer-instruction](http://facebook.github.io/shimmer-android/) about shimmer or you can reference below examples.

### create using Builder
This is how to create `Shimmer`'s instance using `Shimmer.Builder` class.
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
This is how to create `Shimmer`'s instance using kotlin dsl.
```kotlin
val shimmer_alpha = alphaShimmer {
      setBaseAlpha(1.0f)
      setHighlightAlpha(0.5f)
}
val shimmer_color = colorShimmer {
      setBaseAlpha(1.0f)
      setHighlightAlpha(0.5f)
      setBaseColor(ContextCompat.getColor(context, R.color.colorPrimary))
      setHighlightColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
}
```

## RibbonLayout
RibbonLayout lets RibbonViews overlap with other child views.

### RibbonLayout in layout
```gradle
<com.skydoves.androidribbon.RibbonLayout
      android:id="@+id/ribbonLayout01"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:ribbonLayout_header_align="left"
      app:ribbonLayout_bottom_align="center">

     <ImageView
          android:layout_width="match_parent"
          android:layout_height="180dp"
          android:src="@drawable/background02"
          android:scaleType="fitXY"/>
</com.skydoves.androidribbon.RibbonLayout>
```

And should set `ribbonHeader` or `ribbonBottom` using `RibbonView` or `ShimmerRibbonView` instance.
```java
ribbonLayout.setRibbonHeader(ribbon_header);
ribbonLayout.setRibbonBottomAlign(Gravity.LEFT);
ribbonLayout.setRibbonBottom(ribbon_bottom);
ribbonLayout.setRibbonBottomAlign(Gravity.RIGHT);
```

## RibbonRecyclerView
RibbonRecyclerView lets you implement RecyclerView has `RibbonView` items easily.

### RibbonRecyclerView in layout
```gradle
<com.skydoves.androidribbon.RibbonRecyclerView
      android:id="@+id/ribbonRecyclerView"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:layout_marginTop="15dp"
      app:ribbon_recycler_space="3dp"
      app:ribbon_recycler_orientation="horizontal"/>
```

Add or remove `RibbonView` items.
```java
recyclerView.addRibbon(ribbonView);
recyclerView.addRibbon(2, ribbonView);
recyclerView.addRibbonList(ribbonViewList);
recyclerView.removeRibbon(ribbonView);
recyclerView.removeRibbon(2);
recyclerView.clear();
```


## RibbonView Attributes
Attributes | Type | Default | Description
--- | --- | --- | ---
ribbonBackgroundColor | Color | #e254ff | sets ribbon background using color.
ribbonRadius | Float | 10f | sets ribbon corner's radius. It's only active using with `ribbonBackgroundColor`.
ribbonDrawable | Drawable | null | sets ribbon background using drawable. `ribbonBackgroundColor` and `ribbonRadius` will be ignored.
ribbonRotation | Int | 0 | sets ribbon rotation. Only between 1 to 90 or -90 to -1 degree.
paddingLeft | Float | 8f | sets left padding of the text.
paddingTop | Float | 4f | sets top padding of the text.
paddingRight | Float | 8f | sets right padding of the text.
paddingBottom | Float | 4f | sets bottom padding of the text.
text | String | "" | sets text. It is same as `android:text` attribute.
textColor | Color | Color.WHITE | sets text color. It is same as `android:textColor` attribute.
textSize | Float | 12f | sets text size. It is same as `android:textSize` attribute.
textStyle | Int | Typeface.NORMAL | sets text style. It is same as `android:textStyle` attribute.

## ShimmerRibbonView Attributes
Attributes | Type | Default | Description
--- | --- | --- | ---
ribbon | RibbonView | RibbonView(context) | sets `RibbonView` on the frame.
shimmer | Shimmer | AlphaHighlightBuilder(context).build() | sets `Shimmer` on the frame.

## ShimmerLayout Attributes
Attributes | Type | Default | Description
--- | --- | --- | ---
ribbonHeader | RibbonView | RibbonView(context) | sets header `RibbonView` on the frame.
ribbonBottom | RibbonView | RibbonView(context) | sets bottom `RibbonView` on the frame.
ribbonHeaderAlign | Gravity | Gravity.START | sets an align of the header ribbon.
ribbonBottomAlign | Gravity | Gravity.CENTER | sets an align of the bottom ribbon.

## Find this library useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/AndroidRibbon/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/skydoves)__ me for my next creations! 🤩

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
