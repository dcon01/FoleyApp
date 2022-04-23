package com.example.foleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPressed(View view) {
        String buttonText = ((Button) view).getText().toString();
        System.out.println(buttonText);
        Intent intent = new Intent(this, FoleyActivity.class);
        intent.putExtra("button", buttonText);
        startActivity(intent);
    }

    public enum SoundCategory {
        animal, human, nature, technology
    }
}