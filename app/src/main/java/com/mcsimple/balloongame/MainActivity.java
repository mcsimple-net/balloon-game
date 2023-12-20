package com.mcsimple.balloongame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    int score = 0;
    private TextView textViewScore;
    private TextView textViewTime;
    private TextView textViewCountDown;
    Runnable runnable;
    Handler handler;
    ImageView[] balloonsArray = new ImageView[9];
    private ImageView balloon1,balloon2,balloon3,balloon4,balloon5,balloon6,balloon7,balloon8,balloon9;
    MediaPlayer mediaPlayer;
    boolean volStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTime = findViewById(R.id.textViewTime);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        textViewScore = findViewById(R.id.textViewScore);
        balloon1 = findViewById(R.id.balloon1);
        balloon2 = findViewById(R.id.balloon2);
        balloon3 = findViewById(R.id.balloon3);
        balloon4 = findViewById(R.id.balloon4);
        balloon5 = findViewById(R.id.balloon5);
        balloon6 = findViewById(R.id.balloon6);
        balloon7 = findViewById(R.id.balloon7);
        balloon8 = findViewById(R.id.balloon8);
        balloon9 = findViewById(R.id.balloon9);
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.INVISIBLE);
        textViewTime.setVisibility(View.INVISIBLE);
        textViewScore.setVisibility(View.INVISIBLE);

        mediaPlayer = MediaPlayer.create(this,R.raw.balloon_pop);

        balloonsArray[0] = balloon1;
        balloonsArray[1] = balloon2;
        balloonsArray[2] = balloon3;
        balloonsArray[3] = balloon4;
        balloonsArray[4] = balloon5;
        balloonsArray[5] = balloon6;
        balloonsArray[6] = balloon7;
        balloonsArray[7] = balloon8;
        balloonsArray[8] = balloon9;

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewCountDown.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                balloonsControl();

                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        textViewTime.setText("Remaining time: " + millisUntilFinished/1000);


                    }

                    @Override
                    public void onFinish() {

                        Intent i = new Intent(MainActivity.this,ResultActivity.class);
                        i.putExtra("score",score);
                        startActivity(i);
                        finish();

                    }
                }.start();

            }
        }.start();

    }

    public void increaseScoreByOne(View view) {

    score++;
    textViewScore.setText("Score: " + score);

    if (mediaPlayer.isPlaying()){
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
    mediaPlayer.start();

    if (view.getId() == balloon1.getId()){
        balloon1.setImageResource(R.drawable.boom);
    }
    if (view.getId() == balloon2.getId()){
        balloon2.setImageResource(R.drawable.boom);
        }
    if (view.getId() == balloon3.getId()){
        balloon3.setImageResource(R.drawable.boom);
        }
    if (view.getId() == balloon4.getId()){
        balloon4.setImageResource(R.drawable.boom);
        }
    if (view.getId() == balloon5.getId()){
        balloon5.setImageResource(R.drawable.boom);
        }
    if (view.getId() == balloon6.getId()){
        balloon6.setImageResource(R.drawable.boom);
        }
    if (view.getId() == balloon7.getId()){
        balloon7.setImageResource(R.drawable.boom);
        }
    if (view.getId() == balloon8.getId()){
        balloon8.setImageResource(R.drawable.boom);
        }
    if (view.getId() == balloon9.getId()){
        balloon9.setImageResource(R.drawable.boom);
        }

    }

    public void balloonsControl(){
        textViewCountDown.setVisibility(View.INVISIBLE);
        textViewTime.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView balloon : balloonsArray){
                    balloon.setVisibility(View.INVISIBLE);
                    balloon.setImageResource(R.drawable.balloon);
                }
                gridLayout.setVisibility(View.VISIBLE);
                Random random = new Random();
                int i = random.nextInt(balloonsArray.length);
                balloonsArray[i].setVisibility(View.VISIBLE);


                if (score <= 5) {
                    handler.postDelayed(runnable, 2000);
                }
                else if (score > 5 && score <= 10) {
                    handler.postDelayed(runnable, 1500);
                }
                else if (score > 10 && score <= 15) {
                    handler.postDelayed(runnable, 1000);
                }
                else if (score > 15) {
                    handler.postDelayed(runnable, 500);
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.volume){

            if (!volStatus){
                mediaPlayer.setVolume(0,0);
                item.setIcon(R.drawable.volume_off);
                volStatus = true;
            }
            else
            {
                mediaPlayer.setVolume(1,1);
                item.setIcon(R.drawable.volume_on);
                volStatus = false;
            }

        }

        return super.onOptionsItemSelected(item);
    }
}