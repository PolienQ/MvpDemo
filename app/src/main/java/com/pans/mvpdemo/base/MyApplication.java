package com.pans.mvpdemo.base;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.pans.mvpdemo.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***********
 * @author pans
 * @date 2015/12/31
 * @describ
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    private static Handler mHandler = new Handler();
    private static ExecutorService executorService;
    public static int count = 60;
    public List<Activity> activityManager; // 管理Activity栈
//    private TasksRepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        LogUtil.isDebug = true;

        // 管理Activity栈
        activityManager = new ArrayList<>();

    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static synchronized ExecutorService getExecutorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
        }
        return executorService;
    }

/*    public static TasksRepositoryComponent getTasksRepositoryComponent() {

        if (mRepositoryComponent == null) {
            mRepositoryComponent = DaggerTasksRepositoryComponent.builder()
                    .applicationModule(new ApplicationModule((getApplicationContext())))
                    .tasksRepositoryModule(new TasksRepositoryModule()).build();
        }
        return mRepositoryComponent;

    }*/

}
