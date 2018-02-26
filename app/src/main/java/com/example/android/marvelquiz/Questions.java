package com.example.android.marvelquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Arrays;

public class Questions extends AppCompatActivity {

    private RadioGroup question1;
    private EditText question2;
    private RadioGroup question3;
    private CheckBox question4A1;
    private CheckBox question4A2;
    private CheckBox question4A3;
    private CheckBox question4A4;
    private RadioGroup question5;
    private RadioGroup question6;
    private EditText question7;
    private RadioGroup question8;
    private RadioGroup question9;
    private CheckBox question10A1;
    private CheckBox question10A2;
    private CheckBox question10A3;
    private CheckBox question10A4;

    private boolean isKeyBoardOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // Initialise all of the used View objects
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4A1 = findViewById(R.id.question4_a1);
        question4A2 = findViewById(R.id.question4_a2);
        question4A3 = findViewById(R.id.question4_a3);
        question4A4 = findViewById(R.id.question4_a4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);
        question7 = findViewById(R.id.question7);
        question8 = findViewById(R.id.question8);
        question9 = findViewById(R.id.question9);
        question10A1 = findViewById(R.id.question10_a1);
        question10A2 = findViewById(R.id.question10_a2);
        question10A3 = findViewById(R.id.question10_a3);
        question10A4 = findViewById(R.id.question10_a4);

        // The code contained below is from the following link:
        // https://stackoverflow.com/questions/26858884/prevent-edittext-from-focussing-after-rotation
        // ---------------------------------------------------------------------------------------
        // It deals with managing the the keyboard and not focusing on EditText views when
        // transitioning between portrait -> landscape and vice versa
        if (savedInstanceState != null && savedInstanceState.getBoolean("isKeyBoardOpen", false)) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }

        final View activityRootView = findViewById(R.id.root_layout);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                // r will be populated with the coordinates of your view that are still visible
                activityRootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                // If more than 100 pixels, its probably a keyboard...
                if (heightDiff > 100) {
                    isKeyBoardOpen = true;
                } else {
                    isKeyBoardOpen = false;
                }
            }
        });
        // ---------------------------------------------------------------------------------------

        if (savedInstanceState != null) {
            // Retrieve the previous states of the controls before rotation of the screen and
            // set them back to how they were
            loadRadioState(question1, savedInstanceState.getString("question1"));
            question2.setText(savedInstanceState.getString("question2"));
            loadRadioState(question3, savedInstanceState.getString("question3"));

            boolean[] tempBoolArray = savedInstanceState.getBooleanArray("question4");
            if (tempBoolArray != null) {
                question4A1.setChecked(tempBoolArray[0]);
                question4A2.setChecked(tempBoolArray[1]);
                question4A3.setChecked(tempBoolArray[2]);
                question4A4.setChecked(tempBoolArray[3]);
            }

            loadRadioState(question5, savedInstanceState.getString("question5"));
            loadRadioState(question6, savedInstanceState.getString("question6"));
            question7.setText(savedInstanceState.getString("question7"));
            loadRadioState(question8, savedInstanceState.getString("question8"));
            loadRadioState(question9, savedInstanceState.getString("question9"));

            tempBoolArray = savedInstanceState.getBooleanArray("question10");
            if (tempBoolArray != null) {
                question10A1.setChecked(tempBoolArray[0]);
                question10A2.setChecked(tempBoolArray[1]);
                question10A3.setChecked(tempBoolArray[2]);
                question10A4.setChecked(tempBoolArray[3]);
            }
        }
    }

    /**
     * loadRadioState uses the ID name (i.e. the ID in String form) of a RadioButton within
     * a given RadioGroup to set that button as selected
     *
     * @param radioGroup   is the RadioGroup containing the RadioButtons
     * @param buttonIdName is the ID name (i.e. the ID in String form) of the RadioButton within
     *                     the RadioGroup that we want to select
     */
    private void loadRadioState(RadioGroup radioGroup, String buttonIdName) {
        if (buttonIdName != null) {
            int tempId = getResources().getIdentifier(buttonIdName, "id", getPackageName());
            if (tempId != 0) {
                radioGroup.check(tempId);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // If the app restarts due to a rotation of the screen, save the current state of the
        // RadioGroups, the EditText fields and the CheckBoxes to prevent progression loss
        savedInstanceState.putString("question1", getRadioName(question1));

        // Use the trim method to remove any leading or trailing whitespace from the String
        savedInstanceState.putString("question2", question2.getText().toString().trim());
        savedInstanceState.putString("question3", getRadioName(question3));

        boolean[] tempBoolArray = new boolean[]{
                question4A1.isChecked(), question4A2.isChecked(),
                question4A3.isChecked(), question4A4.isChecked()
        };
        savedInstanceState.putBooleanArray("question4", tempBoolArray);

        savedInstanceState.putString("question5", getRadioName(question5));
        savedInstanceState.putString("question6", getRadioName(question6));
        savedInstanceState.putString("question7", question7.getText().toString().trim());
        savedInstanceState.putString("question8", getRadioName(question8));
        savedInstanceState.putString("question9", getRadioName(question9));

        tempBoolArray = new boolean[]{
                question10A1.isChecked(), question10A2.isChecked(),
                question10A3.isChecked(), question10A4.isChecked()
        };
        savedInstanceState.putBooleanArray("question10", tempBoolArray);

        savedInstanceState.putBoolean("isKeyboardOpen", isKeyBoardOpen);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * getRadioName identifies if a button has been selected within a given RadioGroup and then
     * returns the ID name (i.e. the ID in String form) of this button if one has been selected
     *
     * @param radioGroup is the RadioGroup containing the RadioButtons
     * @return the ID name (i.e. the ID in String form) of the selected button. Return null if
     * a button has not been selected
     */
    private String getRadioName(RadioGroup radioGroup) {
        int tempId = radioGroup.getCheckedRadioButtonId();
        if (tempId != -1) {
            return getResources().getResourceEntryName(tempId);
        } else {
            return null;
        }
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
        correctAnswers[0] = checkRadioGroupAnswer(question1, getString(R.string.Q1A2));

        // Question 2:
        // As there are two possible answers, use a logical OR between a check for each one
        correctAnswers[1] = checkEditTextAnswer(question2, getString(R.string.Q2_answer1)) || checkEditTextAnswer(question2, getString(R.string.Q2_answer2));

        // Question 3
        correctAnswers[2] = checkRadioGroupAnswer(question3, getString(R.string.Q3A3));

        // Question 4:
        // Obtain the checked state of each of the boxes in the question and compare them to
        // the correct combination of checked boxes
        boolean[] actualChecked = new boolean[]{
                question4A1.isChecked(), question4A2.isChecked(),
                question4A3.isChecked(), question4A4.isChecked()
        };
        correctAnswers[3] = checkCheckBoxesAnswer(actualChecked, new boolean[]{true, true, true, false});

        // Question 5
        correctAnswers[4] = checkRadioGroupAnswer(question5, getString(R.string.Q5A1));

        // Question 6
        correctAnswers[5] = checkRadioGroupAnswer(question6, getString(R.string.Q6A4));

        // Question 7
        correctAnswers[6] = checkEditTextAnswer(question7, getString(R.string.Q7_answer));

        // Question 8
        correctAnswers[7] = checkRadioGroupAnswer(question8, getString(R.string.Q8A2));

        // Question 9
        correctAnswers[8] = checkRadioGroupAnswer(question9, getString(R.string.Q9A3));

        // Question 10
        actualChecked = new boolean[]{
                question10A1.isChecked(), question10A2.isChecked(),
                question10A3.isChecked(), question10A4.isChecked()
        };
        correctAnswers[9] = checkCheckBoxesAnswer(actualChecked, new boolean[]{true, false, false, true});

        // Create an alert dialog box to check that the user wants to move on from the questions
        final AlertDialog alertDialog = new AlertDialog.Builder(Questions.this).create();
        alertDialog.setMessage(getString(R.string.dialog_finish_quiz));

        // Add a positive button to continue onto the Score activity and a negative button to
        // dismiss the dialog box and stay on the current activity
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dialog_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Start the Score activity and pass it the record of correct answers
                        Intent intent = new Intent(alertDialog.getContext(), Score.class);
                        intent.putExtra("correctAnswers", correctAnswers);
                        finish();
                        startActivity(intent);
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.dialog_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Use dismiss() to cancel the alert dialog and re-enable the button
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
    private boolean checkRadioGroupAnswer(RadioGroup radioGroup, String correctButtonText) {
        // Include a case for if no radio buttons have been selected and return false
        int selectedButton = radioGroup.getCheckedRadioButtonId();
        if (selectedButton == -1) {
            return false;
        } else {
            // If a radio button has been selected, retrieve its ID and then retrieve the actual
            // button object using this ID so that we can then retrieve its associated text
            RadioButton radioButton = radioGroup.findViewById(selectedButton);
            String radioButtonText = radioButton.getText().toString();

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
    private boolean checkEditTextAnswer(EditText editText, String correctInputText) {
        // Retrieve the text input into the view by the user
        String inputText = editText.getText().toString().trim();

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
    private boolean checkCheckBoxesAnswer(boolean[] actualChecked, boolean[] correctChecked) {
        // Use the Arrays class to identify if the two boolean arrays are identical or not
        return Arrays.equals(actualChecked, correctChecked);
    }
}