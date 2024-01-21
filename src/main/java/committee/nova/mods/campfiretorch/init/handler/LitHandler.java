package committee.nova.mods.campfiretorch.init.handler;

import committee.nova.mods.campfiretorch.init.config.ModConfig;
import committee.nova.mods.campfiretorch.init.registry.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.level.block.CampfireBlock.LIT;

/**
 * @Project: CampfireTorch
 * @Author: cnlimiter
 * @CreateTime: 2024/1/21 12:59
 * @Description:
 */

@Mod.EventBusSubscriber
public class LitHandler {

    @SubscribeEvent
    public static void litCampFire(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos blockpos = event.getPos();
        BlockState blockstate = level.getBlockState(blockpos);
        ItemStack itemstack = event.getItemStack();
        if (itemstack.is(Items.SOUL_TORCH) || itemstack.is(Items.TORCH) ){
            if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate))
            {
                BlockPos relative = blockpos.relative(event.getHitVec().getDirection());
                if (!BaseFireBlock.canBePlacedAt(level, relative, getHorizontalDirection(player))) {
                    level.playSound(player, relative, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                    BlockState fire_block = BaseFireBlock.getState(level, relative);
                    level.setBlock(relative, fire_block, 11);
                    level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
                    if (player instanceof ServerPlayer serverplayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger(serverplayer, relative, itemstack);
                        itemstack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(event.getHand()));
                    }
                }
            }
            else {
                level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
                if (player != null) {
                    itemstack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(event.getHand()));
                }
            }
        }

    }

    public static Direction getHorizontalDirection(Player player) {
        return player == null ? Direction.NORTH : player.getDirection();
    }

    @SubscribeEvent
    public static void litTorch(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide) return;
        if (event.getItemStack().getItem() == Items.STICK
        ){
            if (event.getLevel().getBlockState(event.getPos()).is(Blocks.CAMPFIRE)
                    && event.getLevel().getBlockState(event.getPos()).getValue(LIT)
            ){
                if (ModConfig.campfireCustomSound) {
                    event.getLevel().playSound(event.getEntity(), event.getPos(), ModSounds.PLAYER_LIT_TORCH, SoundSource.PLAYERS, 1, 1);
                }
                if (!event.getEntity().isCreative()){
                    event.getItemStack().shrink(ModConfig.campfireStickCost);
                }
                event.getEntity().getInventory().add(new ItemStack(Items.TORCH));
                event.getEntity().awardStat(Stats.INTERACT_WITH_CAMPFIRE);
            }
            else if (event.getLevel().getBlockState(event.getPos()).is(Blocks.SOUL_CAMPFIRE)
                    && event.getLevel().getBlockState(event.getPos()).getValue(LIT)
            ){
                if (!event.getEntity().isCreative()){
                    event.getItemStack().shrink(ModConfig.soulCampfireStickCost);
                }
                event.getEntity().getInventory().add(new ItemStack(Items.SOUL_TORCH));
                event.getEntity().awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                if (ModConfig.soulCampfireCustomSound) {
                    event.getLevel().playSound(event.getEntity(), event.getPos(), ModSounds.PLAYER_LIT_TORCH, SoundSource.PLAYERS, 1, 1);
                }
                if (ModConfig.soulCampfireSandSound) {
                    event.getLevel().playSound(event.getEntity(), event.getPos(), SoundEvents.SOUL_SAND_PLACE, SoundSource.PLAYERS, 0.5f, 0.5f);
                }
            }
        }
    }
}
