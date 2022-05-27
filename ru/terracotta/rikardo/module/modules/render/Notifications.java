package ru.terracotta.rikardo.module.modules.render;

import ru.terracotta.rikardo.module.Category;
import ru.terracotta.rikardo.module.Module;
import ru.terracotta.rikardo.utils.notifications.NotificationManager;

public class Notifications extends Module {
    public Notifications() {
        super("Notifications", Category.Render, 0);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        NotificationManager.Render();
    }
}
