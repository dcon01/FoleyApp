package com.example.foleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

public class FoleyActivity extends AppCompatActivity {
    private String userChoice = "";
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foley);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userChoice = extras.getString("button");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float height = this.getWindow().getDecorView().getHeight();
        float width = this.getWindow().getDecorView().getWidth();
        float heightMiddle = height / 2;
        float widthMiddle = width / 2;
        float x = event.getX();
        float y = event.getY();
//        String action = "";
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                action = "started";
//                break;
//            case MotionEvent.ACTION_MOVE:
//                action = "moved";
//                break;
//            case MotionEvent.ACTION_UP:
//                action = "ended";
//                break;
//        }
        if ((x < widthMiddle) & (y < heightMiddle)) {
            position = 1; //TopLeft
        } else if ((x > widthMiddle) & (y > heightMiddle)) {
            position = 4; //bottomRight
        } else if ((x > widthMiddle) & (y < heightMiddle)) {
            position = 2; //TopRight
        } else if ((x < widthMiddle) & (y > heightMiddle)) {
            position = 3; //bottomLeft
        }
        return true;
    }

    public int getUserChoice() {
        switch (userChoice) {
            case "animal":
                return 1;
            case "human":
                return 2;
            case "technology":
                return 3;
            default:
                return 4;
        }
    }
}