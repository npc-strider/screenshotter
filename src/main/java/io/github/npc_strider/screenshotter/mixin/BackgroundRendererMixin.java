package io.github.npc_strider.screenshotter.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.npc_strider.screenshotter.Screenshotter;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
	@Inject(method = "render", at = @At("RETURN"))
	private static void onRender(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo info) {
		Screenshotter.getScreenshotHandler().onRenderTick();
	}
}
