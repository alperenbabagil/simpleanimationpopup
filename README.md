# Simple Animation Popup
Showing popup with an animation view easily. It is based on Android.Dialog and compatible with Sdk v16 and higher.

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
implementation 'com.github.alperenbabagil:simpleanimationpopup:1.1.0'
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

If you want to show only animation (designed for especially loading popups) you must set `isOnlyAnimation` property to true.
```kotlin
SapDialog(this).apply {
  isOnlyAnimation=true
}.build().show()
```
![](https://media.giphy.com/media/eGsR8bv3VNM40iKYK7/giphy.gif)

If you want to use predefined extension functions, your activity must extend `SapActivity`. It handles `currentDialog.dismiss()` and assigns new dialog to interface's `currentDialog` variable. The extension gives `showDefaultDialog`, `showErrorDialog`, `showWarningDialog`, `showLoadingDialog` and `showInfoDialog` functions.
```kotlin
showWarningDialog(
  warningStr = "This is a warning string", positiveButton = Pair(
    "Wow, I'm warned", null)
  )
```
```kotlin
showErrorDialog(titleRes = R.string.dangerous_error, positiveButton = Pair(
  "ok", {
    //run error positive button clicked
  }
), negativeButton = Pair("whatever", {
    //run error negative button clicked
  })
)
```
```kotlin
showInfoDialog(infoStr = "you are informed",isCancellable = true)
```

## Customization
The popup is highly customizable, you can even load entire custom layout. All you have to do is create xml,style with name library used. By default library uses `colorPrimary`, `colorPrimaryDark` and `colorAccent` values that app uses.

### Animations
The libary uses [Lottie](https://lottiefiles.com/) files to show animations. By default library has 2 animations: sap_error_anim.json and sap_loading_anim.json. If you add lottie animation files to `R.raw` direction with same names, these animations will be overriden by your animations. 

If you want to set popup animation dynamically, you must set `animResource` property as desired animation file that resides in `R.raw` resource id.  
```kotlin
SapDialog(this).apply {
  animResource=R.raw.my_super_animation
}.build().show()
```
Animation loop state is arranged by `loopAnimation` property. If it is true animation will never stop. Else, animation will play only once.

Animation auto start state is arranged by `autoStartAnimation` property. If it is true animation will start playing as soon as it becomes visible. Else, animation will not play until it intentionally started via `SapDialog.startAnimation()` method.

### Resource keys
You can override library resources by adding desired resources to your resource direction with names that popup library uses.
The library uses 2 layout resource files to construct dialog layout: `simple_animation_popup_layout` used to popup with title,message,buttons etc. `simple_only_animation_popup` is used to show dialog with only animation view. You can override these layout if you want to change layout completely.

If you want partial customization, you can override `styles.xml` values. Keys start with Sap and ends with related view. For example:
```xml
<style name="SapMessageTVStyle" parent="Theme.AppCompat">
	<item name="android:textColor">@color/sapMessageTextColor</item>
        <item name="android:textSize">20sp</item>
        <item name="android:gravity">center</item>
        <item name="android:paddingTop">@dimen/SapMessageTopPadding</item>
        <item name="android:paddingBottom">@dimen/SapMessageBottomPadding</item>
</style>
```
this style element arranges popup message view. You can add a style element to your app's style file with `SapMessageTVStyle` key to change popup message textview. You can see all styles in here: [styles.xml](https://github.com/alperenbabagil/simpleanimationpopup/blob/master/simpleanimationpopuplibrary/src/main/res/values/styles.xml)

### Popup title color
You can change popup title color dynamically by setting `titleTextColor` property to desired color resource id.
```kotlin
SapDialog(this).apply {
  titleTextColor=R.color.colorAccent
}.build().show()
```

### Immersive mode support
You can maintain immersive mode by setting `isFullScreen` property true. Demo app includes a showcase about this feature.
```kotlin
SapDialog(this).apply {
  isFullScreen=true
}.build().show()
```

### Interaction

You can show dialog with only positive button, only negative button, both buttons and no buttons at all. But if you show dialog  with no buttons make it cancellable or be sure that popup dismiss conditions well-implemented. You can set popup cancellability with `isCancellable` property. If you be aware of dialog dismiss event you must add a `dismissEvent` property before build `SapDialog` instance via `addDismissEvent(dismissEvent : () -> Unit)` or add a `cancelEvent` via `addCancelEvent(cancelEvent : () -> Unit)` method. Cancel event triggered when user clicks negative button or outside of the popup or back button if it is cancellable. Dismiss event triggered whenever popup becomes unvisible. This is especially useful when showing loading dialogs.

For adding buttons you must call `addPositiveButton(buttonText : String,clickEvent : () -> Unit)` and `addNegativeButton(buttonText : String,clickEvent : () -> Unit)` methods before build `SapDialog` instance. Example usage:

```kotlin
SapDialog(this).apply {
  titleText="Lorem ipsum"
  messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
  addPositiveButton("ok"){
    Toast.makeText(this@MainActivity, "positive button clicked", Toast.LENGTH_SHORT).show()
  }
  addNegativeButton("cancel"){
    Toast.makeText(this@MainActivity,"negative button clicked",Toast.LENGTH_SHORT).show()
  }
}.build().show()
```

This code shows a dialog with positive and negative buttons with click callbacks. 


		    
		    



