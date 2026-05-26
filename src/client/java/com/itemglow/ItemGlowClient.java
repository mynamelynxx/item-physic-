package com.itemglow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ItemGlowClient implements ClientModInitializer {

    // Static flag — читается в миксине
    public static boolean glowEnabled = false;

    private static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        // Регистрируем клавишу G (можно перебиндить в настройках управления)
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.itemglow.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.itemglow"
        ));

        // Слушаем нажатие каждый тик
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                glowEnabled = !glowEnabled;

                if (client.player != null) {
                    if (glowEnabled) {
                        client.player.sendMessage(
                                Text.literal("§a✦ Item Glow: §lВКЛЮЧЕН"),
                                true  // true = action bar, не засоряет чат
                        );
                    } else {
                        client.player.sendMessage(
                                Text.literal("§c✦ Item Glow: §lВЫКЛЮЧЕН"),
                                true
                        );
                    }
                }
            }
        });
    }
}
