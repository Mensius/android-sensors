package com.mty.sensors

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mty.sensors.activities.BaseActivity
import com.mty.sensors.data.sensors
import com.mty.sensors.helpers.SensorConstants.SENSOR_TYPE_ACCEL
import com.mty.sensors.helpers.SensorConstants.SENSOR_TYPE_LIGHT

class MainActivity : BaseActivity() {

    private var mSensorList : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorList = findViewById(R.id.sensor_list)
        mSensorList?.layoutManager = LinearLayoutManager(this, GridLayoutManager.VERTICAL, false)
        val sensors = sensors
        mSensorList?.adapter = SensorListAdapter(this, sensors)
    }
}