package com.mda.basics_lib.work

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkerUtil {

    companion object {

        /**
         *  执行单次后台任务
         */

        fun OneTimeWork( clazz: Class<CustomWorker>, tag: String) {
            val oneTimeRequest = OneTimeWorkRequest.Builder(clazz).addTag(tag).build()
        }


        /**执行单次带重试后台任务
         *@param  tag 标记
         *@param backoffPolicy 设置重试时间模式 默认值为线性模式
         */
        fun OneTimeWork2(
            clazz: Class<CustomWorker>,
            tag: String,
            backoffPolicy: BackoffPolicy = BackoffPolicy.LINEAR,
            retryTime: Long = 2,
            retryTimeUnit: TimeUnit = TimeUnit.MINUTES
        ): OneTimeWorkRequest {
            val oneTimeRequest = OneTimeWorkRequest.Builder(clazz)
                .setBackoffCriteria(backoffPolicy, retryTime, retryTimeUnit).addTag(tag).build()
            return oneTimeRequest

        }

        /**
         * 执行带重试周期后台任务
         * @param  tag 标记
         *
         * @param backoffPolicy 设置重试时间模式 默认值为线性模式
         */

        fun periodicWork(
            clazz: Class<CustomWorker>,
            tag: String,
            backoffPolicy: BackoffPolicy = BackoffPolicy.LINEAR,
            retryTime: Long = 2,
            retryTimeUnit: TimeUnit = TimeUnit.MINUTES,
            repeatTime: Long = 15
        ): PeriodicWorkRequest {
            val periodicRequest =
                PeriodicWorkRequest.Builder(clazz, repeatTime, TimeUnit.MINUTES)
                    .setBackoffCriteria(backoffPolicy, retryTime, retryTimeUnit).addTag(tag).build()
            return periodicRequest
        }


        fun enqueueWork(context: Context) {
            //单次任务
            val request1 = OneTimeWorkRequest.Builder(CustomWorker::class.java).build()
            //周期性任务   周期事件不得少于15分钟，少于15分钟会自动将周期设置成失误分钟
            val request2 =
                PeriodicWorkRequest.Builder(CustomWorker::class.java, 15, TimeUnit.MINUTES).build()


            //设置延时任务 并设置标签
            val request3 = OneTimeWorkRequest.Builder(CustomWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES).addTag("request3").build()


            //配置重试任务 然后配合result.retry一起使用
            val request4 =
                OneTimeWorkRequest.Builder(CustomWorker::class.java)
                    .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.MINUTES).build()


            //使用带出传递数据的  在woker中使用inputData进行读取
            val request5 = OneTimeWorkRequest.Builder(CustomWorker::class.java)
                .setInputData(Data.Builder().apply {
                    putString("1", "1")
                }.build())
                .build()

            //将任务传给workmanager
            WorkManager.getInstance(context).enqueue(request1)

            //取消任务
            WorkManager.getInstance(context).cancelAllWorkByTag("request1")
            //根据id取消任务
            WorkManager.getInstance(context).cancelWorkById(request1.id)


            //实现链式任务

            WorkManager.getInstance(context).beginWith(request1).then(request3).then(request5)
                .enqueue()

            //监听任务结果

//            WorkManager.getInstance(context).getWorkInfoByIdLiveData(request1.id).observe {
//
//                when (it.state) {
//                    WorkInfo.State.SUCCEEDED -> Log.d("~~~", "success")
//                    WorkInfo.State.FAILED -> Log.d("~~~", "fail")
//                    WorkInfo.State.RUNNING -> Log.d("~~~", "running")
//                    WorkInfo.State.ENQUEUED -> Log.d("~~~", "enqueued")
//                    WorkInfo.State.CANCELLED -> Log.d("~~~", "cancelled")
//                    WorkInfo.State.BLOCKED -> Log.d("~~~", "blocked")
//                }
//            }


        }
    }

}