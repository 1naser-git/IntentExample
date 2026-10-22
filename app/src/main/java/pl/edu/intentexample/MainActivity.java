package pl.edu.intentexample;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/** Demonstrates an explicit Intent and two implicit Intents. */
public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowInsetsHelper.apply(findViewById(R.id.main_content));

        findViewById(R.id.open_second_button).setOnClickListener(view -> {
            // An explicit Intent names the exact Activity to open.
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(EXTRA_MESSAGE, getString(R.string.message_to_send));
            startActivity(intent);
        });

        findViewById(R.id.open_website_button).setOnClickListener(view -> {
            // An implicit Intent specifies an action and URI, not a target class.
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.android.com"));
            openExternalActivity(intent, R.string.no_browser);
        });

        findViewById(R.id.show_location_button).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:50.3217,19.1949?q=50.3217,19.1949"));
            openExternalActivity(intent, R.string.no_maps);
        });
    }

    private void openExternalActivity(Intent intent, int unavailableMessage) {
        try {
            // Starting directly avoids package-visibility restrictions on queries.
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, unavailableMessage, Toast.LENGTH_LONG).show();
        }
    }
}
