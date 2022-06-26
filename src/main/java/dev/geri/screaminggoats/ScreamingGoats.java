package dev.geri.screaminggoats;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class ScreamingGoats implements ClientModInitializer {

    private static final Logger logger = LoggerFactory.getLogger("ScreamingGoats");

    @Override
    public void onInitializeClient() {
        logger.info("Pigifying screaming goats!");
    }

}
