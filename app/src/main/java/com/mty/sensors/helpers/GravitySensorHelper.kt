package com.mty.sensors.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent

class GravitySensorHelper (context: Context?) : BaseSensorHelper() {

    init {
        super.init(context, SENSOR_TYPE)
    }
    companion object {
        const val SENSOR_TYPE: Int = Sensor.TYPE_GRAVITY
    }

    override fun handleSensorValueChanged(event: SensorEvent) {
        mSensorValueListener?.invoke(mapOf(
            SensorConstants.GRAV_X to event.values[0],
            SensorConstants.GRAV_Y to event.values[1],
            SensorConstants.GRAV_Z to event.values[2]
        ))
    }

}