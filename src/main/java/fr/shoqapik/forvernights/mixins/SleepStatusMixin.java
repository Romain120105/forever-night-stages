package fr.shoqapik.forvernights.mixins;

import fr.shoqapik.forvernights.ForeverNightsMod;
import net.minecraft.server.players.SleepStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SleepStatus.class)
public abstract class SleepStatusMixin {

    @Shadow private int sleepingPlayers;

    @Shadow public abstract int sleepersNeeded(int p_144011_);

    @Overwrite
    public boolean areEnoughSleeping(int p_144003_) {
        if(ForeverNightsMod.isNightmareEnabled()){
            return false;
        }
        return this.sleepingPlayers >= this.sleepersNeeded(p_144003_);
    }

}
