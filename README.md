# ClientAPI
API designed to make Clients have Universal Support

[![Dependency Status](https://www.versioneye.com/user/projects/588a834fbe496c0037c74b21/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/588a834fbe496c0037c74b21)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat-square)](https://github.com/ZeroMemes/ClientAPI/blob/master/LICENSE)
[![Release](https://img.shields.io/github/release/ZeroMemes/ClientAPI.svg?style=flat-square)](https://github.com/ZeroMemes/ClientAPI/releases)

## Concept
* Clients will be stored in a "Clients" folder inside of ".minecraft"
* To launch a client, you run the "ClientAPI Launcher" and choose the desired Client
* Minecraft will automatically open with the Client running

## Planned Features
* Fully functional Event, Management, and Value system.
* Tab GUI and Click GUI API
* Module data storage
* Halfpetal's CapesAPI integration

---

## Introduction
The following information can be viewed [Here](https://github.com/ZeroMemes/ClientAPI/tree/master/src/test/). This is just an overview

### Client.json
Release can either be ALPHA, BETA, SNAPSHOT, or RELEASE
```json
{
  "name": "Example Client",
  "authors": [ "Zero" ],
  "id": "example",
  "build": 1.0,
  "type": "RELEASE",
  "main": "me.zero.example.ExampleClient"
}
```
### Main Class
```java
package me.zero.example;

public class ExampleClient extends Client {

    @Override
    public void preInit() {
        // Transformers, Module Types, and Manager initialization
    }

    @Override
    public void onInit() {
        // Modules
    }

    @Override
    public void postInit() {
        // UI
    }
}
```

## Plugins
Plugins are external extensions for Clients. They can contain multiple modules.

### Plugin.json
```json
{
  "name": "Example",
  "description": "Just an example plugin",
  "main": "me.zero.example.PluginMain"
}
```

### Plugin Main
```java
package me.zero.example;

public class PluginMain extends Plugin {
    
    @Override
    public void init() {
        // Plugin creator decides what to do with this
        // All modules are automatically loaded
        // This method is called after Client#postInit()
    }
}
```