package committee.nova.mods.campfiretorch;

import committee.nova.mods.campfiretorch.init.config.ModConfig;
import committee.nova.mods.campfiretorch.init.datagen.ModDataGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Const.MOD_ID)
public class CampfireTorch {

    public CampfireTorch() {
        ModConfig.register();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::commonSetup);
        bus.addListener(ModDataGen::gatherData);


        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

}
