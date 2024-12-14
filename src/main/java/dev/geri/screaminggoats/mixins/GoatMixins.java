package dev.geri.screaminggoats.mixins;

import net.minecraft.client.render.entity.GoatEntityRenderer;
import net.minecraft.client.render.entity.state.GoatEntityRenderState;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.WeakHashMap;

@Mixin(GoatEntityRenderer.class)
public class GoatMixins {

    @Unique
    private static final Identifier SCREAMING_TEXTURE = Identifier.of("screaminggoats", "screaming_goat.png");

    // Map to store the association between GoatEntityRenderState and GoatEntity
    @Unique
    private final WeakHashMap<GoatEntityRenderState, GoatEntity> renderStateToEntityMap = new WeakHashMap<>();

    /**
     * Capture the association between the GoatEntity and GoatEntityRenderState during updateRenderState.
     */
    @Inject(method = "updateRenderState(Lnet/minecraft/entity/passive/GoatEntity;Lnet/minecraft/client/render/entity/state/GoatEntityRenderState;F)V",
            at = @At("HEAD"))
    public void captureEntityRenderState(GoatEntity goatEntity, GoatEntityRenderState goatEntityRenderState, float f, CallbackInfo ci) {
        renderStateToEntityMap.put(goatEntityRenderState, goatEntity);
    }

    /**
     * Override the texture if the render state corresponds to a screaming goat.
     */
    @Inject(method = "getTexture(Lnet/minecraft/client/render/entity/state/GoatEntityRenderState;)Lnet/minecraft/util/Identifier;",
            at = @At("HEAD"), cancellable = true)
    public void getTexture(GoatEntityRenderState goatEntityRenderState, CallbackInfoReturnable<Identifier> cir) {
        // Fetch the associated entity from the map
        GoatEntity goatEntity = renderStateToEntityMap.get(goatEntityRenderState);
        if (goatEntity != null && goatEntity.isScreaming()) {
            cir.setReturnValue(SCREAMING_TEXTURE);
        }
    }
}
