package ru.terracotta.rikardo.module.modules.misc;

import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.FloatSetting;

public class VClip extends Module {
    private FloatSetting blocks;
    public VClip() {
        super("VClip", Category.Misc, 0);
        Main.settingManager.add(blocks = new FloatSetting("Blocks", this, -10, 10, 1));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if(mc.player.isRidingHorse()){
            mc.player.setPosition(mc.player.posX, mc.player.posY + blocks.getVal(), mc.player.posZ);
        }
        this.toggle();
    }
}
