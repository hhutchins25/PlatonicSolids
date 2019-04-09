// MainActivity.java
// Houses the starting activity, will provide
// an initial UI possibly for initiating renderer
// Holden Hutchins, 2019

package com.example.platonicsolids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openRenderer(View view) {
        Intent intent = new Intent(this, GLActivity.class);
        startActivity(intent);
    }
}
