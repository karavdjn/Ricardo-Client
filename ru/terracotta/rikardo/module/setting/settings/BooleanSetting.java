package ru.terracotta.rikardo.module.setting.settings;

import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.Setting;

public class BooleanSetting extends Setting {
    private boolean val;
    public BooleanSetting(String name, Module module, boolean val) {
        super(name, module);
        this.val = val;
    }

    public Boolean getVal(){
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }
}
