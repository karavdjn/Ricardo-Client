package ru.terracotta.rikardo.module.setting.settings;

import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.Setting;

import java.awt.*;

public class ColorSetting extends Setting {
    private Color color;
    public ColorSetting(String name, Module module, Color color) {
        super(name, module);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
