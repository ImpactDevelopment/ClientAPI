# ClientAPI
ClientAPI is a modding API/Framework just like Forge, Sponge and Liteloader, it is designed to provide a shared base for large client-rewrite style mods.

Like other modding APIs it is a tweaker and has the potential to be stacked with other tweakers, so long as they do not both entirely overwrite the same methods.

Unlike most other modding APIs it is designed to be used by a single "client" mod. It is this "client" that is installed, not the ClientAPI, so the "client" mod has full control over all modifications to the vanilla code, unless of course the installation is stacked on top of other modding APIs.

[![Dependency Status](https://www.versioneye.com/user/projects/588a834fbe496c0037c74b21/badge.svg)](https://www.versioneye.com/user/projects/588a834fbe496c0037c74b21)
[![License](https://img.shields.io/github/license/ZeroMemes/ClientAPI.svg)](https://github.com/ZeroMemes/ClientAPI/blob/master/LICENSE)
[![Release](https://img.shields.io/github/release/ZeroMemes/ClientAPI.svg)](https://github.com/ZeroMemes/ClientAPI/releases)
[![Build Status](https://travis-ci.org/ZeroMemes/ClientAPI.svg?branch=master)](https://travis-ci.org/ZeroMemes/ClientAPI)

## Credits
| Name                | Contribution         |
|---------------------|----------------------|
| Nerxit              | Tab GUI System       |

## Development Kit
To use the Client API, you must download the CDK from the Releases page. Instructions on how to set it up are included in the README.

## Concept
* Clients will install into `.minecraft/versions` and `.minecraft/libraries` if they are published to a maven repo
  * An installer could be used to configure stacking on top of other tweakers (e.g. Forge)
  * If an installer is used, it could also add a profile to the launcher
* The client will inherit from a vanilla minecraft version
  * This means the client doesn't need to include actual minecraft code
* The client will list ClientAPI as a maven dependency
  * The minecraft launcher will automatically download the ClientAPI lib to the `.minecraft/libraries` folder

## Usage
Refer to the [Example](src/test) to view how clients using the ClientAPI are structured.

