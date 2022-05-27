package ru.terracotta.rikardo.module.modules.render;

import net.minecraft.util.text.TextFormatting;
import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.utils.RenderUtils;

import java.awt.*;

public class WaterMark extends Module {
    public WaterMark() {
        super("WaterMark", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int Xpos = 7;
        int Ypos = 7;
        String text = Main.NAME + " " + Main.SUFFIX + " " + Main.VERSION;
        String text2 = "Coded by" + TextFormatting.GREEN +  " zTerrarxd_";
        int wight = fr.getStringWidth(text2);
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + wight, Ypos + 20, 10);
        fr.drawString(text, Xpos, Ypos + 5 - (fr.getHeight() / 2), Color.white.getRGB());
        fr.drawString(text2, Xpos, Ypos + 15 - (fr.getHeight() / 2), Color.white.getRGB());
    }
}
