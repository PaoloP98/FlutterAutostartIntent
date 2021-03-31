package com.intent.autostart;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.Result;

import android.content.Intent;
import android.content.ComponentName;

import java.util.Arrays;
import android.util.Log;


final class AutostartIntent implements MethodChannel.MethodCallHandler {
    private final Context applicationContext;

    AutostartIntent(
            Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Nullable
    private Activity activity;

    public void setActivity(@Nullable Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull final Result result) {
        switch (call.method) {
            case "addAutoStartup": {
                String manufacturer = getManufacturer().toLowerCase();
                if (Arrays.asList("xiaomi", "oppo", "vivo", "letv", "honor").contains(manufacturer))
                    try {
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
                            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                        } else if ("oppo".equalsIgnoreCase(manufacturer)) {
                            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
                        } else if ("vivo".equalsIgnoreCase(manufacturer)) {
                            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
                        } else if ("Letv".equalsIgnoreCase(manufacturer)) {
                            intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
                        } else if ("Honor".equalsIgnoreCase(manufacturer)) {
                            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
                        }

                        applicationContext.startActivity(intent);
                    } catch (Exception e) {
                        Log.e("exc", String.valueOf(e));
                    }

                break;
            }
            case "getManufacturer": {
                result.success(getManufacturer());
                break;
            }
            default:
                result.notImplemented();
                break;
        }
    }

    private String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }
}