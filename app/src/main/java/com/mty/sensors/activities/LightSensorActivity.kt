package com.mty.sensors.activities

import android.widget.TextView
import com.mty.sensors.R
import com.mty.sensors.helpers.SensorConstants

class LightSensorActivity: BaseSensorActivity() {

    private var mTextLight: TextView? = null
    override fun getLayoutId(): Int = R.layout.activity_sensor_detail
    override fun getSensorType(): String {
        return SensorConstants.SENSOR_TYPE_LIGHT
    }

    override fun initLayout() {
        mTextLight = findViewById(R.id.sensor_values)
    }

    override fun handleSensorValue(sensorValue: Map<String, Float>?) {
        mTextLight?.text = resources.getString(R.string.illuminance, sensorValue?.get(
            SensorConstants.ILLUMINANCE))
    }

}
