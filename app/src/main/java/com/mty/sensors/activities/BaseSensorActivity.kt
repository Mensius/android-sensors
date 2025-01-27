package com.mty.sensors.activities

import android.os.Bundle
import com.mty.sensors.helpers.SensorConstants
import com.mty.sensors.helpers.SensorHelper
import com.mty.sensors.helpers.SensorHelperFactory

abstract class BaseSensorActivity : BaseActivity() {

    protected var mSensorHelper: SensorHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initLayout()
        initSensorHelper()
    }

    private fun initSensorHelper() {
        mSensorHelper = SensorHelperFactory.createSensorHelper(
            getSensorType(),
            this@BaseSensorActivity,
            ::handleSensorValue
        )
    }

    abstract fun getSensorType(): String

    public override fun onResume() {
        super.onResume()
        mSensorHelper?.registerSensor()
    }

    public override fun onPause() {
        super.onPause()
        mSensorHelper?.unRegisterSensor()
    }

    abstract fun initLayout()

    abstract fun handleSensorValue(sensorValue: Map<String, Float>?)

    abstract fun getLayoutId(): Int
}