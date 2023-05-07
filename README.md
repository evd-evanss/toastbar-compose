# ToastBar - Jetpack Compose [![](https://jitpack.io/v/evd-evanss/toastbar-compose.svg)](https://jitpack.io/#evd-evanss/toastbar-compose)

A custom toastbar to display short messages to the user.

https://user-images.githubusercontent.com/26419059/236640309-4efd2058-b328-413a-a44e-c0789178e1d2.mp4

# Toast Levels

- Info:
  Low priority level, use to show informational messages

- Warning:
  Medium priority level, use to show warning messages

- Error:
  High priority level, use to show error messages

- Success:
  High priority level, use to show success messages

## Getting Started

Step 1. Include repository jitpack io in gradle (project).

``` kotlin
allprojects {
    repositories {
 	maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency in gradle (app).

``` kotlin
dependencies {
    implementation("com.github.evd-evanss:toastbar-compose:$latest_version")
}
```

### How to use the component?

```Kotlin
setContent {
    ToastBarTheme {
        val toastState = rememberToastState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Button(onClick = { toastState.display() }) {
                Text(text = "Show toast")
            }
        }
        ToastBar(
            toastState = toastState, // Handle toast visibility
            message = "Write here your message",
            toastLevel = ToastLevel.INFO, // ToastLevel.WARNING, ToastLevel.ERROR or ToastLevel.SUCCESS
            textStyle = MaterialTheme.typography.bodyMedium, // Override text style here
            iconStatusIsVisible = true, // Show or hide icon level in toast
            timerDuration = 2000L, // time in millis
            alignment = ToastAlignment.TOP, // or ToastAlignment.BOTTOM
            toastLevelColors = ToastLevelColors.colors(isDarkTheme = isSystemInDarkTheme()), // override colors here
            onDismiss = {} // Observe close event
        )
    }
}
```

|             Param             |                        Description                         |
|:-----------------------------:|:----------------------------------------------------------:|
|           modifier            |         Modifications of padding and positioning.          |
|          toastState           |       Manipulates the visibility state of the toast.       |
|            message            |                  Message to be displayed.                  |
|           textStyle           |          Text style to be applied to the message.          |
|         timerDuration         |       Length of time the message will remain active.       |
|      iconStatusIsVisible      |     Show or hide the status icon defined in toastLevel     |
|       toastLevelColors        |         Set background color according to levels.          |
|          toastLevel           | Toast message level, can be INFO, WARNING, ERROR, SUCCESS. |
|           alignment           | Determines where the message will be aligned. Top, Bottom  |
|           onDismiss           |                 Message closing listener.                  |
