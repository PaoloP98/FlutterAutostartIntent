package com.intent.autostart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import android.content.Context;
import android.app.Activity;

import io.flutter.embedding.engine.plugins.activity.ActivityAware;

/**
 * AutostartPlugin
 */

// implements ActivityAware to access Context
public final class AutostartPlugin implements FlutterPlugin, ActivityAware {

  private MethodChannel methodChannel;

  @Nullable
  private AutostartIntent autostartIntent;


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    startListening(
            flutterPluginBinding.getApplicationContext(),
            flutterPluginBinding.getBinaryMessenger()
    );
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    stopListening();
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    startListeningToActivity(
            binding.getActivity()
    );
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    onAttachedToActivity(binding);
  }

  @Override
  public void onDetachedFromActivity() {
    stopListeningToActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    onDetachedFromActivity();
  }


  private void startListening(Context applicationContext, BinaryMessenger messenger) {
    methodChannel = new MethodChannel(
            messenger,
            "autostart");

    autostartIntent = new AutostartIntent(
            applicationContext
    );

    methodChannel.setMethodCallHandler(autostartIntent);
  }

  private void stopListening() {
    methodChannel.setMethodCallHandler(null);
    methodChannel = null;
    autostartIntent = null;
  }

  private void startListeningToActivity(
          Activity activity
  ) {
    if (autostartIntent != null) {
      autostartIntent.setActivity(activity);
    }
  }

  private void stopListeningToActivity() {
    if (autostartIntent != null) {
      autostartIntent.setActivity(null);
    }
  }
}