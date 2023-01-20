package xyz.tildejustin.cooldowndisplay;


import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CooldownDisplay implements ModInitializer {
    public static boolean showCooldown;

    public static final Logger LOGGER = LogManager.getLogger("cooldowndisplay");

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

    @Override
    public void onInitialize() {
        showCooldown = true;
        log(Level.INFO, "Hello, world!");
    }
}
