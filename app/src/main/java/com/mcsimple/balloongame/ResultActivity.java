package com.mcsimple.balloongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int yourScore;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textViewInfo = findViewById(R.id.textViewInfo);
        TextView textViewYourScore = findViewById(R.id.textViewYourScore);
        TextView textViewHighest = findViewById(R.id.textViewYourHighest);
        Button buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        Button buttonQuitGame = findViewById(R.id.buttonQuitGame);

        yourScore = getIntent().getIntExtra("score",0);
        textViewYourScore.setText("Your Score: " + yourScore);

        sharedPreferences = this.getSharedPreferences("Score",MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore",0);

        if (yourScore >= highestScore){
            sharedPreferences.edit().putInt("highestScore",yourScore).apply();
            textViewHighest.setText("Highest Score :" + yourScore);
            textViewInfo.setText("Congratulations! The new highest score! Do you want to get better scores?");
        }
        else {
            textViewHighest.setText("Highest Score :" + highestScore);
            if ((highestScore - yourScore) < 10){
                textViewInfo.setText("You must get a little faster!");
            }
            else if ((highestScore - yourScore) >3 && (highestScore - yourScore) <= 10){
                textViewInfo.setText("Good. How about getting a little faster?");
            }
            else if ((highestScore - yourScore) <= 3){
                textViewInfo.setText("Excellent! If you get a little faster you can reach the high score!");
            }


        }

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonQuitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }
}