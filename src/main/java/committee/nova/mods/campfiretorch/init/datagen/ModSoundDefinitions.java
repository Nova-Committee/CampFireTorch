package committee.nova.mods.campfiretorch.init.datagen;

import committee.nova.mods.campfiretorch.Const;
import committee.nova.mods.campfiretorch.init.registry.ModSounds;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

/**
 * @Project: CampfireTorch
 * @Author: cnlimiter
 * @CreateTime: 2024/1/21 12:49
 * @Description:
 */
public class ModSoundDefinitions extends SoundDefinitionsProvider {

    public ModSoundDefinitions(DataGenerator output, ExistingFileHelper helper) {
        super(output.getPackOutput(), Const.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.add(ModSounds.PLAYER_LIT_TORCH, definition().with(
                sound("campfire_torch:lit_torch").stream()
        ));
    }
}
