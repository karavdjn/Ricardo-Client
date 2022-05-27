package ru.terracotta.rikardo.module.modules.Combat;


import org.lwjgl.input.Keyboard;
import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.BooleanSetting;
import ru.terracotta.rikardo.module.setting.settings.ColorSetting;
import ru.terracotta.rikardo.module.setting.settings.FloatSetting;
import ru.terracotta.rikardo.module.setting.settings.ModeSetting;

import java.awt.*;

public class test extends Module {
    BooleanSetting check;
    FloatSetting slider;
    ModeSetting mode;
    ColorSetting color;
    public test() {
        super("TestModule", Category.Combat, 0);
        Main.settingManager.add(check = new BooleanSetting("Check", this, true));
        Main.settingManager.add(slider = new FloatSetting("Slider", this, 0, 10, 5));
        Main.settingManager.add(mode = new ModeSetting("Mode", this, "Client", new String[] {"Client", "Silent"}));
        Main.settingManager.add(color = new ColorSetting("Color", this, new Color(65, 30, 96, 150)));
    }


}
