package ru.terracotta.rikardo.module.modules.render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;

public class Glowing extends Module {
    public Glowing() {
        super("Glowing", Category.Render, 0);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        for(EntityPlayer player : mc.world.playerEntities){
            if(player.isGlowing()){
                player.setGlowing(false);
            }
        }
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        for(EntityPlayer player : mc.world.playerEntities){
            if(!player.isGlowing()){
                player.setGlowing(true);
            }
        }
    }
}
