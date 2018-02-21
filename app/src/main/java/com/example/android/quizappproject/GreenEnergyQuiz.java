package com.example.android.quizappproject;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class GreenEnergyQuiz extends MainActivity {

    //Declaration of values used in onSaveInstanceState and onRestoreInstanceState methods
    private static final String KEY_QUESTION_NO = "LevelQuestionNo";
    private static final String KEY_SCORE = "LevelScoreValue";
    private static final String KEY_NEXT_BUTTON_VISIBILITY = "nextButtonVisibility";
    private static final String KEY_RESET_BUTTON_VISIBILITY = "resetButtonVisibility";
    private static final String KEY_SUBMIT_BUTTON_VISIBILITY = "submitButtonVisibility";
    private static final String KEY_CHECKMARK_VISIBILITY = "checkmarkVisibility";
    private static final String KEY_EXCLAMATION_MARK_VISIBILITY = "exclamationMarkVisibility";
    private static final String KEY_CORRECT_VISIBILITY = "correctVisibility";
    private static final String KEY_WRONG_VISIBILITY = "wrongVisibility";
    private static final String KEY_CHECK_BOX1_STATE = "checkBox1state";
    private static final String KEY_CHECK_BOX2_STATE = "checkBox2state";
    private static final String KEY_CHECK_BOX3_STATE = "checkBox3state";
    private static final String KEY_CHECK_BOX4_STATE = "checkBox4state";
    private static final String KEY_RADIO_BUTTON1_STATE = "radioButton1state";
    private static final String KEY_RADIO_BUTTON2_STATE = "radioButton2state";
    private static final String KEY_RADIO_BUTTON3_STATE = "radioButton3state";
    private static final String KEY_RADIO_BUTTON4_STATE = "radioButton4state";

    //Delclaration of array containing questions numbers and related values:
    //1.: question's image
    //2.: question
    //3.: answer 1
    //4.: answer 2
    //5.: answer 3
    //6.: answer 4
    //7.: correct answer
    String[][] doubleArray = new String[11][7];
    //First value of questions numbering
    private int questionNo = 1;
    //Initial value of score numbering
    private int score = 0;
    private TextView questionInfo;
    private TextView quizQuestion;
    private ImageView backgroundImage;
    private ImageView checkmark;
    private ImageView exclamationMark;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private CheckBox checkbox4;
    private TextView textquestion1;
    private TextView textquestion2;
    private TextView textquestion3;
    private TextView textquestion4;
    private TextView correct;
    private TextView wrong;
    private EditText textviewedit;
    private ImageButton nextButton;
    private ImageButton resetButton;
    private ImageButton submitButton;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_energy_main);

        //Initialization of main view components
        viewComponents();
        viewHiddenComponents();
        setQuestion();
        setListeners();

    }

    /**
     * Saves the current state to avoid data loss in case of screen rotation.
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(KEY_QUESTION_NO, questionNo);
        savedInstanceState.putInt(KEY_SCORE, score);
        savedInstanceState.putBoolean(KEY_CHECK_BOX1_STATE, checkbox1.isEnabled());
        savedInstanceState.putBoolean(KEY_CHECK_BOX2_STATE, checkbox2.isEnabled());
        savedInstanceState.putBoolean(KEY_CHECK_BOX3_STATE, checkbox3.isEnabled());
        savedInstanceState.putBoolean(KEY_CHECK_BOX4_STATE, checkbox4.isEnabled());
        savedInstanceState.putBoolean(KEY_RADIO_BUTTON1_STATE, rb1.isEnabled());
        savedInstanceState.putBoolean(KEY_RADIO_BUTTON2_STATE, rb2.isEnabled());
        savedInstanceState.putBoolean(KEY_RADIO_BUTTON3_STATE, rb3.isEnabled());
        savedInstanceState.putBoolean(KEY_RADIO_BUTTON4_STATE, rb4.isEnabled());

        if (nextButton != null) {
            savedInstanceState.putInt(KEY_NEXT_BUTTON_VISIBILITY, nextButton.getVisibility());
        }

        if (resetButton != null) {
            savedInstanceState.putInt(KEY_RESET_BUTTON_VISIBILITY, resetButton.getVisibility());
        }

        if (submitButton != null) {
            savedInstanceState.putInt(KEY_SUBMIT_BUTTON_VISIBILITY, submitButton.getVisibility());
        }

        if (checkmark != null) {
            savedInstanceState.putInt(KEY_CHECKMARK_VISIBILITY, checkmark.getVisibility());
        }

        if (exclamationMark != null) {
            savedInstanceState.putInt(KEY_EXCLAMATION_MARK_VISIBILITY, exclamationMark.getVisibility());
        }

        if (correct != null) {
            savedInstanceState.putInt(KEY_CORRECT_VISIBILITY, correct.getVisibility());
        }

        if (wrong != null) {
            savedInstanceState.putInt(KEY_WRONG_VISIBILITY, wrong.getVisibility());
        }
    }

    /**
     * Restores the current state in case of screen rotation.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        questionNo = savedInstanceState.getInt(KEY_QUESTION_NO);
        score = savedInstanceState.getInt(KEY_SCORE);
        checkbox1.setEnabled(savedInstanceState.getBoolean(KEY_CHECK_BOX1_STATE));
        checkbox2.setEnabled(savedInstanceState.getBoolean(KEY_CHECK_BOX2_STATE));
        checkbox3.setEnabled(savedInstanceState.getBoolean(KEY_CHECK_BOX3_STATE));
        checkbox4.setEnabled(savedInstanceState.getBoolean(KEY_CHECK_BOX4_STATE));
        rb1.setEnabled(savedInstanceState.getBoolean(KEY_RADIO_BUTTON1_STATE));
        rb2.setEnabled(savedInstanceState.getBoolean(KEY_RADIO_BUTTON2_STATE));
        rb3.setEnabled(savedInstanceState.getBoolean(KEY_RADIO_BUTTON3_STATE));
        rb4.setEnabled(savedInstanceState.getBoolean(KEY_RADIO_BUTTON4_STATE));
        nextButton.setVisibility(savedInstanceState.getInt(KEY_NEXT_BUTTON_VISIBILITY, nextButton.getVisibility()));
        resetButton.setVisibility(savedInstanceState.getInt(KEY_RESET_BUTTON_VISIBILITY, resetButton.getVisibility()));
        submitButton.setVisibility(savedInstanceState.getInt(KEY_SUBMIT_BUTTON_VISIBILITY, submitButton.getVisibility()));
        checkmark.setVisibility(savedInstanceState.getInt(KEY_CHECKMARK_VISIBILITY, checkmark.getVisibility()));
        exclamationMark.setVisibility(savedInstanceState.getInt(KEY_EXCLAMATION_MARK_VISIBILITY, exclamationMark.getVisibility()));
        correct.setVisibility(savedInstanceState.getInt(KEY_CORRECT_VISIBILITY, correct.getVisibility()));
        wrong.setVisibility(savedInstanceState.getInt(KEY_WRONG_VISIBILITY, wrong.getVisibility()));

        setQuestion();

    }

    //This method determines the path to hidden components.
    public void viewHiddenComponents() {

        correct = findViewById(R.id.correct);
        correct.setVisibility(View.INVISIBLE);
        wrong = findViewById(R.id.wrong);
        wrong.setVisibility(View.INVISIBLE);

        backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.setVisibility(View.INVISIBLE);
        checkmark = findViewById(R.id.checkmark);
        checkmark.setVisibility(View.INVISIBLE);
        exclamationMark = findViewById(R.id.exclamationMark);
        exclamationMark.setVisibility(View.INVISIBLE);
        nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setVisibility(View.INVISIBLE);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setVisibility(View.INVISIBLE);

    }

    //This method determines the path of main scene components.
    public void viewComponents() {

        questionInfo = findViewById(R.id.questionInfo);
        quizQuestion = findViewById(R.id.quizQuestion);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        checkbox4 = findViewById(R.id.checkbox4);

        textquestion1 = findViewById(R.id.textquestion1);
        textquestion2 = findViewById(R.id.textquestion2);
        textquestion3 = findViewById(R.id.textquestion3);
        textquestion4 = findViewById(R.id.textquestion4);

        textviewedit = findViewById(R.id.textviewedit);

        //Array gets the data from arrays.xml file in res/values folder.
        doubleArray[1] = getResources().getStringArray(R.array.Question1);
        doubleArray[2] = getResources().getStringArray(R.array.Question2);
        doubleArray[3] = getResources().getStringArray(R.array.Question3);
        doubleArray[4] = getResources().getStringArray(R.array.Question4);
        doubleArray[5] = getResources().getStringArray(R.array.Question5);
        doubleArray[6] = getResources().getStringArray(R.array.Question6);
        doubleArray[7] = getResources().getStringArray(R.array.Question7);
        doubleArray[8] = getResources().getStringArray(R.array.Question8);
        doubleArray[9] = getResources().getStringArray(R.array.Question9);
        doubleArray[10] = getResources().getStringArray(R.array.Question10);

    }

    //This method sets the listeners for EditText and RadioButtons and also determines behavior of question image.
    public void setListeners() {

        // Initialize Radio Group and attach click handler.
        rg = findViewById(R.id.rg);

        // Attach CheckedChangeListener to radio group.
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    onClickCheck();
                    rg.clearCheck();
                }
            }
        });

        //Displays question's image with delay of 1s.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Show image after 1000ms
                backgroundImage.setVisibility(View.VISIBLE);
            }
        }, 1000);

        // Attach CheckedChangeListener to EditText.
        textviewedit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    setTextViewEdit();
                }
                return false;
            }
        });
    }

    //This method sets the behavior of the next button and view for each question.
    @TargetApi(Build.VERSION_CODES.O)
    public void setNext_button(View view) {

        //It works until the last question
        if (questionNo != 10) {
            questionNo += 1;

            //Settings of visibility for components below.
            findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
            findViewById(R.id.checkmark).setVisibility(View.INVISIBLE);
            findViewById(R.id.exclamationMark).setVisibility(View.INVISIBLE);
            findViewById(R.id.wrong).setVisibility(View.INVISIBLE);
            findViewById(R.id.correct).setVisibility(View.INVISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Show question's image after 1000ms
                    backgroundImage.setVisibility(View.VISIBLE);
                }
            }, 1000);
            //After passing to the next question - it's image stays invisible for 1s.
            findViewById(R.id.backgroundImage).setVisibility(View.INVISIBLE);
            //Resetting color and function of RadioButtons.
            rb1.setTextColor(getResources().getColor(R.color.black));
            rb1.setEnabled(true);
            rb2.setTextColor(getResources().getColor(R.color.black));
            rb2.setEnabled(true);
            rb3.setTextColor(getResources().getColor(R.color.black));
            rb3.setEnabled(true);
            rb4.setTextColor(getResources().getColor(R.color.black));
            rb4.setEnabled(true);
            //Sets the next question's view.
            setQuestion();
        }
    }

    //This method set the next question's view.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setQuestion() {

        questionInfo.setText((getString(R.string.question_no) + questionNo));
        quizQuestion.setText(doubleArray[questionNo][1]);
        setRadio_group_main();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Show image after 1000ms
                setBackground_image();
            }
        }, 1000);
    }

    //This method gets the path of the image as a string.
    public void setBackground_image() {

        String uri = getString(R.string.drawable) + doubleArray[questionNo][0];
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        backgroundImage.setImageResource(imageResource);
    }

    //This method set the behavior of RadioButtons and CheckBoxes.
    public void setRadio_group_main() {

        //RadioButtons are visible until the last question.
        if (questionNo <= 8) {

            rb1.setText(doubleArray[questionNo][2]);
            rb2.setText(doubleArray[questionNo][3]);
            rb3.setText(doubleArray[questionNo][4]);
            rb4.setText(doubleArray[questionNo][5]);

        } else {

            rg.setVisibility(View.GONE);

        }

        if (questionNo == 9) {

            textviewedit.setVisibility(View.VISIBLE);
            textquestion1.setText(doubleArray[questionNo][2]);
            textquestion2.setText(doubleArray[questionNo][3]);
            textquestion3.setText(doubleArray[questionNo][4]);
            textquestion4.setText(doubleArray[questionNo][5]);

        } else {

            textviewedit.setVisibility(View.INVISIBLE);

        }

        if (questionNo == 10) {

            textviewedit.setVisibility(View.INVISIBLE);
            textquestion1.setVisibility(View.GONE);
            textquestion2.setVisibility(View.GONE);
            textquestion3.setVisibility(View.GONE);
            textquestion4.setVisibility(View.GONE);

            //Instead of that CheckBoxes are visible now.
            checkbox1.setVisibility(View.VISIBLE);
            checkbox2.setVisibility(View.VISIBLE);
            checkbox3.setVisibility(View.VISIBLE);
            checkbox4.setVisibility(View.VISIBLE);
            checkbox1.setText(doubleArray[questionNo][2]);
            checkbox2.setText(doubleArray[questionNo][3]);
            checkbox3.setText(doubleArray[questionNo][4]);
            checkbox4.setText(doubleArray[questionNo][5]);

        } else {
            checkbox1.setVisibility(View.INVISIBLE);
            checkbox2.setVisibility(View.INVISIBLE);
            checkbox3.setVisibility(View.INVISIBLE);
            checkbox4.setVisibility(View.INVISIBLE);
        }
    }

    //This method checks the answers and sets the behavior of RadioButtons.
    public void onClickCheck() {

        //The answer which was chosen.
        String chosenAnswer;

        //Transfer the chosen answer into the string.
        RadioButton radioButton;
        rg = findViewById(R.id.rg);
        int selectedId = rg.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);
        chosenAnswer = radioButton.getText().toString();

        //Defines custom toast message
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        //Compares the chosen answer with correct answers from the last cell of array.
        if (doubleArray[questionNo][6].equals(chosenAnswer)) {

            if (rb1.isChecked()) {
                rb1.setTextColor(getResources().getColor(R.color.green));
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb3.setEnabled(false);
                rb4.setEnabled(false);
            }

            if (rb2.isChecked()) {
                rb2.setTextColor(getResources().getColor(R.color.green));
                rb2.setEnabled(false);
                rb1.setEnabled(false);
                rb3.setEnabled(false);
                rb4.setEnabled(false);
            }

            if (rb3.isChecked()) {
                rb3.setTextColor(getResources().getColor(R.color.green));
                rb3.setEnabled(false);
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb4.setEnabled(false);
            }

            if (rb4.isChecked()) {
                rb4.setTextColor(getResources().getColor(R.color.green));
                rb4.setEnabled(false);
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb3.setEnabled(false);
            }

            score += 1;

            //If the answer is equal with the answer from the last cell of the array - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText((getString(R.string.yes) + doubleArray[questionNo][6]));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            correctAnswerSubmitted();

        } else {

            if (rb1.isChecked()) {
                rb1.setTextColor(getResources().getColor(R.color.red));
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb3.setEnabled(false);
                rb4.setEnabled(false);
            }

            if (rb2.isChecked()) {
                rb2.setTextColor(getResources().getColor(R.color.red));
                rb2.setEnabled(false);
                rb1.setEnabled(false);
                rb3.setEnabled(false);
                rb4.setEnabled(false);
            }

            if (rb3.isChecked()) {
                rb3.setTextColor(getResources().getColor(R.color.red));
                rb3.setEnabled(false);
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb4.setEnabled(false);
            }

            if (rb4.isChecked()) {
                rb4.setTextColor(getResources().getColor(R.color.red));
                rb4.setEnabled(false);
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb3.setEnabled(false);
            }

            //If the answer is not the same as the answer from the last cell of the array - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText((getString(R.string.no) + doubleArray[questionNo][6]));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            wrongAnswerSubmitted();
        }
    }

    //This method checks the CheckBoxes and sets their behavior.
    public void checkboxOnClick(View view) {

        //Defines custom toast message
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        if (checkbox1.isChecked()) {

            checkbox1.setTextColor(getResources().getColor(R.color.red));
            checkbox1.setEnabled(false);
            checkbox2.setEnabled(false);
            checkbox3.setEnabled(false);
            checkbox4.setEnabled(false);

            //If the answer is not the same as the answer from the last cell of the array - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText((getString(R.string.no) + doubleArray[questionNo][6]));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            wrongAnswerSubmitted();
        }

        if (checkbox2.isChecked()) {

            checkbox2.setTextColor(getResources().getColor(R.color.red));
            checkbox2.setEnabled(false);
            checkbox1.setEnabled(false);
            checkbox3.setEnabled(false);
            checkbox4.setEnabled(false);

            //If the answer is not the same as the answer from the last cell of the array - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText((getString(R.string.no) + doubleArray[questionNo][6]));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            wrongAnswerSubmitted();
        }

        if (checkbox3.isChecked()) {

            checkbox3.setTextColor(getResources().getColor(R.color.green));
            checkbox3.setEnabled(false);
            checkbox1.setEnabled(false);
            checkbox2.setEnabled(false);
            checkbox4.setEnabled(false);

            //If the answer is equal with the answer from the last cell of the array - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText((getString(R.string.yes) + doubleArray[questionNo][6]));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            correctAnswerSubmitted();

            score += 1;
        }

        if (checkbox4.isChecked()) {

            checkbox4.setTextColor(getResources().getColor(R.color.red));
            checkbox4.setEnabled(false);
            checkbox1.setEnabled(false);
            checkbox2.setEnabled(false);
            checkbox3.setEnabled(false);

            //If the answer is not the same as the answer from the last cell of the array - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText((getString(R.string.no) + doubleArray[questionNo][6]));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            wrongAnswerSubmitted();
        }
    }

    //This method checks the answer for Edittext field.
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setTextViewEdit() {

        EditText textViewEdit = findViewById(R.id.textviewedit);
        String editAnswer = textViewEdit.getText().toString();

        //Defines custom toast message
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));


        if (editAnswer.equals(getString(R.string.green_q9_1))) {

            textquestion1.setTextColor(getResources().getColor(R.color.green));

            //If the answer is equal with the answer from the last cell of the array - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText((getString(R.string.yes) + doubleArray[questionNo][6]));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            correctAnswerSubmitted();

            score += 1;

        } else {

            if (editAnswer.equals(getString(R.string.green_q9_2))) {

                textquestion2.setTextColor(getResources().getColor(R.color.red));

                //If the answer is not the same as the answer from the last cell of the array - toast message is being displayed.
                TextView text = layout.findViewById(R.id.text);
                text.setText((getString(R.string.no) + doubleArray[questionNo][6]));

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

                wrongAnswerSubmitted();
            }

            if (editAnswer.equals(getString(R.string.green_q9_3))) {

                textquestion3.setTextColor(getResources().getColor(R.color.red));

                //If the answer is not the same as the answer from the last cell of the array - toast message is being displayed.
                TextView text = layout.findViewById(R.id.text);
                text.setText((getString(R.string.no) + doubleArray[questionNo][6]));

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

                wrongAnswerSubmitted();
            }

            if (editAnswer.equals(getString(R.string.green_q9_4))) {

                textquestion4.setTextColor(getResources().getColor(R.color.red));

                //If the answer is not the same as the answer from the last cell of the array - toast message is being displayed.
                TextView text = layout.findViewById(R.id.text);
                text.setText((getString(R.string.no) + doubleArray[questionNo][6]));

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

                wrongAnswerSubmitted();
            }
        }
    }

    //This method set the behavior of additional components and bottom buttons after the correct answer.
    public void correctAnswerSubmitted() {

        //Sets the animation for components
        findViewById(R.id.checkmark).setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 2000, 0);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (questionNo != 10) {

                    //Next button becomes visible nntil the last question.
                    findViewById(R.id.nextButton).setVisibility(View.VISIBLE);

                } else {

                    //In the last question submit and reset buttons become visible.
                    findViewById(R.id.submitButton).setVisibility(View.VISIBLE);
                    findViewById(R.id.resetButton).setVisibility(View.VISIBLE);
                }

                findViewById(R.id.correct).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do nothing
            }
        });
        findViewById(R.id.exclamationMark).setVisibility(View.INVISIBLE);
        findViewById(R.id.wrong).setVisibility(View.INVISIBLE);
        findViewById(R.id.checkmark).startAnimation(animation);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct_answer);
        mp.start();

    }

    //This method set the behavior of additional components and bottom buttons after the wrong answer.
    public void wrongAnswerSubmitted() {

        findViewById(R.id.exclamationMark).setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 2000, 0);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (questionNo != 10) {

                    //Next button becomes visible nntil the last question.
                    findViewById(R.id.nextButton).setVisibility(View.VISIBLE);

                } else {

                    //In the last question submit and reset buttons become visible.
                    findViewById(R.id.submitButton).setVisibility(View.VISIBLE);
                    findViewById(R.id.resetButton).setVisibility(View.VISIBLE);

                }

                findViewById(R.id.wrong).setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do nothing
            }
        });
        findViewById(R.id.checkmark).setVisibility(View.INVISIBLE);
        findViewById(R.id.correct).setVisibility(View.INVISIBLE);
        findViewById(R.id.exclamationMark).startAnimation(animation);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong_answer);
        mp.start();

    }

    //this method set the behavior of submit button.
    public void submit(View view) {

        //Pass the name from MainActivity class.
        String nameData = getIntent().getStringExtra(getString(R.string.namefield));

        //Build the message and title for Alert Dialog box.
        String finalMessage = finalmessage(score);
        String title = (nameData + getString(R.string.congratulations));

        //Plays the sound for Alert Dialog box.
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.french_horns);
        mp.start();

        //Components of Alert Dialog box.
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_box);

        TextView titlebox = dialog.findViewById(R.id.text_title);
        titlebox.setText(Html.fromHtml(title));

        TextView text = dialog.findViewById(R.id.text_dialog);
        text.setText(Html.fromHtml(finalMessage));

        Button dialogButton = dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    //This method sets the final message accordingly to gained points.
    private String finalmessage(int score) {

        String message;

        if (score < 5) {

            message = getString(R.string.message1) + score + getString(R.string.message2);
            message += getString(R.string.message3);
            message += getString(R.string.message4);
            message += getString(R.string.message5);
            message += getString(R.string.message6);
            message += getString(R.string.message8);
            return message;

        } else {

            message = getString(R.string.message1) + score + getString(R.string.message2);
            message += getString(R.string.message9);
            message += getString(R.string.message10);
            message += getString(R.string.message11);
            message += getString(R.string.message12);
            message += getString(R.string.message8);
            return message;
        }
    }

    //This method brings back the view and settings to initial screen and values.
    public void resetButton(View view) {

        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        if (i != null) {
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(i);
    }
}