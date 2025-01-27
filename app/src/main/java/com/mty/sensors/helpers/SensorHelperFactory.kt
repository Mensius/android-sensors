package com.mty.sensors.helpers

import android.content.Context

object SensorHelperFactory {

    fun createSensorHelper(type: String, context: Context, listener: ISensorValueListener) : BaseSensorHelper? {
        val sensorHelper = when (type) {
            SensorConstants.SENSOR_TYPE_LIGHT -> LightSensorHelper(context)
            SensorConstants.SENSOR_TYPE_ACCEL -> AccelSensorHelper(context)
            SensorConstants.SENSOR_TYPE_MAGNETIC -> MagneticSensorHelper(context)
            SensorConstants.SENSOR_TYPE_GYRO -> GyroSensorHelper(context)
            SensorConstants.SENSOR_TYPE_PRESSURE -> PressureSensorHelper(context)
            SensorConstants.SENSOR_TYPE_PROXIMITY -> ProximitySensorHelper(context)
            SensorConstants.SENSOR_TYPE_GRAVITY -> GravitySensorHelper(context)
            SensorConstants.SENSOR_TYPE_LINEAR -> LinearSensorHelper(context)
            SensorConstants.SENSOR_TYPE_ROTATION -> RotationSensorHelper(context)
            SensorConstants.SENSOR_TYPE_HUMIDITY -> HumiditySensorHelper(context)
            SensorConstants.SENSOR_TYPE_TEMPERATURE -> TemperatureSensorHelper(context)
            SensorConstants.SENSOR_TYPE_STEP -> StepSensorHelper(context)
            else -> null
        }
        return sensorHelper?.apply {
            setSensorValueListener(listener)
        }
    }

}