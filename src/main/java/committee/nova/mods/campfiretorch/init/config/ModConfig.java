package committee.nova.mods.campfiretorch.init.config;

import committee.nova.mods.campfiretorch.CampfireTorch;
import committee.nova.mods.campfiretorch.Const;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Const.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();



    private static final ForgeConfigSpec.IntValue CAMPFIRE_STICK_COST = BUILDER
            .comment("Stick cost number")
            .defineInRange("campfireStickCost", 1, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue SOUL_CAMPFIRE_STICK_COST = BUILDER
            .comment("Stick cost number")
            .defineInRange("soulCampfireStickCost", 3, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.BooleanValue CAMPFIRE_CUSTOM_SOUND = BUILDER
            .define("campfireCustomSound",true);

    private static final ForgeConfigSpec.BooleanValue SOUL_CAMPFIRE_CUSTOM_SOUND = BUILDER
            .define("soulCampfireCustomSound",true);

    private static final ForgeConfigSpec.BooleanValue SOUL_CAMPFIRE_SAND_SOUND = BUILDER
            .define("soulCampfireSandSound",true);



    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int campfireStickCost;
    public static int soulCampfireStickCost;
    public static boolean campfireCustomSound;
    public static boolean soulCampfireCustomSound;
    public static boolean soulCampfireSandSound;


    public static void register(){
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, SPEC);
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        campfireStickCost = CAMPFIRE_STICK_COST.get();
        soulCampfireStickCost = SOUL_CAMPFIRE_STICK_COST.get();
        campfireCustomSound = CAMPFIRE_CUSTOM_SOUND.get();
        soulCampfireCustomSound = SOUL_CAMPFIRE_CUSTOM_SOUND.get();
        soulCampfireSandSound = SOUL_CAMPFIRE_SAND_SOUND.get();
    }
}
