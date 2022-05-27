package ru.terracotta.rikardo.module.modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.Display;
import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.managers.FriendManager;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.BooleanSetting;
import ru.terracotta.rikardo.module.setting.settings.FloatSetting;
import ru.terracotta.rikardo.module.setting.settings.ModeSetting;
import ru.terracotta.rikardo.utils.*;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Random;

import static net.minecraft.client.gui.inventory.GuiInventory.drawEntityOnScreen;

public class AimBot extends Module {
    private BooleanSetting walls;
    private BooleanSetting AutoShoot;
    private BooleanSetting Silent;
    private TimerUtils timer;
    private ModeSetting pos;
    private FloatSetting mindeley;
    private FloatSetting maxdeley;
    private BooleanSetting targethud;
    private FloatSetting Predict;
    private Entity target;
    private FloatSetting fov;
    public AimBot() {
        super("AimBot", Category.Combat, 0);
        timer = new TimerUtils();
        Main.settingManager.add(fov = new FloatSetting("Fov", this, 0, 360, 360));
        Main.settingManager.add(walls = new BooleanSetting("Walls", this, true));
        Main.settingManager.add(AutoShoot = new BooleanSetting("AutoShoot", this, true));
        Main.settingManager.add(mindeley = new FloatSetting("AutoShootMinDeley", this, 0, 1000, 300));
        Main.settingManager.add(maxdeley = new FloatSetting("AutoShootMaxDeley", this, 0, 1000, 900));

        Main.settingManager.add(Silent = new BooleanSetting("Silent", this, true));

        Main.settingManager.add(targethud = new BooleanSetting("TargetHud", this, true));
        Main.settingManager.add(Predict = new FloatSetting("Predict", this, 0, 10, 3.5f));
        Main.settingManager.add(pos = new ModeSetting("Pos", this, "Head", new String[] {"Head", "Chest", "Dick", "Legs"}));
    }

    public static ArrayList<Entity> entities = new ArrayList<Entity>();


    public void sortList() {
        entities = new ArrayList<>(mc.world.loadedEntityList);
        entities.sort((entity1, entity2) -> {
            if (mc.player.getDistanceToEntity(entity1) > mc.player.getDistanceToEntity(entity2)) {
                return 1;
            } else if (mc.player.getDistanceToEntity(entity1) < mc.player.getDistanceToEntity(entity2)) {
                return -1;
            }
            return 0;
        });
    }

    public  boolean canSeeEntityAtFov(Entity entityLiving, float scope) {
        Minecraft.getMinecraft();
        double diffX = entityLiving.posX - mc.player.posX;
        Minecraft.getMinecraft();
        double diffZ = entityLiving.posZ - mc.player.posZ;
        float newYaw = (float)(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
        double d = newYaw;
        Minecraft.getMinecraft();
        double difference = angleDifference(d, mc.player.rotationYaw);
        difference*=2;
        return difference <= (double)scope;
    }

    public static double angleDifference(double a, double b) {
        float yaw360 = (float)(Math.abs(a - b) % 360.0);
        if (yaw360 > 180.0f) {
            yaw360 = 360.0f - yaw360;
        }
        return yaw360;
    }



    public Entity gettarget(float range, boolean walls){
        sortList();
        for(Entity entity : entities){
            if(entity instanceof EntityPlayer && !FriendManager.isFriend(entity.getName()) && ((EntityPlayer) entity).getHealth() != 0 && mc.player != entity && mc.player.getDistanceToEntity(entity) <= range && !entity.isInvisible()){
                if((walls || (!walls && mc.player.canEntityBeSeen(entity))) && (canSeeEntityAtFov(entity, fov.getVal()))){

                    return entity;
                }
            }
        }

        return null;
    }

    public float[] getRotationFromPosition(final double d, final double d2, final double d3) {
        final double d4 = d - mc.player.posX;
        final double d5 = d2 - Minecraft.getMinecraft().player.posZ;
        final double d6 = d3 - Minecraft.getMinecraft().player.posY - 0.6;
        final double d7 = MathHelper.sqrt(d4 * d4 + d5 * d5);
        final float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        final float f2 = (float)(-(Math.atan2(d6, d7) * 180.0 / Math.PI));
        return new float[] { f, f2 };
    }

    public float[] getRotations(float posX, float posY, float posZ, final String string) {
        if (string == "Head") {
            final double d = posX;
            final double d2 = posZ;
            final double d3 = posY + mc.player.getEyeHeight() / 2.0f;
            return getRotationFromPosition(d, d2, d3);
        }
        if (string == "Chest") {
            final double d = posX;
            final double d4 = posZ;
            final double d5 = posY + mc.player.getEyeHeight() / 2.0f - 0.75;
            return getRotationFromPosition(d, d4, d5);
        }
        if (string == "Dick") {
            final double d = posX;
            final double d6 = posZ;
            final double d7 = posY + mc.player.getEyeHeight() / 2.0f - 1.2;
            return getRotationFromPosition(d, d6, d7);
        }
        if (string == "Legs") {
            final double d = posX;
            final double d8 = posZ;
            final double d9 = posY + mc.player.getEyeHeight() / 2.0f - 1.5;
            return getRotationFromPosition(d, d8, d9);
        }
        final double d = posX;
        final double d10 = posZ;
        final double d11 = posY + mc.player.getEyeHeight() / 2.0f - 0.5;
        return getRotationFromPosition(d, d10, d11);
    }

    public float[] getPredict(Entity entity){
        double XDiff = entity.posX - entity.lastTickPosX;
        double YDiff = entity.posY - entity.lastTickPosY;
        double ZDiff = entity.posZ - entity.lastTickPosZ;
        float predict = Predict.getVal();
        double x = entity.posX + (XDiff * (predict));
        double y = entity.posY + (YDiff * (predict));
        double z = entity.posZ + (ZDiff * (predict));
        if((YDiff * (predict)) > 1.1){
            y = entity.posY;
        }



        return new float[] {(float) x, (float) y, (float) z};
    }


    @Override
    public void onRender2D() {
        super.onRender2D();
        if(!targethud.getVal()){
            return;
        }
        if(target == null){
            return;
        }
        float Xpos = (GuiScreen.width / 2) + 100;
        float Ypos = (GuiScreen.height) / 2;
        Color color = new Color(30, 30, 30, 150);
        RenderUtils.drawShadowRect(Xpos, Ypos, Xpos + 150, Ypos + 30, 3);
        try {
            drawEntityOnScreen((int) (Xpos + 8), (int) (Ypos + 30), 15, 0, 0, (EntityLivingBase) target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RenderUtil.drawSmoothRect(Xpos + 29, Ypos + 1, Xpos + 29 + fr.getStringWidth(target.getName()), Ypos + fr.getHeight() + 4, color.getRGB());
        fr.drawString(target.getName(), Xpos + 30, Ypos + 3, Color.white.getRGB());
        int health = (int)((((EntityPlayer)target).getHealth() / ((EntityPlayer)target).getMaxHealth()) * 100);
        int health2 = (int)((((EntityPlayer)target).getHealth() / ((EntityPlayer)target).getMaxHealth()) * 360);
        RenderUtil.drawCircle228(Xpos + 135, Ypos + 15, 14,ColorUtils.getHealthColor((EntityLivingBase) target).getRGB(), health2);
        fr.drawString(health + "", Xpos + 135 - ((fr.getStringWidth(health + "")) / 2), Ypos + 15 - (fr.getHeight() / 2), ColorUtils.getHealthColor((EntityLivingBase) target).getRGB());
        int pos = (int) (Xpos + 30);
        RenderUtil.renderItem(((EntityPlayer) target).getHeldItem(EnumHand.MAIN_HAND), pos, (int) Ypos + 14);
        pos += 16;
        for(ItemStack itemStack : ((EntityPlayer) target).getArmorInventoryList()){
            RenderUtil.renderItem(itemStack, pos,  (int) Ypos + 14);
            pos += 16;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        target = gettarget(300, walls.getVal());
        setDisplay(this.getName() + TextFormatting.DARK_PURPLE + " " + Predict.getVal());
        if(target == null){
            return;
        }
        float[] pr = getPredict(target);
        float[] rotation = getRotations(pr[0], (float) pr[1], pr[2], pos.getVal());

        if(Silent.getVal()){
            Rotation.setYaw(rotation[0]);
            Rotation.setPitch(rotation[1]);
        }else {
            mc.player.rotationYaw = rotation[0];
            mc.player.rotationPitch = rotation[1];
        }


        if(timer.hasReached((long) (MathUtils.getRandomInRange(mindeley.getVal(), maxdeley.getVal()))) && mc.player.canEntityBeSeen(new Vec3d(pr[0], pr[1], pr[2])) && AutoShoot.getVal()){
            try {
                Robot bot = new Robot();
                if(mc.currentScreen == null && Display.isActive()){
                    bot.mousePress(InputEvent.BUTTON1_MASK);
                    bot.mouseRelease(InputEvent.BUTTON1_MASK);
                }

            } catch (AWTException e) {
                e.printStackTrace();
            }
            timer.reset();
        }
    }
}
