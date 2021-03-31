import 'dart:async';

import 'package:flutter/services.dart';

class Autostart {
  static const MethodChannel _channel = const MethodChannel('autostart');

  static Future<void> addAutoStartup() async {
    await _channel.invokeMethod('addAutoStartup');
  }

  static Future<String> get getManufacturer async {
    return await _channel.invokeMethod('getManufacturer');
  }
}
