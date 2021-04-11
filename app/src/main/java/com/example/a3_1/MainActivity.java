package com.example.a3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText Name;
    TextView Title;
    Button StartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent Intent = getIntent();
        String userName = Intent.getStringExtra("userName");

        LinearLayout Layout = findViewById(R.id.MainMenu);
        Layout.setBackgroundColor(Color.WHITE);
        Name = findViewById(R.id.editTextPersonName);
        StartButton = findViewById(R.id.StartButton);

        Name.setText(userName);

        Title = findViewById(R.id.Title);
        Title.setText(R.string.QuizTitle);
        Title.setTextColor(Color.BLACK);

        Intent Intent1 = new Intent(MainActivity.this, MainActivity2.class);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name.getText() != null)
                {
                    Intent1.putExtra("userName", Name.getText().toString());
                    startActivity(Intent1);
                    finish();
                }
            }
        });
    }
}