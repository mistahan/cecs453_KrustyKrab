package edu.csulb.android.decide;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;

public class Eight_Ball extends AppCompatActivity {
    TextView tv;
    String[] text;
    Typeface tf;
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eight_ball);

        tf = Typeface.createFromAsset(getAssets(), "CaptainAmerica.ttf");
        tv = (TextView) findViewById(R.id.textView2);
        text = getResources().getStringArray(R.array.answers);
        r = new Random();
        tv.setTypeface(tf);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(text[r.nextInt(20)]);
                tv.startAnimation(AnimationUtils.loadAnimation(Eight_Ball.this, R.anim.zoom_in));
            }
        });


    }
}
