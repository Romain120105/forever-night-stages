package fr.shoqapik.forvernights;

import net.darkhax.gamestages.GameStageHelper;
import net.darkhax.gamestages.data.IStageData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod(ForeverNightsMod.MODID)
public class ForeverNightsMod
{
    public static final String MODID = "forevernights";
    public ForeverNightsMod()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static boolean nightmareEnabled;
    private static UUID nightmareOwner;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        IStageData data = GameStageHelper.getPlayerData(player);
        if(data.hasStage("nightmare")){
            nightmareEnabled = true;
            if(player.getLevel().getLevelData() instanceof ServerLevelData){
                ServerLevelData serverLevelData = (ServerLevelData) player.level.getLevelData();
                serverLevelData.setDayTime(18000);
            }
            nightmareOwner = player.getUUID();
        }else if(nightmareOwner != null && nightmareOwner == player.getUUID()){
            nightmareEnabled = false;
            nightmareOwner = null;
        }
    }

    public static boolean isNightmareEnabled() {
        return nightmareEnabled;
    }
}
