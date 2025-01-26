package com.mty.sensors.helpers

import android.content.Context
import com.mty.sensors.helpers.SensorConstants.SENSOR_TYPE_LIGHT

object SensorHelperFactory {

    fun createSensorHelper(type: String, context: Context, listener: ISensorValueListener) : SensorHelper? {
        val sensorHelper = when (type) {
            SENSOR_TYPE_LIGHT -> LightSensorHelper(context)
            else -> null
        }
        return sensorHelper?.apply {
            setSensorValueListener(listener)
        }
    }

}