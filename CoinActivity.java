package kevin.coin;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class CoinActivity extends Activity implements SensorEventListener {

    ImageView coin_image;
    Random rand;
    int coinSide;
    private SensorManager sSensorManager;
    private Sensor sAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    //shake threshold is the sensitivity of the shake
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        sSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sAccelerometer = sSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sSensorManager.registerListener(this, sAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        coin_image = (ImageView) findViewById(R.id.iv_coin);

    }

    @Override
    public void onSensorChanged(SensorEvent event){

        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long time = System.currentTimeMillis();

            //change this to adjust how often we can shake
            if ((time - lastUpdate) > 100) {
                long diffTime = (time - lastUpdate);
                lastUpdate = time;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    flip();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    protected void onPause() {
        super.onPause();
        sSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        sSensorManager.registerListener(this, sAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void flip(){
        rand = new Random();

        coinSide = rand.nextInt(2);

        if(coinSide == 0){
            coin_image.setImageResource(R.drawable.heads);
            Toast.makeText(CoinActivity.this, "Heads", Toast.LENGTH_SHORT).show();
        }
        else if(coinSide == 1){
            coin_image.setImageResource(R.drawable.tails);
            Toast.makeText(CoinActivity.this, "Tails", Toast.LENGTH_SHORT).show();
        }
    }


}
