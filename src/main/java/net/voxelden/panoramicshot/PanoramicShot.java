package net.voxelden.panoramicshot;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class PanoramicShot implements ClientModInitializer {
    KeyBinding panoramicScreenshotKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.panoramic_screenshot", GLFW.GLFW_KEY_F9, KeyBinding.MISC_CATEGORY));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (panoramicScreenshotKeybind.wasPressed()) {
                if (client.player != null) {
                    client.player.sendMessage(client.takePanorama(client.runDirectory, 2048, 2048));
                }
            }
        });
    }
}
