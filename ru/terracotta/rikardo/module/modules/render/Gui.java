package ru.terracotta.rikardo.module.modules.render;

import org.lwjgl.input.Keyboard;
import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.gui.Gui_Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.BooleanSetting;

public class Gui extends Module {
    private BooleanSetting blur;
    private BooleanSetting snow;
    private BooleanSetting bind;
    public Gui() {
        super("Gui", Category.Render, Keyboard.KEY_RSHIFT);
        Main.settingManager.add(bind = new BooleanSetting("Show Binds", this, false));
        Main.settingManager.add(snow = new BooleanSetting("Snow Effect", this, false));
        Main.settingManager.add(blur = new BooleanSetting("Blur", this, true));

    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(new Gui_Main(bind, blur, snow));
        this.toggle();
    }
}
