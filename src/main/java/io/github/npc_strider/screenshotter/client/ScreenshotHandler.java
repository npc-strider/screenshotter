package io.github.npc_strider.screenshotter.client;

import java.io.File;

import io.github.npc_strider.screenshotter.Screenshotter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.ScreenshotUtils;
import net.minecraft.client.util.Window;

public class ScreenshotHandler {
	private final MinecraftClient MC;
	private final ResizeHandler resizeHandler;
    private final File file;

	private boolean active;
	private int frame;
	private int displayWidth;
	private int displayHeight;
    private int screenshotWidth = 1024;
    private int screenshotHeight = 1024;
    
	public ScreenshotHandler(File file) {
		this.MC = MinecraftClient.getInstance();
		this.resizeHandler = Screenshotter.getResizeHandler();
        this.file = file;
		this.frame = 0;
		this.active = false;
	}

	public void saveScreenshot() {
		this.frame = 0;
		this.active = true;
	}
    
	public boolean onRenderTick() {
		if (!this.active) {
			return false;
		}

        Window window = MC.getWindow();
		switch (this.frame) {
			case 0:
				this.displayWidth = window.getFramebufferWidth();
				this.displayHeight = window.getFramebufferHeight();
				resizeHandler.queueChange(screenshotWidth, screenshotHeight);
				window.getHandle();
				break;

			case 3:
				Framebuffer fb = MC.getFramebuffer();
				ScreenshotUtils.saveScreenshot(
					file,
					screenshotWidth,
					screenshotHeight,
					fb,
					text -> {MC.player.sendMessage(text, false);}
				);
				resizeHandler.queueChange(displayWidth, displayHeight);
				window.getHandle();
				break;
		}
		this.frame++;
		this.active = this.frame > 3 ? false : true;
		return this.active;
	}
}
