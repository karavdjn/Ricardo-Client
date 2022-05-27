package ru.terracotta.rikardo.module.modules.misc;

import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;

public class Panic extends Module {
    public Panic() {
        super("SelfDestruct", Category.Misc, 0);
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        for(Module module : Main.moduleManager.getEnabledModules()){
            module.toggle();
        }
        Main.enable = false;
    }
}
