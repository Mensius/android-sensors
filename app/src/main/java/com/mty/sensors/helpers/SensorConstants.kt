package com.mty.sensors.helpers

object SensorConstants {

    const val INTENT_ACTION_EXPLORE_SENSOR = "android.intent.action.EXPLORE_SENSOR"

    const val NO_SENSOR = -1

    // 传感器类型
    const val SENSOR_TYPE_LIGHT = "light" // 光传感器
    const val SENSOR_TYPE_ACCEL = "accelerometer" // 加速度传感器

    // 传感器参数
    //// 光传感器
    const val ILLUMINANCE: String = "illuminance" // 照度
    //// 加速度传感器
    const val ACC_X: String = "acc_x" // X轴
    const val ACC_Y: String = "acc_y" // Y轴
    const val ACC_Z: String = "acc_z" // Z轴

}