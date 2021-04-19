import 'package:flutter/material.dart';
import 'package:autostart/autostart.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String? _manufacturer;
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () {
                  Autostart.addAutoStartup();
                },
                child: Text("Launch intent"),
              ),
              ElevatedButton(
                onPressed: () async {
                  final manufacturer = await Autostart.getManufacturer;
                  setState(() {
                    _manufacturer = manufacturer;
                  });
                },
                child: Text("Get Manufacturer"),
              ),
              SizedBox(
                height: 50,
              ),
              if (_manufacturer != null) Text(_manufacturer!)
            ],
          ),
        ),
      ),
    );
  }
}
