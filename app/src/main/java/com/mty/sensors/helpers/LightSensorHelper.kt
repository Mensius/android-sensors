package com.mty.sensors.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import com.mty.sensors.helpers.SensorConstants.ILLUMINANCE

class LightSensorHelper(context: Context?) : SensorHelper() {

    init {
        super.init(context, SENSOR_TYPE)
    }
    companion object {
        const val SENSOR_TYPE: Int = Sensor.TYPE_LIGHT
    }

    override fun handleSensorValueChanged(event: SensorEvent) {
        mSensorValueListener?.invoke(mapOf(ILLUMINANCE to event.values[0]))
    }

}
