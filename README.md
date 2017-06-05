# ClientAPI
API designed to make Clients have Universal Support

[![Dependency Status](https://www.versioneye.com/user/projects/588a834fbe496c0037c74b21/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/588a834fbe496c0037c74b21)
[![License](https://img.shields.io/github/license/ZeroMemes/ClientAPI.svg?style=flat-square)](https://github.com/ZeroMemes/ClientAPI/blob/master/LICENSE)
[![Release](https://img.shields.io/github/release/ZeroMemes/ClientAPI.svg?style=flat-square)](https://github.com/ZeroMemes/ClientAPI/releases)

## Credits
| Name                | Contribution         |
|---------------------|----------------------|
| Halalaboos          | CFont                |
| MatthewH & MarcoMC  | CapesAPI Integration |
| Nerxit              | Tab GUI System       |

## Development Kit
To use the Client API, you must download [Development Kit](https://github.com/ZeroMemes/ClientAPI-CDK). Instructions on how to set it up will be posted in the README.

## Concept
* Clients will install into `.minecraft/versions`
  * An installer could be used to configure stacking on top of other tweakers (e.g. Forge)
  * If an installer is used, it could also add a profile to the launcher
* The client will inherit from a vanilla minecraft version
  * This means the client doesn't need to include actual minecraft code
* The client will list ClientAPI as a maven dependancy
  * The minecraft launcher will autmatically download the ClientAPI lib to the `.minecraft/libraries` folder

## Usage
Refer to the [Example](https://github.com/ZeroMemes/ClientAPI-Example) to view how clients using the ClientAPI are structured.
