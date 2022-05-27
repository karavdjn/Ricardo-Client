package ru.terracotta.rikardo.module.modules.render;

import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.BooleanSetting;

public class NameProtect extends Module {
    private BooleanSetting friends;


    public NameProtect() {
        super("NameProtect", Category.Render, 0);

    }

}
