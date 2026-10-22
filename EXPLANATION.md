# Activities and Intents

The application contains two Activities. MainActivity displays buttons for opening SecondActivity, opening a website, and showing a location. SecondActivity displays a message received from MainActivity and provides a return button.

An explicit Intent identifies the exact component to start. The first button creates `new Intent(MainActivity.this, SecondActivity.class)`. It attaches the text `Message sent from MainActivity` with `putExtra("MESSAGE", ...)`, then calls `startActivity(intent)`. SecondActivity retrieves the text using `getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE)` and displays it in a TextView. Its return button calls `finish()`, revealing the existing MainActivity.

An implicit Intent requests an action without naming a target component. The website button creates `new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.android.com"))`. Android finds an application that can view that URL, such as a browser. The optional location button uses the same action with a geographic URI. The app handles a missing compatible application with a Toast rather than crashing.

An explicit Intent is appropriate for launching a specific Activity inside the app because the developer knows its class and wants that particular screen. An implicit Intent is appropriate for opening a web page because the desired action is viewing a URL, and Android can choose a compatible installed browser without the app depending on a specific browser implementation.

Reference: [Android Developers — Intents and intent filters](https://developer.android.com/guide/components/intents-filters).
