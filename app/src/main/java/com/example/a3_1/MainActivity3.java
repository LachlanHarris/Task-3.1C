package com.example.a3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView Congratulations;
    TextView ScoreTitle;
    TextView Result;
    Button NewQuiz;
    Button Finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent IntentFinal = getIntent();
        String userName = IntentFinal.getStringExtra("userName");
        int QuestionsAnswered = IntentFinal.getIntExtra("QuestionsAnswered", 0);
        int QuestionsAnsweredCorrect= IntentFinal.getIntExtra("QuestionsAnsweredCorrect", 0);

        Intent IntentRestart = new Intent(MainActivity3.this, MainActivity.class);

        Congratulations = findViewById(R.id.Congratulations);
        ScoreTitle = findViewById(R.id.ScoreTitle);
        Result = findViewById(R.id.Result);
        NewQuiz = findViewById(R.id.NewQuiz);
        Finish = findViewById(R.id.Finish);

        Congratulations.setText("Congratulations " + userName + "!");
        ScoreTitle.setText("Your Score: ");
        Result.setText(QuestionsAnsweredCorrect + "/" + QuestionsAnswered);

        NewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentRestart.putExtra("userName",userName);
                startActivity(IntentRestart);
            }
        });

        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });




    }
}