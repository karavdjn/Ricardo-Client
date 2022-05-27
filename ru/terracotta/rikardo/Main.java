package ru.terracotta.rikardo;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.ModuleManager;
import ru.terracotta.rikardo.module.setting.SettingManager;


public class Main {
    public static String NAME = "Rikardo";
    public static String VERSION = "V0.0.3";
    public static String SUFFIX = "Client";
    public static ModuleManager moduleManager;
    public static boolean enable = true;
    public static SettingManager settingManager;


    public static void onRender2D(){
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getEnabledModules()){
            module.onRender2D();
        }
    }

    public static void onRender3D(){
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getEnabledModules()){
            module.onRender3D();
        }
    }

    public static void onUpdate(){
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getEnabledModules()){
            module.onUpdate();
        }
    }

    public static void init(){
        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        moduleManager.init();

    }


    public static void onKeyPressed(int key) {
        if(!enable){
            return;
        }
        for(Module module : moduleManager.getModules()){
            if(module.getKey() == key){
                module.toggle();
            }
        }
    }
}
