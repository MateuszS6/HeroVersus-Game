# Local runtime library

The project requires `CityEngine.jar` in this directory.

CityEngine provides the game engine and already embeds its required JBox2D
classes. Do not add a separate JBox2D dependency, because that would introduce
duplicate classes.

The MP3 playback libraries are downloaded automatically by Gradle from Maven
Central.

`CityEngine.jar` remains excluded from Git until its redistribution terms can
be confirmed.