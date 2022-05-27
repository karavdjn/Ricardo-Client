package ru.terracotta.rikardo.module.modules.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import ru.terracotta.rikardo.Main;
import ru.terracotta.rikardo.managers.FriendManager;
import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.module.setting.settings.BooleanSetting;

public class WallHack extends Module {
    private BooleanSetting friends;
    public WallHack() {
        super("WallHack", Category.Render, 0);
        Main.settingManager.add(friends = new BooleanSetting("Friends", this, false));
    }

    void render(Entity entity, float ticks) {
        try {
            if (entity == null || entity == mc.player) {
                return;
            }
            if (entity == mc.getRenderViewEntity() && mc.gameSettings.thirdPersonView == 0) {
                return;
            }
            mc.entityRenderer.disableLightmap();
            mc.getRenderManager().renderEntityStatic(entity, ticks, false);
            mc.entityRenderer.enableLightmap();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        GlStateManager.clear((int)256);
        RenderHelper.enableStandardItemLighting();
        for (Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity != mc.getRenderViewEntity()) {
                if (FriendManager.isFriend(entity.getName()) && friends.getVal()) {
                    this.render(entity, mc.getRenderPartialTicks());
                    continue;
                }
                this.render(entity, mc.getRenderPartialTicks());
                continue;
            }
        }

    }
}
