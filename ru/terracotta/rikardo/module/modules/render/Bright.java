package ru.terracotta.rikardo.module.modules.render;

import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.FloatSetting;

public class Bright extends Module {
    private FloatSetting gamma;
    public Bright() {
        super("Bright", Category.Render, 0);
        Main.settingManager.add(gamma = new FloatSetting("Gamma", this, 0, 1000, 100));
    }


    @Override
    public void onEnable() {
        super.onEnable();
        mc.gameSettings.gammaSetting = gamma.getVal();
    }
}
