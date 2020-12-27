# BetterChatRelay
A nice lil' discord to minecraft chat relay  for spigot based servers

### How to setup
[Download the latest plugin jar](https://github.com/femboy-neal/betterChatRelay/releases/download/0.2.0/betterMinecraftRelay-1.0-SNAPSHOT.jar)
and place it in your plugins folder

Configure your relay.properties (in your servers root directory) with the following
```properties
webhook = <webhook of the minecraft channel>
channelID = <Channel ID of the minecraft server>
token =  <Token of discord bot>
```
Once you do this, also install this plugin
https://github.com/zDevelopers/ImageOnMap/releases/tag/v4.0

after that your plugin is setup and all that you have left is to enjoy!

### Build From source (Not Required)
clone this git and run mvn install

### Technologies Used in Project 
* [SpigotMC](https://www.spigotmc.org/)
* [Discord4j](https://github.com/Discord4J/Discord4J)
* [TemmieWebhook](https://github.com/MrPowerGamerBR/TemmieWebhook)
* [Crafatar](https://github.com/crafatar/crafatar)
