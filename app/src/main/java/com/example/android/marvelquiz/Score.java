package com.example.android.marvelquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Score extends AppCompatActivity {

    private boolean[] correctAnswers = new boolean[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Retrieve the correctAnswers array from the intent
        Intent intent = getIntent();
        correctAnswers = intent.getBooleanArrayExtra("correctAnswers");

        // Set the TextView displaying the score to the calculated value found using the
        // boolean array
        TextView scoreText = findViewById(R.id.user_score);
        int score = calculateScore(correctAnswers);
        scoreText.setText(String.valueOf(score));

        // Display a Toast message that differs depending on the user's score
        if (score >= 7) {
            Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
        } else if (score >= 4) {
            Toast.makeText(getApplicationContext(), "Nice Try!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unlucky!", Toast.LENGTH_SHORT).show();
        }
    }

    // onClick for the RETRY QUIZ button
    public void resetQuiz(View view) {
        // Prevent the button from being clicked multiple times in quick succession again
        Button button = findViewById(R.id.retry_button1);
        button.setEnabled(false);

        // Finish the current activity so that the user's view returns to the paused main activity
        finish();
    }

    // onClick for the SEE ANSWERS button
    public void seeAnswers(View view) {
        // Prevent the button from being clicked on multiple times in quick succession again
        final Button button = findViewById(R.id.answers_button);
        button.setEnabled(false);

        // Create an alert dialog box to check that the user wants to see the answers
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(R.string.dialog_see_answers));

        // Add a positive button to continue onto the Answers activity and a negative button to
        // dismiss the dialog box and stay on the current activity
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dialog_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Start the Answers activity and pass it the correctAnswers array
                        Intent intent = new Intent(alertDialog.getContext(), Answers.class);
                        intent.putExtra("correctAnswers", correctAnswers);
                        finish();
                        startActivity(intent);
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.dialog_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // use dismiss() to cancel the alert dialog and re-enable the button
                        dialog.dismiss();
                        button.setEnabled(true);
                    }
                });

        alertDialog.show();
    }

    /**
     * calculateScore uses the boolean array passed from the Questions activity to calculate
     * the user's score
     *
     * @param correctAnswers a boolean array identifying which questions the user correctly
     *                       answered (true is a correct answer and false is an incorrect answer)
     * @return an int representing the user's final score
     */
    private int calculateScore(boolean[] correctAnswers) {
        int score = 0;

        // For each element in the boolean array, add one to the score if it equals true
        for (boolean current : correctAnswers) {
            if (current) {
                score += 1;
            }
        }

        return score;
    }
}