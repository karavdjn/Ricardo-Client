package ru.terracotta.rikardo.module.modules.render;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.utils.RenderUtils;

public class ArrmorHud extends Module {
    public ArrmorHud() {
        super("ArrmorHud", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();

        int Xpos = 7;
        if(Main.moduleManager.getModule("Coords").isEnable()){
            Xpos = 120;
        }
        int Ypos = GuiScreen.height - 21;
        if(mc.player.getTotalArmorValue() == 0){
            return;
        }
        int count = 0;
        for(ItemStack itemStack : mc.player.getArmorInventoryList()){
            if(itemStack.getItem() != Items.field_190931_a){
                count++;
            }
        }
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + (count * 16), Ypos + 17, 6);
        int pos = Xpos;
        for(ItemStack itemStack : mc.player.getArmorInventoryList()){
            if(itemStack.getItem() != Items.field_190931_a){
                RenderUtils.renderItem(itemStack, pos, Ypos);
                pos += 16;
            }

        }

    }
}
