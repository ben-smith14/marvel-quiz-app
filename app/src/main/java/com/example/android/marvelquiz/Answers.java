package com.example.android.marvelquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Answers extends AppCompatActivity {

    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4P1;
    private TextView answer4P2;
    private TextView answer4P3;
    private TextView answer5;
    private TextView answer6;
    private TextView answer7;
    private TextView answer8;
    private TextView answer9;
    private TextView answer10P1;
    private TextView answer10P2;

    private ImageView aImg1;
    private ImageView aImg2;
    private ImageView aImg3;
    private ImageView aImg4;
    private ImageView aImg5;
    private ImageView aImg6;
    private ImageView aImg7;
    private ImageView aImg8;
    private ImageView aImg9;
    private ImageView aImg10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        // Initialise all of the used View objects
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4P1 = findViewById(R.id.answer4_part1);
        answer4P2 = findViewById(R.id.answer4_part2);
        answer4P3 = findViewById(R.id.answer4_part3);
        answer5 = findViewById(R.id.answer5);
        answer6 = findViewById(R.id.answer6);
        answer7 = findViewById(R.id.answer7);
        answer8 = findViewById(R.id.answer8);
        answer9 = findViewById(R.id.answer9);
        answer10P1 = findViewById(R.id.answer10_part1);
        answer10P2 = findViewById(R.id.answer10_part2);

        aImg1 = findViewById(R.id.answer_img1);
        aImg2 = findViewById(R.id.answer_img2);
        aImg3 = findViewById(R.id.answer_img3);
        aImg4 = findViewById(R.id.answer_img4);
        aImg5 = findViewById(R.id.answer_img5);
        aImg6 = findViewById(R.id.answer_img6);
        aImg7 = findViewById(R.id.answer_img7);
        aImg8 = findViewById(R.id.answer_img8);
        aImg9 = findViewById(R.id.answer_img9);
        aImg10 = findViewById(R.id.answer_img10);

        // Retrieve the boolean array again from the intent
        Intent intent = getIntent();
        boolean[] correctAnswers = intent.getBooleanArrayExtra("correctAnswers");

        // Set the colours of the answers and the image displayed after them based on the
        // contents of the correctAnswers array
        setAnswerStates(correctAnswers);
    }

    // onClick for the RETRY QUIZ button (same as for the Score activity)
    public void resetQuiz(View view) {
        Button button = findViewById(R.id.retry_button2);
        button.setEnabled(false);

        finish();
    }

    /**
     * setAnswerStates uses the correctAnswers array to change the colour of the text for each
     * question to indicate whether the user answered it correctly or not. It also displays a
     * tick or a cross at the end of the text to indicate this as well
     *
     * @param correctAnswers a boolean array identifying which questions the user correctly
     *                       answered (true is a correct answer and false is an incorrect answer)
     */
    private void setAnswerStates(boolean[] correctAnswers) {
        // Question 1:
        // Use the displayAnswerState method with the corresponding element in the correctAnswers
        // array and the relevant TextView/ImageView objects to change the text colour and set the
        // image source
        displayAnswerState(correctAnswers[0], answer1, aImg1);

        // Question 2
        displayAnswerState(correctAnswers[1], answer2, aImg2);

        // Question 3
        displayAnswerState(correctAnswers[2], answer3, aImg3);

        // Question 4:
        // Use the overloaded displayAnswerState method to achieve the same goal as the original
        // method, but using an ArrayList of TextView objects as an parameter instead of a singular
        // TextView
        ArrayList<TextView> allCheckBoxText = new ArrayList<>();
        allCheckBoxText.add(answer4P1);
        allCheckBoxText.add(answer4P2);
        allCheckBoxText.add(answer4P3);
        displayAnswerState(correctAnswers[3], allCheckBoxText, aImg4);

        // Question 5
        displayAnswerState(correctAnswers[4], answer5, aImg5);

        // Question 6
        displayAnswerState(correctAnswers[5], answer6, aImg6);

        // Question 7
        displayAnswerState(correctAnswers[6], answer7, aImg7);

        // Question 8
        displayAnswerState(correctAnswers[7], answer8, aImg8);

        // Question 9
        displayAnswerState(correctAnswers[8], answer9, aImg9);

        // Question 10:
        // Use the overloaded method once again, but use the same ArrayList as before so that we
        // do not have to create another one. Simply override the objects in the list and remove
        // any at an index that we no longer need
        allCheckBoxText.set(0, answer10P1);
        allCheckBoxText.set(1, answer10P2);
        allCheckBoxText.remove(2);
        displayAnswerState(correctAnswers[9], allCheckBoxText, aImg10);
    }

    /**
     * displayAnswerState sets the text colour for the given TextView and the source for the given
     * ImageView based on the answer state also passed to it
     *
     * @param answerState is a boolean that indicates whether the user answered a specific question
     *                    correctly (true value) or incorrectly (false value)
     * @param answerText  is the TextView object that displays the correct answer text
     * @param tickOrCross is the ImageView that displays a tick or a cross next to the text
     */
    public void displayAnswerState(boolean answerState, TextView answerText, ImageView tickOrCross) {
        if (answerState) {
            // If the user answered the given question correctly, set the text colour to green
            // and the image source to a tick
            answerText.setTextColor(getResources().getColor(R.color.correctGreen));
            tickOrCross.setImageResource(R.drawable.tick);
        } else {
            // If the user answered the given question incorrectly, set the text colour to red
            // and the image source to a cross
            answerText.setTextColor(getResources().getColor(R.color.incorrectRed));
            tickOrCross.setImageResource(R.drawable.cross);
        }
    }

    /**
     * displayAnswerState (overloaded) sets the text colour for the given set of TextViews and
     * the source for the given ImageView based on the answer state also passed to it
     *
     * @param answerState is a boolean that indicates whether the user answered a specific question
     *                    correctly (true value) or incorrectly (false value)
     * @param allText     is an ArrayList containing several TextViews that all display parts of the
     *                    correct answer
     * @param tickOrCross is the ImageView that displays a tick or a cross next to the text
     */
    public void displayAnswerState(boolean answerState, ArrayList<TextView> allText, ImageView tickOrCross) {
        // Carry out the same colour setting and image source setting as before, but set the
        // text colour of each TextView in the ArrayList to reflect the answer state
        if (answerState) {
            for (TextView answerText : allText) {
                answerText.setTextColor(getResources().getColor(R.color.correctGreen));
            }
            tickOrCross.setImageResource(R.drawable.tick);
        } else {
            for (TextView answerText : allText) {
                answerText.setTextColor(getResources().getColor(R.color.incorrectRed));
            }
            tickOrCross.setImageResource(R.drawable.cross);
        }
    }
}