package com.mda.basics_lib.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * 自定义后台任务
 */
abstract class CustomWorker(context: Context,workerParameters: WorkerParameters) :Worker(context,workerParameters)