package ru.terracotta.rikardo.module.modules.Combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;

public class AntiBot extends Module {
    public AntiBot() {
        super("AntiBot", Category.Combat, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        for(Entity entity : mc.world.loadedEntityList){
            if(entity instanceof EntityPlayer && entity.isInvisible() && entity != mc.player){
                mc.world.removeEntity(entity);
            }
        }
    }
}
