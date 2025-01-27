package com.mty.sensors.helpers

import android.content.Context
import com.mty.sensors.helpers.SensorConstants

object SensorHelperFactory {

    fun createSensorHelper(type: String, context: Context, listener: ISensorValueListener) : SensorHelper? {
        val sensorHelper = when (type) {
            SensorConstants.SENSOR_TYPE_LIGHT -> LightSensorHelper(context)
            SensorConstants.SENSOR_TYPE_ACCEL -> AccelSensorHelper(context)
            else -> null
        }
        return sensorHelper?.apply {
            setSensorValueListener(listener)
        }
    }

}