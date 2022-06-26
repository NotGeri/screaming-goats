package dev.geri.screaminggoats.mixins;

import net.minecraft.client.render.entity.GoatEntityRenderer;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoatEntityRenderer.class)
public class GoatMixins {

    private final Identifier screamingTexture = new Identifier("screaminggoats", "screaming_goat.png");

    @Inject(cancellable = true, at = @At("HEAD"), method = "getTexture(Lnet/minecraft/entity/passive/GoatEntity;)Lnet/minecraft/util/Identifier;")
    public void getTexture(GoatEntity goat, CallbackInfoReturnable<Identifier> cir) {
        if (goat.isScreaming()) {
            cir.setReturnValue(screamingTexture);
        }
    }
}
