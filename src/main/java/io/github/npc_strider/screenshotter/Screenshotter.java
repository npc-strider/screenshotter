package io.github.npc_strider.screenshotter;

import java.io.File;

import com.mojang.brigadier.Command;

import io.github.npc_strider.screenshotter.client.ResizeHandler;
import io.github.npc_strider.screenshotter.client.ScreenshotHandler;
import io.github.npc_strider.screenshotter.util.PlayerUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Screenshotter implements ModInitializer {

	private final MinecraftClient MC = MinecraftClient.getInstance();
	private final PlayerUtils playerCheck = new PlayerUtils(96);

	@Override
	public void onInitialize() {
		//All of these are testing commands for now...
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(CommandManager.literal("foo").then(
				CommandManager.literal("fabric_test_dedicated")
				.executes(c -> {
					if (playerCheck.isLoaded(MC.player, MC.world)) {
						c.getSource().sendFeedback(new LiteralText("All loaded"), false);
					} else {
						c.getSource().sendFeedback(new LiteralText("Not loaded"), false);
					}
					return Command.SINGLE_SUCCESS;
				})
            ));
			dispatcher.register(CommandManager.literal("sshot").then(
				CommandManager.literal("fabric_test_dedicated")
				.executes(c -> {
					bs.saveScreenshot();
					return Command.SINGLE_SUCCESS;
				})
			));
			dispatcher.register(CommandManager.literal("rtx").then(
				CommandManager.literal("fabric_test_dedicated")
				.executes(c -> {
					c.getSource().getPlayer().teleport(MC.player.getX(),MC.player.getY()+5, MC.player.getZ());
					BlockPos target = playerCheck.raycast(MC.player, MC.world);
					ServerPlayerEntity player = c.getSource().getPlayer();
					World world = player.getEntityWorld();
					c.getSource().sendFeedback(new LiteralText(target.toString()), false);
					c.getSource().sendFeedback(new LiteralText(world.getBlockState(target).toString()), false);
					return Command.SINGLE_SUCCESS;
				})
            ));
        });
	}

	
    private static final ResizeHandler resizeHandler = new ResizeHandler();
	private static final ScreenshotHandler bs = new ScreenshotHandler(new File(""));

	public static ResizeHandler getResizeHandler() {
		return resizeHandler;
	}

	public static ScreenshotHandler getScreenshotHandler() {
		return bs;
	}
}
