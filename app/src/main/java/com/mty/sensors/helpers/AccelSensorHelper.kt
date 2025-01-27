package com.mty.sensors.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent

class AccelSensorHelper(context: Context?) : SensorHelper() {

    init {
        super.init(context, SENSOR_TYPE)
    }
    companion object {
        const val SENSOR_TYPE: Int = Sensor.TYPE_ACCELEROMETER
    }

    override fun handleSensorValueChanged(event: SensorEvent) {
        mSensorValueListener?.invoke(mapOf(
            SensorConstants.ACC_X to event.values[0],
            SensorConstants.ACC_Y to event.values[1],
            SensorConstants.ACC_Z to event.values[2]
        ))
    }

}