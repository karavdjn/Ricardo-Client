package ru.terracotta.rikardo.module.modules.move;

import org.lwjgl.input.Keyboard;
import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.gui.Gui_Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.BooleanSetting;

public class GuiMove extends Module {
    private BooleanSetting sneak;
    public GuiMove() {
        super("GuiMove", Category.Move, 0);
        Main.settingManager.add(sneak = new BooleanSetting("Sneak", this, false));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.keyBindJump.pressed = false;
        mc.gameSettings.keyBindForward.pressed = false;
        mc.gameSettings.keyBindBack.pressed = false;
        mc.gameSettings.keyBindLeft.pressed = false;
        mc.gameSettings.keyBindRight.pressed = false;
        if(sneak.getVal()){
            mc.gameSettings.keyBindSneak.pressed = false;
        }
        mc.gameSettings.keyBindSprint.pressed = false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!(mc.currentScreen instanceof net.minecraft.client.gui.GuiChat)) {

            mc.gameSettings.keyBindJump.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
            mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode());
            mc.gameSettings.keyBindBack.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode());
            mc.gameSettings.keyBindLeft.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode());
            mc.gameSettings.keyBindRight.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode());
            if(sneak.getVal()){
                mc.gameSettings.keyBindSneak.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode());
            }
            mc.gameSettings.keyBindSprint.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindSprint.getKeyCode());
        }
        if((mc.currentScreen instanceof Gui_Main)){
            mc.gameSettings.keyBindJump.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
            mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode());
            mc.gameSettings.keyBindBack.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode());
            mc.gameSettings.keyBindLeft.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode());
            mc.gameSettings.keyBindRight.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode());
            if(sneak.getVal()){
                mc.gameSettings.keyBindSneak.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode());
            }
            mc.gameSettings.keyBindSprint.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindSprint.getKeyCode());
        }
    }
}
