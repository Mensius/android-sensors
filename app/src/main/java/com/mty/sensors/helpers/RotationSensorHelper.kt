package com.mty.sensors.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent

class RotationSensorHelper(context: Context?) : BaseSensorHelper() {

    init {
        super.init(context, SENSOR_TYPE)
    }
    companion object {
        const val SENSOR_TYPE: Int = Sensor.TYPE_ROTATION_VECTOR
    }

    override fun handleSensorValueChanged(event: SensorEvent) {
        mSensorValueListener?.invoke(mapOf(
            SensorConstants.LINEAR_ACC_X to event.values[0],
            SensorConstants.LINEAR_ACC_Y to event.values[1],
            SensorConstants.LINEAR_ACC_Z to event.values[2]
        ))
    }

}