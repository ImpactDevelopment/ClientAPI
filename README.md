# ClientAPI
API designed to make Clients have Universal Support

## Concept
* Clients will be stored in a "Clients" folder inside of ".minecraft"
* To launch a client, you will install the Client API and then launcher the Client API version
* On launch, a screen will appear prompting for the Client wanting to be used

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
        // Transformers and Module Types
    }

    @Override
    public void onInit() {
        // Modules and UI
    }

    @Override
    public void postInit() {
        // Nothing at the moment
    }
}
```