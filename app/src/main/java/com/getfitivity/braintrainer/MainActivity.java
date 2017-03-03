package com.getfitivity.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int lacationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions=0;

        timerTextView.setText("30s");
        pointsTextView.setText(("0/0"));
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);

        generateQuestion();

        countDownTimer();

    }

    public void countDownTimer(){
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: " + Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));

            }
        }.start();


    }

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" +Integer.toString(b));

        int incorrectAnswer;

        lacationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for(int i = 0; i<4;i++){
            if(i == lacationOfCorrectAnswer){
                answers.add(a+b);
            }else{

                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer== a+b){

                    incorrectAnswer= rand.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }
        }

        button.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }


    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(lacationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong!");

        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start (View view){

        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(playAgainButton);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
         sumTextView = (TextView)findViewById(R.id.sumTextView);
         button = (Button)findViewById(R.id.button);
         button2 = (Button)findViewById(R.id.button2);
         button3 = (Button)findViewById(R.id.button3);
         button4 = (Button)findViewById(R.id.button4);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);


        startButton = (Button)findViewById(R.id.startButton);







    }
}
