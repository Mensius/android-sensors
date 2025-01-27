package com.mty.sensors.helpers

object SensorConstants {

    const val INTENT_ACTION_EXPLORE_SENSOR = "android.intent.action.EXPLORE_SENSOR"

    const val NO_SENSOR = -1

    // 传感器类型
    const val SENSOR_TYPE_ACCEL = "accelerometer" // 加速度传感器
    const val SENSOR_TYPE_MAGNETIC = "magnetic" // 磁场传感器
    const val SENSOR_TYPE_GYRO = "gyroscope" // 陀螺仪传感器
    const val SENSOR_TYPE_LIGHT = "light" // 光传感器
    const val SENSOR_TYPE_PRESSURE = "pressure" // 压力传感器
    const val SENSOR_TYPE_PROXIMITY = "proximity" // 距离传感器
    const val SENSOR_TYPE_GRAVITY = "gravity" // 重力传感器
    const val SENSOR_TYPE_LINEAR = "linear acceleration" // 线性加速度传感器
    const val SENSOR_TYPE_ROTATION = "rotation" // 旋转矢量传感器
    const val SENSOR_TYPE_ORIENTATION = "orientation" // 方向传感器
    const val SENSOR_TYPE_HUMIDITY = "humidity" // 湿度传感器
    const val SENSOR_TYPE_TEMPERATURE = "temperature" // 温度传感器
    const val SENSOR_TYPE_STEP = "step" // 步数传感器
    const val SENSOR_TYPE_GAME = "game" // 游戏旋转矢量传感器
    const val SENSOR_TYPE_HEART = "heart" // 心率传感器

    // 传感器参数
    //// 光传感器
    const val ILLUMINANCE: String = "illuminance" // 照度
    //// 加速度传感器
    const val ACC_X: String = "acc_x" // X轴
    const val ACC_Y: String = "acc_y" // Y轴
    const val ACC_Z: String = "acc_z" // Z轴
    //// 磁场传感器
    const val MAG_X: String = "mag_x" // X轴
    const val MAG_Y: String = "mag_y" // Y轴
    const val MAG_Z: String = "mag_z" // Z轴
    //// 陀螺仪传感器
    const val GYRO_X: String = "gyro_x" // X轴
    const val GYRO_Y: String = "gyro_y" // Y轴
    const val GYRO_Z: String = "gyro_z" // Z轴
    //// 气压传感器
    const val PRESSURE: String = "pressure" // 气压
    //// 距离传感器
    const val PROXIMITY: String = "proximity" // 距离
    //// 重力传感器
    const val GRAV_X: String = "grav_x" // X轴
    const val GRAV_Y: String = "grav_y" // Y轴
    const val GRAV_Z: String = "grav_z" // Z轴
    //// 线性加速度传感器
    const val LINEAR_ACC_X: String = "linear_acc_x" // X轴
    const val LINEAR_ACC_Y: String = "linear_acc_y" // Y轴
    const val LINEAR_ACC_Z: String = "linear_acc_z" // Z轴
    //// 旋转矢量传感器
    const val ROTATION_VECTOR: String = "rotation_vector" // 旋转向量
    //// 方向传感器
    const val ORIENTATION: String = "orientation" // 方向
    //// 湿度传感器
    const val HUMIDITY: String = "humidity" // 湿度
    //// 温度传感器
    const val TEMPERATURE: String = "temperature" // 温度
    //// 步数传感器
    const val STEP_COUNT: String = "step_count" // 步数
    //// 游戏旋转矢量传感器
    const val GAME_ROTATION_VECTOR: String = "game_rotation_vector" // 游戏旋转向量
    //// 心率传感器
    const val HEART_RATE: String = "heart_rate" // 心率


}