package io.github.npc_strider.screenshotter.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.npc_strider.screenshotter.Screenshotter;
import io.github.npc_strider.screenshotter.client.ResizeHandler.Change;
import net.minecraft.client.util.Window;

@Mixin(Window.class)
public class WindowMixin {
    @Shadow
	private void onFramebufferSizeChanged(long window, int width, int height) {}
    
    @Shadow @Final private long handle;

	@Inject(method = "getHandle", at = @At("HEAD"))
	private void onGetHandle(CallbackInfoReturnable<Long> info) {
		Change change = Screenshotter.getResizeHandler().getChange();
		if (change != null) {
			onFramebufferSizeChanged(handle, change.width, change.height);
		}
	}
}
