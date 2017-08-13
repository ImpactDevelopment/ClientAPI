# ClientAPI CDK (Client Development Kit)
Development Kit for [ClientAPI](https://github.com/ZeroMemes/ClientAPI)

## Setup

The setup is the same as any other forgegradle project. The instructions below are for Intellij IDEA, however you can look up instructions for other IDEs. Try searching for “How to setup Forge MDK”.

- Import the project as a gradle project in Intellij
- Run the gradle task `setupDecompWorkspace` (under `tasks/forgegradle`)
- Once this is complete reload gradle libs by clicking the blue "Refresh all Gradle projects" icon under the gradle tab
- Create client run configurations by running gradle task `genIntellijRuns`

That's it. When the gradle config changes, you will need to re-run `setupDecompWorkspace` and refresh gradle projects, but you shouldn't need to run `genIntellijRuns` again.

To build a jar, you should run the `build` gradle task. To debug you can use the generated "Minecraft Client" run configuration.

For a release you will need to create a Minecraft Launcher version json, which depends on ClientAPI, your client and some other stuff like launchwrapper. You should specify the ClientAPI tweak class as a garm argument and extend from the vanilla version (e.g. `1.12`).
