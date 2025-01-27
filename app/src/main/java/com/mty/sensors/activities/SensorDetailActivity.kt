package com.mty.sensors.activities

import android.widget.TextView
import com.mty.sensors.R

class SensorDetailActivity: BaseSensorActivity() {

    private var mSensorName = "sensor_detail"
    private var mSensorValuesText: TextView? = null

    override fun getSensorType(): String {
        mSensorName = intent?.getStringExtra("sensorName") ?: ""
        return mSensorName
    }

    override fun getLayoutId(): Int = R.layout.activity_sensor_detail

    override fun initLayout() {
        mSensorValuesText = findViewById(R.id.sensor_values)
    }

    override fun handleSensorValue(sensorValue: Map<String, Float>?) {
        mSensorValuesText?.text = sensorValue?.let { formatSensorValue(it) }
    }

    private fun formatSensorValue(sensorValue: Map<String, Float>): String {
        return sensorValue.entries.joinToString("\n") {
            "${it.key} = ${it.value}"
        }
    }
}