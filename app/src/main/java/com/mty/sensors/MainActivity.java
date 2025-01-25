package com.mty.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SensorManager mSensorManager;
    private LightSensorUtils mLightSensorUtils;

    TextView textLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textLight = findViewById(R.id.light_sensor_value);

        //初始化光线传感器
        mLightSensorUtils = LightSensorUtils.getInstance();
        mLightSensorUtils.init(getApplicationContext());
        mLightSensorUtils.setSensorValueListener(aFloat -> {
            if (textLight != null) {
                textLight.setText("Lux: " + String.valueOf(aFloat));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mLightSensorUtils.registerSensor();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLightSensorUtils.unRegisterSensor();
    }
}