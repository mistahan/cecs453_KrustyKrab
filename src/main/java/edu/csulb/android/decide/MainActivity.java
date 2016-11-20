package edu.csulb.android.decide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button thirdButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thirdButton = (Button) findViewById(R.id.button3);
    }

    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.button3:
                intent = new Intent(MainActivity.this, Eight_Ball.class);
                startActivity(intent);
        }
    }
}
