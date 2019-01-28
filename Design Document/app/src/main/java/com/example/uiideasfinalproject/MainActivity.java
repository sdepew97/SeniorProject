package com.example.uiideasfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int currentCount = 0;
    MyDrawable[] stopMotionAnimation = new MyDrawable[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initSearchingSorting();
        //initGraph();
        //initGreedy();
        //initDynamicProgramming();

        String[] numbers = new String[stopMotionAnimation.length-1];
        //TODO: get this info from the user screen and parse to make array...
        numbers[0] = "5";
        numbers[1] = "10";
        numbers[2] = "15";
        numbers[3] = "20";
        numbers[4] = "25";

        //TODO: get this info from the user and parse into array of "animation frames"
        Color main = new Color(255, 0, 255);
        Color secondary = new Color(255, 255, 0);
        Color found = new Color(255, 0, 0);

        int locationInArray = linearSearch(numbers, numbers[0]);
        int square = -1;

        //setup main frame git
        stopMotionAnimation[0] = new MyDrawable(main, secondary, found, square, false, numbers);
        square++;

        for(int i=1; i<stopMotionAnimation.length; i++) {
            stopMotionAnimation[i] = locationInArray == (i-1) ? new MyDrawable(main, secondary, found, square, true, numbers) : new MyDrawable(main, secondary, found, square, false, numbers);
            square++;
        }

        /*
        stopMotionAnimation[0] = new MyDrawable(main, secondary, found, -1, false, numbers);
        stopMotionAnimation[1] = new MyDrawable(main, secondary, found, 0, true, numbers);
        stopMotionAnimation[2] = new MyDrawable(main, secondary, found, 1, false, numbers);
        stopMotionAnimation[3] = new MyDrawable(main, secondary, found, 2, false, numbers);
        stopMotionAnimation[4] = new MyDrawable(main, secondary, found, 3, false, numbers);
        stopMotionAnimation[5] = new MyDrawable(main, secondary, found, 4, false, numbers);
        */

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

    private int linearSearch(String[] numbers, String target) {
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

}
