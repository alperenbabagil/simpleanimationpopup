# Simple Animation Popup
Showing popup with an animation view easily

![](https://media.giphy.com/media/VcxL0025VcDPVpLWsQ/giphy.gif)

## Installation

Add JitPack repository in your root build.gradle at the end of repositories:
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```gradle
implementation 'com.github.alperenbabagil:simpleanimationpopup:1.0.0'
```

## Usage

A demo app also included in the project. Basic use cases and implementations showed in it.

Basically in your Activity implement like this:
```kotlin
SapDialog(this).apply {
  titleText="Lorem ipsum"
  messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
  addPositiveButton("ok"){
    Toast.makeText(this@MainActivity, "positive button clicked", Toast.LENGTH_SHORT).show()
  }
}.build().show()
```

If you want to show only animation (designed for especially loading popups) you must set `isOnlyAnimation` parameter to true.
```kotlin
SapDialog(this).apply {
  isOnlyAnimation=true
}.build().show()
```

