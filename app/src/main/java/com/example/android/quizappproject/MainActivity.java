package com.example.android.quizappproject;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView quizName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizName = findViewById(R.id.quiz_name);

        //Makes the animation of quiz name
        ObjectAnimator anim = ObjectAnimator.ofFloat(quizName, "Alpha", 0, 1);
        anim.setDuration(5000);
        anim.start();

    }

    //This method leads to GreenEnergyQuiz class.
    public void green_energy_quiz(View view) {

        //Defines custom toast message
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        EditText nameField = findViewById(R.id.name_field);
        String nameData = nameField.getText().toString();
        //Play sound while pressing the button
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.strings);
        mp.start();
        if (nameData.equals("")) {

            //If no name was specified - toast message is being displayed.
            TextView text = layout.findViewById(R.id.text);
            text.setText(getString(R.string.namewarning));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        } else {

            Intent intent = new Intent(MainActivity.this, GreenEnergyQuiz.class);
            intent.putExtra(getString(R.string.namefield), nameData);
            startActivity(intent);
            finish();
        }
    }
}