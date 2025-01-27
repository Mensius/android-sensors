package com.mty.sensors.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.mty.sensors.helpers.SensorConstants.NO_SENSOR

abstract class SensorHelper {

    private lateinit var mSensorManager: SensorManager
    private var mHasSensor = false
    protected var mSensorValueListener: ISensorValueListener? = null
    private var mSensorType = NO_SENSOR

    fun init(context: Context?, sensorType: Int = NO_SENSOR) {
        mSensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensorType = sensorType
        mHasSensor = mSensorManager.getDefaultSensor(mSensorType) != null
    }

    fun setSensorValueListener(listener: ISensorValueListener) {
        mSensorValueListener = listener
    }

    fun registerSensor() {
        Log.d(TAG, "registerSensor: ")
        val sensor = mSensorManager.getDefaultSensor(mSensorType)
        if (sensor != null && mHasSensor) {
            mSensorManager.registerListener(
                mSensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } else {
            Log.w(TAG, "registerSensor: sensor not available")
        }
    }

    fun unRegisterSensor() {
        Log.d(TAG, "unRegisterSensor: ")
        mSensorManager.unregisterListener(mSensorEventListener)
    }

    protected open fun handleSensorValueChanged(event: SensorEvent) {
        mSensorValueListener?.invoke(mapOf("value" to event.values[0]))
    }

    private val mSensorEventListener: SensorEventListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == mSensorType && event.values.isNotEmpty()) {
                Log.d(TAG, event.values[0].toString() + "")
                handleSensorValueChanged(event)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            Log.d(TAG, "onAccuracyChanged() called with: sensor = [$sensor], accuracy = [$accuracy]")
        }
    }

    companion object {
        private const val TAG = "SensorHelper"
    }

}