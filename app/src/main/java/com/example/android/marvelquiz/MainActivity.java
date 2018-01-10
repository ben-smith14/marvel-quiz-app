package com.example.android.marvelquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onClick for the START button
    public void startQuiz(View view) {
        // Prevent the button from being clicked multiple times in quick succession
        Button button = findViewById(R.id.start_button);
        button.setEnabled(false);

        // Start the Questions activity
        Intent intent = new Intent(MainActivity.this, Questions.class);
        startActivity(intent);

        // Re-enable the button so that the user can start the quiz again once they return
        // to the home screen
        button.setEnabled(true);
    }
}
