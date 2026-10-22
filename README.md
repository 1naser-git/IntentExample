# IntentExample

A Java Android application for the laboratory assignment **First Android Application – Activities and Intents**.

## Open and run

1. Open the `IntentExample` folder in Android Studio.
2. Allow Gradle sync to finish. The first sync requires an internet connection.
3. If asked, select your Android SDK location. On the prepared PC it is `D:\Android\Sdk`.
4. Use Android Studio's bundled JDK for Gradle.
5. Select the `app` run configuration and the `IntentLab_Pixel` emulator, or an Android phone with USB debugging enabled.
6. Click **Run**.

The working copy on the prepared PC is `D:\Android\Projects\IntentExample`. Build caches and emulator files are stored on D:.

## What the app does

- **Open second activity** launches `SecondActivity` with an explicit Intent and sends `Message sent from MainActivity` in the `MESSAGE` extra.
- **Return to main activity** calls `finish()` and reveals the original `MainActivity`.
- **Open website** uses an implicit `ACTION_VIEW` Intent for `https://www.android.com`.
- **Show location** implements the optional extension with an implicit `ACTION_VIEW` Intent for `geo:50.3217,19.1949?q=50.3217,19.1949`.

If no browser or maps app can handle an implicit Intent, a Toast explains the missing app. Opening a website is delegated to the browser, so this app itself does not need the INTERNET permission. System-bar insets are handled so the content remains readable on Android 16.

## Source files

- `app/src/main/java/pl/edu/intentexample/MainActivity.java`
- `app/src/main/java/pl/edu/intentexample/SecondActivity.java`
- `app/src/main/java/pl/edu/intentexample/WindowInsetsHelper.java`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/activity_second.xml`
- `app/src/main/AndroidManifest.xml`

## Build settings

| Setting | Value |
|---|---|
| Language | Java |
| Package | `pl.edu.intentexample` |
| Minimum SDK | 24 (Android 7.0) |
| Compile and target SDK | 36 (Android 16) |
| Android Gradle plugin | 9.1.1 |
| Gradle wrapper | 9.3.1 |
| Java source compatibility | 17 |

Build and check from the project directory:

```text
Windows:       gradlew.bat assembleDebug lintDebug
macOS/Linux:   sh gradlew assembleDebug lintDebug
```

The debug APK is generated in `app/build/outputs/apk/debug/app-debug.apk`.

## Explanation and control question

An **explicit Intent** names the component that should handle it. This app uses `new Intent(MainActivity.this, SecondActivity.class)` because it knows the exact Activity inside its own application. `putExtra("MESSAGE", ...)` attaches text to that Intent, and `getStringExtra("MESSAGE")` retrieves the text in the second Activity.

An **implicit Intent** describes an action and data without naming a component. This app uses `new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.android.com"))`. Android resolves the request to a compatible browser, using a default handler or presenting a choice when appropriate.

An explicit Intent is appropriate for opening a specific internal Activity because the app controls and knows its target. An implicit Intent is appropriate for a web page because the app needs the action of viewing a URL and can let Android select an installed browser. It does not need to hard-code a browser's package or Activity class.

Official reference: https://developer.android.com/guide/components/intents-filters

## Verification and screenshots

The final app was built with `assembleDebug` and checked with `lintDebug` on 22 October 2026. Both completed successfully; Android Lint reported **No issues found**. Runtime testing used the Android 16 / API 36 Pixel emulator. Older supported Android versions were not tested on a device.

| Check | Result | Evidence |
|---|---|---|
| Main screen opens and all three buttons are available | Passed | `screenshots/01-main.png` |
| Explicit Intent opens SecondActivity and passes the MESSAGE extra | Passed | `screenshots/02-explicit-intent-message.png` |
| Return button restores the original MainActivity | Passed | Activity back-stack inspection and UI check |
| Website button uses ACTION_VIEW and opens android.com in Chrome | Passed | `screenshots/03-implicit-intent-website.png` |
| Optional location button sends the geographic URI to Google Maps | Passed | `screenshots/04-optional-location.png` |

Screenshots were captured from the running emulator. Chrome and Maps were used without signing in to an account.

## Submission

Share a link to this complete project using a cloud repository or a file-sharing service accepted by the instructor. Include the two main Java files, both layout files, screenshots of the working app and browser, and the explanation above. Keep `local.properties`, build outputs, `.gradle`, and `.idea` out of a source repository. They contain local settings or generated files and are unnecessary for opening the project on another computer.
