package com.example.a3_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    TextView WelcomeMessage;
    TextView Progress;
    TextView QuestionTitle;
    TextView Question;
    Button Answer1;
    Button Answer2;
    Button Answer3;
    Button NextButton;
    String AnswerChoice;
    String userName;
    ProgressBar ProgressBar;

    int indexOfAnswer;
    int QuestionsAnswered;
    int QuestionsAnsweredCorrect;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent Intent2 = getIntent();
        userName = Intent2.getStringExtra("userName");

        QuestionsAnswered = Intent2.getIntExtra("QuestionsAnswered", 0);
        QuestionsAnsweredCorrect= Intent2.getIntExtra("QuestionsAnsweredCorrect", 0);
        //this intent throws back to this activity if there are less than 5 questions answered
        Intent Intent3 = new Intent(MainActivity2.this, MainActivity2.class);
        //otherwise this intent throws to the final screen
        Intent Intent4 = new Intent(MainActivity2.this, MainActivity3.class);

        ProgressBar = findViewById(R.id.progressBar);
        Progress = findViewById(R.id.progress);
        QuestionTitle = findViewById(R.id.QuestionTitle);
        Question = findViewById(R.id.Question);
        Answer1 = findViewById(R.id.Answer1);
        Answer2 = findViewById(R.id.Answer2);
        Answer3 = findViewById(R.id.Answer3);
        NextButton = findViewById(R.id.NextButton);

        WelcomeMessage = findViewById(R.id.WelcomeMessage);

        Progress.setText((QuestionsAnswered+1)+"/"+"5");

        ProgressBar.setProgress((QuestionsAnswered+1)*20, true);

        //creating lists for each array so i can call the index of method
        List<String> CorrectAnswersList = Arrays.asList("LinearLayout","fill_parent","Python","Broken","Spinner");
        List<String> Q1answerslist = Arrays.asList("LinearLayout", "Button", "TextView");
        List<String> Q2answerslist = Arrays.asList("fill_parent", "wrap_content", "an exact number");
        List<String> Q3answerslist = Arrays.asList("Python", "java", "Kotlin");
        List<String> Q4answerslist = Arrays.asList("Broken", "Foreground", "Destroyed");
        List<String> Q5answerslist = Arrays.asList("Spinner", "Image View", "Progress bar");

        //this section sets the question and answer buttons dependant on how many questions have been asked
        //as well we shuffle the array before we display it in order to have a different orientation of quiz answers every time we do the quiz
        switch (QuestionsAnswered) {
            case 0:
                WelcomeMessage.setText("Welcome " + userName + "!");
                Collections.shuffle(Q1answerslist);
                Question.setText(R.string.question1);
                QuestionTitle.setText(R.string.question1title);
                Answer1.setText(Q1answerslist.get(0));
                Answer2.setText(Q1answerslist.get(1));
                Answer3.setText(Q1answerslist.get(2));
                break;
            case 1:
                Collections.shuffle(Q2answerslist);
                Question.setText(R.string.question2);
                QuestionTitle.setText(R.string.question2title);
                Answer1.setText(Q2answerslist.get(0));
                Answer2.setText(Q2answerslist.get(1));
                Answer3.setText(Q2answerslist.get(2));
                break;
            case 2:
                Collections.shuffle(Q3answerslist);
                Question.setText(R.string.question3);
                QuestionTitle.setText(R.string.question3title);
                Answer1.setText(Q3answerslist.get(0));
                Answer2.setText(Q3answerslist.get(1));
                Answer3.setText(Q3answerslist.get(2));
                break;
            case 3:
                Collections.shuffle(Q4answerslist);
                Question.setText(R.string.question4);
                QuestionTitle.setText(R.string.question4title);
                Answer1.setText(Q4answerslist.get(0));
                Answer2.setText(Q4answerslist.get(1));
                Answer3.setText(Q4answerslist.get(2));
                break;
            case 4:
                Collections.shuffle(Q5answerslist);
                Question.setText(R.string.question5);
                QuestionTitle.setText(R.string.question5title);
                Answer1.setText(Q5answerslist.get(0));
                Answer2.setText(Q5answerslist.get(1));
                Answer3.setText(Q5answerslist.get(2));
                break;
        }

        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerChoice = Answer1.getText().toString();
            }
        });
        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerChoice = Answer2.getText().toString();
            }
        });
        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerChoice = Answer3.getText().toString();
            }
        });
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerChoice == null)
                {
                    Toast.makeText(MainActivity2.this,"Please select an answer before submission",Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (QuestionsAnswered)
                {
                    case 0: indexOfAnswer = Q1answerslist.indexOf(AnswerChoice);
                    break;
                    case 1: indexOfAnswer = Q2answerslist.indexOf(AnswerChoice);
                    break;
                    case 2: indexOfAnswer = Q3answerslist.indexOf(AnswerChoice);
                    break;
                    case 3: indexOfAnswer = Q4answerslist.indexOf(AnswerChoice);
                    break;
                    case 4: indexOfAnswer = Q5answerslist.indexOf(AnswerChoice);
                    break;
                }
                if (CorrectAnswersList.contains(AnswerChoice))
                {
                    QuestionsAnsweredCorrect += 1;
                    switch (indexOfAnswer)
                    {
                        case 0: Answer1.setBackgroundColor(Color.GREEN);
                        break;
                        case 1: Answer2.setBackgroundColor(Color.GREEN);
                        break;
                        case 2: Answer3.setBackgroundColor(Color.GREEN);
                        break;
                    }
                }
                else
                {
                    switch (indexOfAnswer)
                    {
                        case 0: Answer1.setBackgroundColor(Color.RED);
                            if(CorrectAnswersList.contains(Answer2))
                            {
                                Answer2.setBackgroundColor(Color.GREEN);
                            }
                            else {
                                Answer3.setBackgroundColor(Color.GREEN);
                            }
                            break;
                        case 1: Answer2.setBackgroundColor(Color.RED);
                            if(CorrectAnswersList.contains(Answer1))
                            {
                                Answer1.setBackgroundColor(Color.GREEN);
                            }
                            else {
                                Answer3.setBackgroundColor(Color.GREEN);
                            }
                            break;
                        case 2: Answer3.setBackgroundColor(Color.RED);
                            if(CorrectAnswersList.contains(Answer2))
                            {
                                Answer2.setBackgroundColor(Color.GREEN);
                            }
                            else {
                                Answer1.setBackgroundColor(Color.GREEN);
                            }
                            break;
                    }
                }
                QuestionsAnswered += 1;
                Intent3.putExtra("QuestionsAnswered",QuestionsAnswered);
                Intent3.putExtra("QuestionsAnsweredCorrect",QuestionsAnsweredCorrect);
                Intent3.putExtra("userName",userName);
                if (QuestionsAnswered > 4)
                {
                    Intent4.putExtra("QuestionsAnswered",QuestionsAnswered);
                    Intent4.putExtra("QuestionsAnsweredCorrect",QuestionsAnsweredCorrect);
                    Intent4.putExtra("userName",userName);
                    startActivity(Intent4);
                    finish();
                }
                else {
                    startActivity(Intent3);
                    finish();
                }
            }
        });
    }
}