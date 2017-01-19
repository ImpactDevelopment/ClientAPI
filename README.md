# ClientAPI
API designed to make Clients have Universal Support

### Concept
* Clients will be stored in a "Clients" folder inside of ".minecraft"
* To launch a client, you will install the Client API and then launcher the Client API version
* On launch, a screen will appear prompting for the Client wanting to be used

### Planned Features
* Fully functional Event, Management, and Value system.
* Tab GUI and Click GUI API
* Module data storage
* Halfpetal's CapesAPI integration

### Overview

#### Client.json
* **Name** Client Name
* **Authors** Client Authors-
* **ID** Unique ID
* **Build** The Current Build/Version
* **Type** Build Type, Can be "SNAPSHOT", "ALPHA", "BETA", or "RELEASE"
* **Main** Path to Main Class
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
#### Main Class
```java
package me.zero.example;

public class ExampleClient extends Client {
    
    @Override
    public void init() {
        // load
    }
}
```