package com.mty.sensors

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mty.sensors.helpers.SensorConstants.INTENT_ACTION_EXPLORE_SENSOR

class SensorListAdapter(private val mContext: Context, private val mSensors:List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "SensorListAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_sensor_list_item, parent, false)
        return SensorViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mSensors.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sensorName = mSensors[position]
        (holder as SensorViewHolder).mTextView?.apply {
            text = sensorName
            setOnClickListener {
                jumpToSensorActivity(sensorName)
            }
        }
    }

    private fun jumpToSensorActivity(sensorName: String) {
        val intent = Intent(INTENT_ACTION_EXPLORE_SENSOR).apply {
            putExtra("sensorName", sensorName)
            addCategory(Intent.CATEGORY_DEFAULT)
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        try {
            val resolveList = mContext.packageManager.queryIntentActivities(intent, 0)
            if (resolveList.isNotEmpty()) {
                intent.setClassName(mContext, resolveList[0].activityInfo.name)
                mContext.startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView? = null

        init {
            mTextView = itemView.findViewById(R.id.sensor_name)
        }
    }

}
