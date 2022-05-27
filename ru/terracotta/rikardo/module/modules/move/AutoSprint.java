package ru.terracotta.rikardo.module.modules.move;

import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;

public class AutoSprint extends Module {
    public AutoSprint() {
        super("AutoSprint", Category.Move, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(mc.player.movementInput.field_192832_b > 0){
            mc.player.setSprinting(true);
        }
    }
}
