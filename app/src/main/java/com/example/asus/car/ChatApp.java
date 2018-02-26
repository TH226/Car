package com.example.asus.car;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by asus on 2016/11/1.
 */
public class ChatApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         *
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);

            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

                DemoContext.init(this);
            }
        }

       String Token1 = "C7/RKAjA0Trdgh+hxi/nOL8jeokNoTvT9MYBA4q8pOHASN3aJ+SmTEkJ9fWChHisMENA91sGi/7GPgatrIQr2g==";
        RongIM.connect(Token1, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
                Toast.makeText(ChatApp.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String s) {
               // Toast.makeText(ChatApp.this, "sucess"+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(ChatApp.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
}
