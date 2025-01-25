package com.mty.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.core.util.Consumer

/**
 * Created by KeithXiaoY on 2017/5/31.
 *
 * 光线传感器的类型常量是Sensor.TYPE_LIGHT。values数组只有第一个元素（values[0]）有意义。表示光线的强度。最大的值是120000.0f
 * Android SDK将光线强度分为不同的等级，每一个等级的最大值由一个常量表示，这些常量都定义在SensorManager类中，代码如下：
 * public static final float LIGHT_SUNLIGHT_MAX =120000.0f;
 * public static final float LIGHT_SUNLIGHT=110000.0f;
 * public static final float LIGHT_SHADE=20000.0f;
 * public static final float LIGHT_OVERCAST= 10000.0f;
 * public static final float LIGHT_SUNRISE= 400.0f;
 * public static final float LIGHT_CLOUDY= 100.0f;
 * public static final float LIGHT_FULLMOON= 0.25f;
 * public static final float LIGHT_NO_MOON= 0.001f;
 * 上面的八个常量只是临界值，在实际使用光线传感器时要根据实际情况确定一个范围。
 * 例如，当太阳逐渐升起时，values[0] 的值很可能会超过 LIGHT_SUNRISE，
 * 当 values[0] 的值逐渐增大时，就会逐渐越过LIGHT_OVERCAST，而达到 LIGHT_SHADE。
 * 当然，如果天特别好的话，也可能会达到LIGHT_SUNLIGHT，甚至更高。
 */
class LightSensorUtils private constructor() {
    private lateinit var mSensorManager: SensorManager// 传感器管理器
    private var mIsContains = false
    private var mSensorValueListener: Consumer<Float>? = null


    //需要用光感器的地方在 init 初始化
    fun init(context: Context?) {
        mSensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //获取手机上支持的传感器
        mIsContains = mSensorManager.getSensorList(Sensor.TYPE_ALL) ?.any { it.type == Sensor.TYPE_LIGHT } ?: false
    }

    fun setSensorValueListener(consumer: Consumer<Float>?) {
        mSensorValueListener = consumer
    }

    /*    第三个参数是传感器数据更新数据的速度
          有以下四个值可选，他们的速度是递增的
          SENSOR_DELAY_UI
          SENSOR_DELAY_NORMAL
          SENSOR_DELAY_GAME
          SENSOR_DELAY_FASTEST*/
    fun registerSensor() {
        Log.d(TAG, "registerSensor: ")
        // 获得光线传感器
        val sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (sensor != null && mIsContains) {
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

    private val mSensorEventListener: SensorEventListener = object : SensorEventListener {
        /*  onSensorChanged()在传感器数值发生变化已经注册监听器时调用，其更新频率就是注册中的参数三。
        对于光传感器，有效数值存放在values[0]中的，单位为SI lux。
        光传感器通常位于上方（一般靠左侧）， 除了前置摄像头外还有一个孔，一般就是它。遮盖会触发onSensorChanged()  */
        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_LIGHT && event.values.isNotEmpty()) {
                Log.d(TAG, event.values[0].toString() + "")
                mSensorValueListener?.accept(event.values[0])
            }
        }

        /* onAccuracyChanged()会在精度改变或在注册监听器时调用。
        accuracy分为4档，0（unreliable），1（low），2（medium），3（high）。
        注意0并不代表有问题，同时是传感器需要校准。 */
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            Log.d(TAG, "onAccuracyChanged() called with: sensor = [$sensor], accuracy = [$accuracy]")
        }
    }

    companion object {
        private const val TAG = "LightSensorUtils"
        private val mLock = Any()
        var instance: LightSensorUtils? = null
            // 带异步保护的单例模式
            get() {
                if (field == null) {
                    synchronized(mLock) {
                        if (field == null) {
                            field = LightSensorUtils()
                        }
                    }
                }
                return field
            }
            private set
    }
}
