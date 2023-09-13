/*
 * Ninja
 *
 * Copyright (c) 2023 NamiUni (Unitarou)
 *                    Contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
