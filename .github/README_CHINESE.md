# AndroidRibbon
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16) 
[![Build Status](https://travis-ci.org/skydoves/AndroidRibbon.svg?branch=master)](https://travis-ci.org/skydoves/AndroidRibbon)<br>
一个可以让你使用缎带的图书馆,并且很容易就能轻松地模仿动画. <br>

![gif0](https://user-images.githubusercontent.com/24237865/51105497-7873e680-182c-11e9-954a-1bf767d15312.gif) ![gif1](https://user-images.githubusercontent.com/24237865/51105671-fb953c80-182c-11e9-8288-7ce97d5474e8.gif)

## 下載
### Gradle
並將一個從屬代碼添加到您的 `build.gradle` 文件中。
```gradle
dependencies {  
    implementation "com.github.skydoves:androidribbon:1.0.0"
}
```

## 用法
在XML佈局文件中添加XML命名工具。

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
     app:ribbon_rotation="-45" // 旋轉
     app:ribbon_background_color="@color/colorPrimary" // 背景顏色
     app:ribbon_ribbonRadius="4dp" // 半徑
     app:ribbon_drawable="@drawable/ribbon03" // 可提取的
     app:ribbon_padding_top="4dp"
     app:ribbon_padding_bottom="8dp"/>
```

### 使用 Builder 創建
這就是如何利用 `Ribbonview` 的 `RibbonviewBuilder` 的例子。
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

### 使用 kotlin dsl 創建
這就是如何用 kotlin dsl 來創建 `RibbonView` 的例子。
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
ShimmerribonView 幫助你創作 shimmer animation。

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

### 使用 Builder 創建
这就是如何利用 `ShimmerRibbonView.Builder` 的实例创建 `ShimmerRibbonView` 的例子。
```java
new ShimmerRibbonView.Builder(context)
      .setRibbonView(ribbonView)
      .setShimmer(shimmer)
      .build();
```

### 使用 kotlin dsl 創建
這是如何使用 kotlin dsl 創建 `ShimmerRibbonView` 的實例。
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
這個庫使用 Facebook 的 [shimmer-android](https://github.com/facebook/shimmer-android)。<br>
以下是關於微光的詳細 [shimmer-instruction](http://facebook.github.io/shimmer-android/)，您可以參考以下示例。

### 使用 Builder 創建
這是如何使用 `Shimmer.Builder` 類創建 `Shimmer` 的實例。
```java
new Shimmer.AlphaHighlightBuilder()
      .setBaseAlpha(1.0f)
      .setHighlightAlpha(0.5f)
      .setRepeatDelay(1000)
      .setDuration(1000)
      .setDirection(Shimmer.Direction.RIGHT_TO_LEFT)
      .build();
```

### 使用 kotlin dsl 創建
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
RibbonLayout 允許 `RibbonViews` 與其他子視圖重疊。

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

並且應該使用 `RibbonView` 或 `ShimmerRibbonView` 實例設置 `ribbonHeader` 或 `ribbonBottom`。
```java
ribbonLayout.setRibbonHeader(ribbon_header);
ribbonLayout.setRibbonBottomAlign(Gravity.LEFT);
ribbonLayout.setRibbonBottom(ribbon_bottom);
ribbonLayout.setRibbonBottomAlign(Gravity.RIGHT);
```

## RibbonRecyclerView
RibbonRecyclerView 允許您輕鬆實現 RecyclerView 具有 `RibbonView` 項目。

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

添加或刪除 `RibbonView` 項。
```java
recyclerView.addRibbon(ribbonView);
recyclerView.addRibbon(2, ribbonView);
recyclerView.addRibbonList(ribbonViewList);
recyclerView.removeRibbon(ribbonView);
recyclerView.removeRibbon(2);
recyclerView.clear();
```


## RibbonView 屬性
Attributes | Type | Default | Description
--- | --- | --- | ---
ribbonBackgroundColor | Color | #e254ff | 使用顏色設置功能區背景。
ribbonRadius | Float | 10f | 設置功能區角的半徑。 它只能與`ribbonBackgroundColor`一起使用。
ribbonDrawable | Drawable | null | 使用drawable設置功能區背景。 `ribbonBackgroundColor`和`ribbonRadius`將被忽略。
ribbonRotation | Int | 0 | 設置色帶旋轉。 僅在1到90或-90到-1度之間。
paddingLeft | Float | 8f | 設置文本的左側填充。
paddingTop | Float | 4f | 設置文本的頂部填充。
paddingRight | Float | 8f | 設置正確的文本填充。
paddingBottom | Float | 4f | 設置文本的底部填充。
text | String | "" | 設置文字。 它與`android：text`屬性相同。
textColor | Color | Color.WHITE | 設置文字顏色。 它與`android：textColor`屬性相同。
textSize | Float | 12f | 設置文字大小。 它與`android：textSize`屬性相同。
textStyle | Int | Typeface.NORMAL | 設置文字樣式。 它與`android：textStyle`屬性相同。

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
