package pl.edu.intentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/** Receives and displays the text supplied by MainActivity. */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        WindowInsetsHelper.apply(findViewById(R.id.second_content));

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView messageView = findViewById(R.id.message_text);
        messageView.setText(message != null ? message : getString(R.string.no_message));

        // Finish this Activity to reveal the existing MainActivity on the back stack.
        findViewById(R.id.return_button).setOnClickListener(view -> finish());
    }
}
