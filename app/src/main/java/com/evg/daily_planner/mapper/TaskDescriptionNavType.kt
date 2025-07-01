package com.evg.daily_planner.mapper

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.evg.task_description.presentation.model.TaskDescription
import com.google.gson.Gson

class TaskDescriptionNavType : NavType<TaskDescription>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TaskDescription? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, TaskDescription::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun put(bundle: Bundle, key: String, value: TaskDescription) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): TaskDescription =
        Gson().fromJson(value, TaskDescription::class.java)

    override fun serializeAsValue(value: TaskDescription): String =
        Uri.encode(Gson().toJson(value))
}