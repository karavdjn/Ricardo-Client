package ru.terracotta.rikardo.utils.snow;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import ru.terracotta.rikardo.utils.RenderUtil;

import java.awt.*;
import java.util.Random;

public class Snow {
    private float x;
    private float y;
    private long time;
    public Snow(float x, float y){
        this.x = x;
        this.y = y;
        time = System.currentTimeMillis();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void Render(){
        RenderUtil.drawSmoothRect(x, y, x + 1, y + 1, Color.white.getRGB());
    }

    public void move(){
        if(System.currentTimeMillis() - time >= 5){
            y+=1.1;
            x+= (Mouse.getX() - GuiScreen.width) / 100;
            time = System.currentTimeMillis();
        }
    }
}
