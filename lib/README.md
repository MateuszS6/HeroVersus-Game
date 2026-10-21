# Local runtime libraries

The game requires the following local libraries:

- `CityEngine.jar`
- `jlayer-1.0.1-2.jar`
- `mp3spi-1.9.5-2.jar`
- `tritonus-share-0.3.7-3.jar`

Copy them into this directory before building.

`CityEngine.jar` already contains its required JBox2D classes. Do not add a separate JBox2D dependency, because that would introduce duplicate classes.

The binary JARs are intentionally excluded from Git until their redistribution terms have been confirmed.