package com.example.android.marvelquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Arrays;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
    }

    // onClick for the FINISH button
    public void finishQuiz(View view) {
        // Prevent the button from being clicked multiple times in quick succession again
        final Button button = findViewById(R.id.finish_button);
        button.setEnabled(false);

        // Use a boolean array to record whether each question was answered correctly or not.
        // True represents a correct answer and false represents an incorrect answer
        final boolean[] correctAnswers = new boolean[10];

        // Question 1:
        // Use the relevant method to check that the correct radio button is selected
        correctAnswers[0] = checkRadioGroupAnswer((RadioGroup) findViewById(R.id.question1), getString(R.string.Q1A2));

        // Question 2:
        // Again, use the relevant method to check that the input text is correct. As there
        // are two possible answers, use a logical OR between a check for each one
        correctAnswers[1] = checkEditTextAnswer((EditText) findViewById(R.id.question2), getString(R.string.Q2_answer1))
                || checkEditTextAnswer((EditText) findViewById(R.id.question2), getString(R.string.Q2_answer2));

        // Question 3
        correctAnswers[2] = checkRadioGroupAnswer((RadioGroup) findViewById(R.id.question3), getString(R.string.Q3A3));

        // Question 4:
        // Obtain the checked state of each of the boxes in the question and compare them to
        // the correct combination of checked boxes
        boolean[] actualChecked = new boolean[]{
                ((CheckBox) findViewById(R.id.question4_a1)).isChecked(),
                ((CheckBox) findViewById(R.id.question4_a2)).isChecked(),
                ((CheckBox) findViewById(R.id.question4_a3)).isChecked(),
                ((CheckBox) findViewById(R.id.question4_a4)).isChecked()};
        correctAnswers[3] = checkCheckBoxesAnswer(actualChecked, new boolean[]{true, true, true, false});

        // Question 5
        correctAnswers[4] = checkRadioGroupAnswer((RadioGroup) findViewById(R.id.question5), getString(R.string.Q5A1));

        // Question 6
        correctAnswers[5] = checkRadioGroupAnswer((RadioGroup) findViewById(R.id.question6), getString(R.string.Q6A4));

        // Question 7
        correctAnswers[6] = checkEditTextAnswer((EditText) findViewById(R.id.question7), getString(R.string.Q7_answer));

        // Question 8
        correctAnswers[7] = checkRadioGroupAnswer((RadioGroup) findViewById(R.id.question8), getString(R.string.Q8A2));

        // Question 9
        correctAnswers[8] = checkRadioGroupAnswer((RadioGroup) findViewById(R.id.question9), getString(R.string.Q9A3));

        // Question 10
        actualChecked = new boolean[]{
                ((CheckBox) findViewById(R.id.question10_a1)).isChecked(),
                ((CheckBox) findViewById(R.id.question10_a2)).isChecked(),
                ((CheckBox) findViewById(R.id.question10_a3)).isChecked(),
                ((CheckBox) findViewById(R.id.question10_a4)).isChecked()};
        correctAnswers[9] = checkCheckBoxesAnswer(actualChecked, new boolean[]{true, false, false, true});

        // Create an alert dialog box to check that the user wants to move on from the questions
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(R.string.dialog_finish_quiz));

        // Add a positive button to continue onto the Score activity and a negative button to
        // dismiss the dialog box and stay on the current activity
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dialog_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // start the Score activity and pass it the record of correct answers
                        Intent intent = new Intent(alertDialog.getContext(), Score.class);
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
     * checkRadioGroupAnswer identifies if the correct radio button in a radio group has been
     * selected based on an input String which should match the correct radio button's text
     *
     * @param radioGroup        is the radio group object to be checked
     * @param correctButtonText is a String that matches the text of the correct radio button
     * @return a boolean indicating if the selected answer was correct or not
     */
    public boolean checkRadioGroupAnswer(RadioGroup radioGroup, String correctButtonText) {
        // Include a case for if no radio buttons have been selected and return false
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            return false;
        } else {
            // If a radio button has been selected, retrieve its ID and then retrieve the actual
            // button object using this ID so that we can then retrieve its associated text
            int selectedButton = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = radioGroup.findViewById(selectedButton);
            String radioButtonText = (String) radioButton.getText();

            // Check to see if the selected radio button text matches the correct answer text
            return radioButtonText.equals(correctButtonText);
        }
    }

    /**
     * checkEditTextAnswer identifies if the correct text has been input into a EditText view
     * by checking it against a correct String
     *
     * @param editText         is the edit text object to be checked
     * @param correctInputText is a String that identifies the correct answer
     * @return a boolean indicating if the input answer was correct or not
     */
    public boolean checkEditTextAnswer(EditText editText, String correctInputText) {
        // Retrieve the text input into the view by the user
        String inputText = editText.getText().toString();

        // Check that the user has input any text first and, if they have, check that it matches
        // the correct answer String, ignoring case
        return !inputText.equals("") && inputText.equalsIgnoreCase(correctInputText);
    }

    /**
     * checkCheckBoxesAnswer identifies if the correct combination of checkboxes in a set has
     * been selected using boolean arrays
     *
     * @param actualChecked  is a boolean array containing values that indicate whether each
     *                       checkbox in a set have been checked or not (true means it has and
     *                       false means it hasn't)
     * @param correctChecked is a boolean array containing values that indicate the correct
     *                       combination of selected and unselected checkboxes (true means a
     *                       box should be selected and false means a box shouldn't be selected)
     * @return a boolean indicating if the combination of selected boxes was correct or not
     */
    public boolean checkCheckBoxesAnswer(boolean[] actualChecked, boolean[] correctChecked) {
        // Use the Arrays class to identify if the two boolean arrays are identical or not
        return Arrays.equals(actualChecked, correctChecked);
    }
}