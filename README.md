# Autostart intent for Android manufacturers

The purpose of this Flutter plugin is to launch an intent to request permission for automatic start-up.

Some manufacturers (for battery saving purposes) do not allow the app to run in the background when the device is rebooted.

Intents have been retrieved from this stackoverflow answer: https://stackoverflow.com/a/48945679/15328543

The intent is currently launched for these manufacturers:

- xiaomi ✔
- oppo
- vivo
- Letv
- honor

Personally I tested only on xiaomi because I don't have the other devices. ping me if you try it on other Android devices

To use this plugin add the dependency to your package's pubspec.yaml file:

```
autostart:
    git:
      url: git://github.com/PaoloP98/FlutterAutostartIntent
```

Or clone this repository and put it in a project folder e.g. "plugins".

```
autostart:
    path: plugins/autostart
```



```
import 'package:autostart/autostart.dart';
...
@override
  Widget build(BuildContext context) {
  	return ElevatedButton(
	    onPressed: () async {
	    if (Platform.isAndroid) {
    		final manufacturer = await Autostart.getManufacturer; // *optional* to know who the manufacturer is
	    	print(manufacturer);
    		await Autostart.addAutoStartup();
    		}
    	},
    	child: Text("Launch intent"),
    );
}

```

