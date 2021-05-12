package io.github.npc_strider.screenshotter.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class PlayerUtils {

	private MinecraftClient MC;

	private final int raycastRange;

    public PlayerUtils(int raycastRange) {
		this.MC = MinecraftClient.getInstance();
		this.raycastRange = raycastRange;
	}

	public void execute() {
		MC.options.hudHidden = !MC.options.hudHidden;
		// MC.player.teleport(destX, destY, destZ);
	}

	public BlockPos raycast(ClientPlayerEntity player, World world) {
		float dt = MinecraftClient.getInstance().getTickDelta();
		Vec3d cameraV = player.getCameraPosVec(dt);
		Vec3d unitRay = Vec3d.fromPolar(player.getPitch(dt), player.getYaw(dt));
		Vec3d targetV = cameraV.add(unitRay.multiply(raycastRange));
		RaycastContext raycastContext = new RaycastContext(
			cameraV,
			targetV,
			RaycastContext.ShapeType.COLLIDER,
			RaycastContext.FluidHandling.ANY,
			(Entity)player
		);
		return world.raycast(raycastContext)
					.getBlockPos();
	}
	
    public boolean isLoaded(ClientPlayerEntity player, World world) {
		int r = MC.options.viewDistance;
        for (int i = player.chunkX-r; i < player.chunkX+r; i++) {
            for (int j = player.chunkZ-r; j < player.chunkZ+r; j++) {
                if (!world.isChunkLoaded(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
