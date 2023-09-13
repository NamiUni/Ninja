package com.github.namiuni.ninja;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public final class Ninja extends JavaPlugin {

    private final NamespacedKey silentJoin = new NamespacedKey(this, "silent-join");

    @Override
    public void onEnable() {
        // Command登録
        var ninjaCommand = new NinjaCommand(this.silentJoin);
        this.getServer().getCommandMap().register("ninja", ninjaCommand);

        // Listener登録
        var joinListener = new JoinListener(this.silentJoin);
        this.getServer().getPluginManager().registerEvents(joinListener, this);
    }
}
