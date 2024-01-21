package committee.nova.mods.campfiretorch;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Project: CampfireTorch
 * @Author: cnlimiter
 * @CreateTime: 2024/1/21 12:47
 * @Description:
 */

public class Const {
    public static final String NAME = "CampfireTorch";
    public static final String MOD_ID = "campfire_torch";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation rl(String name){
        return new ResourceLocation(MOD_ID, name);
    }
}
