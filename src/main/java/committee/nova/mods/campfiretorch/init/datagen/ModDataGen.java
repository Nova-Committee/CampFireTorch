package committee.nova.mods.campfiretorch.init.datagen;

import committee.nova.mods.campfiretorch.Const;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project: CampfireTorch
 * @Author: cnlimiter
 * @CreateTime: 2024/1/21 12:51
 * @Description:
 */

public class ModDataGen {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();

        if (event.includeClient()) {
//            generator.addProvider(true, new ModBlockStates(generator, helper));
//            generator.addProvider(true, new ModSpriteSource(generator, helper));
//            generator.addProvider(true, new ModItemModels(output, helper));
//            generator.addProvider(true, new ModLang(output));
            generator.addProvider(true, new ModSoundDefinitions(generator, helper));
        }
        if (event.includeServer()) {
//            generator.addProvider(true, new ModRecipes(generator));
//            generator.addProvider(true, new ModLootTables(generator));
//            generator.addProvider(true, new ModItemTags(generator, future, helper));
//            generator.addProvider(true, new ModBlockTags(generator, future, helper));
//            generator.addProvider(true, new ModEntityTags(generator, future, helper));
//            generator.addProvider(true, new ModAdvancements(output, future, helper));
//            generator.addProvider(true, new ModFluidTags(output, future, helper));

//            ModRegistries.addProviders(true, generator, future, helper);
            generator.addProvider(true, new PackMetadataGenerator(generator.getPackOutput()).add(PackMetadataSection.TYPE, new PackMetadataSection(
                    Component.literal(Const.NAME + " Resources"),
                    DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                    Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion)))));
        }
    }
}
