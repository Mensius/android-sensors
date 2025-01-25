package com.mty.sensors;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LightSensorUtils mLightSensorUtils;

    TextView mTextLight;
    Resources mRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        mTextLight = findViewById(R.id.light_sensor_value);
        mRes = getResources();

        //初始化光线传感器
        mLightSensorUtils = LightSensorUtils.getInstance();
        mLightSensorUtils.init(getApplicationContext());
        mLightSensorUtils.setSensorValueListener(value -> {
            if (mTextLight != null) {
                mTextLight.setText(mRes.getString(R.string.lux, value));
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