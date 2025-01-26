package com.mty.sensors

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mty.sensors.helpers.SensorConstants
import com.mty.sensors.helpers.SensorConstants.SENSOR_TYPE_LIGHT
import com.mty.sensors.helpers.SensorHelper
import com.mty.sensors.helpers.SensorHelperFactory

class MainActivity : AppCompatActivity() {
    private var mLightSensorUtils: SensorHelper? = null

    private var mTextLight: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        mTextLight = findViewById(R.id.light_sensor_value)

        //初始化光线传感器
        mLightSensorUtils = SensorHelperFactory.createSensorHelper(SENSOR_TYPE_LIGHT, this@MainActivity) {
            sensorValue: Map<String, Float>? -> mTextLight?.text = resources.getString(
                R.string.illuminance, sensorValue?.get(SensorConstants.ILLUMINANCE)
            )
        }
    }

    public override fun onResume() {
        super.onResume()
        mLightSensorUtils?.registerSensor()
    }

    public override fun onPause() {
        super.onPause()
        mLightSensorUtils?.unRegisterSensor()
    }
}