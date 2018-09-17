[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b10fe09654754df8843fe796b835e38f)](https://app.codacy.com/app/ImpactDevelopment/ClientAPI?utm_source=github.com&utm_medium=referral&utm_content=ImpactDevelopment/ClientAPI&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.org/ImpactDevelopment/ClientAPI.svg?branch=master)](https://travis-ci.org/ImpactDevelopment/ClientAPI)
[![License](https://img.shields.io/github/license/ImpactDevelopment/ClientAPI.svg)](LICENSE)
[![Release](https://img.shields.io/github/release/ImpactDevelopment/ClientAPI.svg)](https://github.com/ImpactDevelopment/ClientAPI/releases)

# ClientAPI
ClientAPI is a modding API/Framework for Minecraft just like Forge, Sponge and Liteloader, it
is designed to provide a shared base for client-side mods for minecraft.

Like other modding APIs it is a tweaker and has the potential to be stacked with other tweakers, so long
as they do not both entirely overwrite the same methods.

Unlike most other modding APIs it is designed to be used by a single "client" mod. It is this "client"
that is installed, not ClientAPI, so the "client" mod has full control over all modifications to
the vanilla code, unless of course the installation is stacked on top of other modding APIs.

## Developing ClientAPI mods

### Downloading the CDK
To use the Client API, you must download the CDK from the Releases page. Instructions on how to set
it up are included in the README.

### Developing a ClientAPI Mod
An example ClientAPI mod is provided with the CDK, however if you choose to start from scratch, an overview
of how to setup the core functionalities can be found [here](src/example/README.md).

### Creating a Launcher Profile
In order to create a Minecraft Launcher Profile for a ClientAPI based mod, you need to...
* Create a copy of the vanilla Minecraft JSON for the game version that is being used by your ClientAPI version.
* Open the JSON file in a text editor like [Notepad++](https://notepad-plus-plus.org)
* Add the ClientAPI dependencies as well as ClientAPI itself into the ``libraries`` array.
  ```
  {
      "name": "project-group:project-id:project-version",
      "url": "https://dependency.repository.url/"
  }
  ```
* Add Mojang's launchwrapper as a dependency
  ```
  {
      "name": "net.minecraft:launchwrapper:1.12"
  }
  ```
* Add a launch argument to load ClientAPI's tweaker 
  ```
  --tweakClass clientapi.load.ClientTweaker
  ```
  If you are planning on using [Minecraft Forge](https://files.minecraftforge.net/) or [OptiFine](https://optifine.net/)
  in parallel with ClientAPI, you should use their respective tweakers found [here](src/main/java/clientapi/load).
* Add your client mod's dependency. This follows the same format as normal dependencies. If your
  client mod is not on a maven repository then the URL should not be defined, and the jar file
  should be installed into the ``libraries`` directory of minecraft.

## Contributing to ClientAPI
This section is incomplete.
