# Screenshotter
# In development
Generates a random screenshot of minecraft.

---
## GPL compliance/credits
Portions of this mod contain code from [mineshot revived](https://github.com/pascallj/mineshot-revived-fabric), which is licensed under the GPL 3.0.

Changes from mineshot revived:
* removal of anything unrelated to the screenshot functionality
* some code was very closely based off original code in the mod but rewritten so that I could understand exactly what it was doing (`ScreenshotHandler.java`). It is still a file derived from the mod and this is being written for compliance. Differences in this file include the use of `ScreenshotUtils` instead of the file handling for large screenshots, and lack of a options file for simplicity.
* Relevant files are: `WindowMixin.java`, `BackgroundRendererMixin.java`, `ResizeHandler.java`, `ScreenshotHandler.java`.