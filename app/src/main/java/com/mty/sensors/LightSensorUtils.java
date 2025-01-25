package com.mty.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.core.util.Consumer;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

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

public class LightSensorUtils{

    private static final String TAG = "LightSensorUtils";
    private static final Object mLock = new Object();
    private static LightSensorUtils instance;
    private static Context mContext;

    private SensorManager mSensorManager;// 传感器管理器
    private List<Sensor> mList;
    private boolean mIsContains = false;
    private  Boolean isBright ;//true 代表亮      false 代表暗
    private final Float criticalValue = 40.0f;  //  40.0f 代表人视觉的亮暗临界值
    private Consumer<Float> mSensorValueListener;


    private LightSensorUtils() {
    }

    //需要用光感器的地方在 init 初始化
    public void init(Context context) {
        mContext = context;
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        //获取手机上支持的传感器
        mList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : mList) {
            if (Sensor.TYPE_LIGHT ==  sensor.getType()){
                mIsContains = true ;
                return;
            }
        }
    }

    // 带异步保护的单例模式
    public static LightSensorUtils getInstance() {
        if (instance == null) {
            synchronized (mLock) {
                if (instance == null) {
                    instance = new LightSensorUtils();
                }
            }
        }
        return instance;
    }

    public void setSensorValueListener(Consumer<Float> consumer) {
        mSensorValueListener = consumer;
    }

    /*    第三个参数是传感器数据更新数据的速度
          有以下四个值可选，他们的速度是递增的
          SENSOR_DELAY_UI
          SENSOR_DELAY_NORMAL
          SENSOR_DELAY_GAME
          SENSOR_DELAY_FASTEST*/
    public void registerSensor() {
        // 获得光线传感器
        if (mSensorManager != null){
            Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (sensor != null && mIsContains) {
                mSensorManager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

    }

    public void unRegisterSensor() {
        if (mSensorManager != null){
            mSensorManager.unregisterListener(mSensorEventListener);
        }

    }

    private final SensorEventListener mSensorEventListener = new SensorEventListener() {

        /*  onSensorChanged()在传感器数值发生变化已经注册监听器时调用，其更新频率就是注册中的参数三。
        对于光传感器，有效数值存放在values[0]中的，单位为SI lux。
        光传感器通常位于上方（一般靠左侧）， 除了前置摄像头外还有一个孔，一般就是它。遮盖会触发onSensorChanged()  */
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event != null && event.sensor.getType() == Sensor.TYPE_LIGHT && event.values.length > 0) {
                Log.d(TAG,event.values[0] + "");
                mSensorValueListener.accept(event.values[0]);
            }
        }

        /* onAccuracyChanged()会在精度改变或在注册监听器时调用。
        accuracy分为4档，0（unreliable），1（low），2（medium），3（high）。
        注意0并不代表有问题，同时是传感器需要校准。 */

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.d(TAG, "onAccuracyChanged() called with: sensor = [" + sensor + "], accuracy = [" + accuracy + "]");
        }
    };
}
