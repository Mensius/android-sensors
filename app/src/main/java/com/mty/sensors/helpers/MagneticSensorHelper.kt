package com.mty.sensors.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent

class MagneticSensorHelper(context: Context?) : BaseSensorHelper() {

    init {
        super.init(context, SENSOR_TYPE)
    }
    companion object {
        const val SENSOR_TYPE: Int = Sensor.TYPE_MAGNETIC_FIELD
    }

    override fun handleSensorValueChanged(event: SensorEvent) {
        mSensorValueListener?.invoke(mapOf(
            SensorConstants.MAG_X to event.values[0],
            SensorConstants.MAG_Y to event.values[1],
            SensorConstants.MAG_Z to event.values[2]
        ))
    }

}