package com.itemglow.mixin;

import com.itemglow.ItemGlowClient;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Перехватывает метод isGlowing() у всех выброшенных предметов (ItemEntity).
 * Когда флаг glowEnabled = true — возвращает true для ВСЕХ предметов на земле,
 * что заставляет движок рендерить светящийся контур (даже сквозь блоки).
 */
@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method = "isGlowing", at = @At("HEAD"), cancellable = true)
    private void itemglow$forceGlow(CallbackInfoReturnable<Boolean> cir) {
        if (ItemGlowClient.glowEnabled) {
            cir.setReturnValue(true);
        }
    }
}
