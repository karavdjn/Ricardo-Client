package ru.terracotta.rikardo.module.modules.misc;

import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.FloatSetting;

/**
 * @author zTerrarxd_
 * @since 12:38 of 27.05.2022
 */
public class Timer extends Module {
    FloatSetting speed;
    public Timer() {
        super("Timer", Category.Misc, 0);
        Main.settingManager.add(speed = new FloatSetting("Speed", this, 0, 5, 1.5f));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        mc.timer.field_194147_b = speed.getVal();
    }
}
