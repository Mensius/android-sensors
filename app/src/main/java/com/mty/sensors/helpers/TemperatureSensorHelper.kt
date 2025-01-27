package com.mty.sensors.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent

class TemperatureSensorHelper(context: Context?) : BaseSensorHelper() {

    init {
        super.init(context, SENSOR_TYPE)
    }
    companion object {
        const val SENSOR_TYPE: Int = Sensor.TYPE_AMBIENT_TEMPERATURE
    }

    override fun handleSensorValueChanged(event: SensorEvent) {
        mSensorValueListener?.invoke(mapOf(
            SensorConstants.TEMPERATURE to event.values[0]
        ))
    }

}