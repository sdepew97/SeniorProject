package com.example.uiideasfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int currentCount = 0;
    MyDrawable[] stopMotionAnimation = new MyDrawable[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchingSorting();
        initGraph();
        initGreedy();
        initDynamicProgramming();

        stopMotionAnimation[0] = new MyDrawable(255, 0, 255);
        stopMotionAnimation[1] = new MyDrawable(0, 255, 255);

        Button nextFrameButton = findViewById(R.id.button);

        nextFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCount >= stopMotionAnimation.length) {
                    currentCount = 0;
                }

                ImageView image = findViewById(R.id.imageView);
                image.setImageDrawable(stopMotionAnimation[currentCount]);
                currentCount++;
            }
        });

    }

    private void initSearchingSorting() {

    }

    private void initGraph() {

    }

    private void initGreedy() {

    }

    private void initDynamicProgramming() {

    }
}
