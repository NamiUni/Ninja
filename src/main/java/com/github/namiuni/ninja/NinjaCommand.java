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

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@DefaultQualifier(NonNull.class)
public final class NinjaCommand extends BukkitCommand {

    private final NamespacedKey silentJoin;

    public NinjaCommand(final NamespacedKey silentJoin) {
        super("ninja", "サーバー参加通知の表示と非常時を切り替えます", "/ninja", List.of());
        this.setPermission("ninja.command.ninja");
        this.silentJoin = silentJoin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player player) {
            var container = player.getPersistentDataContainer();
            @Nullable Boolean tag = container.get(this.silentJoin, PersistentDataType.BOOLEAN);

            if (Boolean.TRUE.equals(tag)) { // タグがtrueに設定されていればfalseにする
                container.set(this.silentJoin, PersistentDataType.BOOLEAN, false);
                player.sendMessage(Component.text("あなたのサーバー参加通知をオンにしました"));
            } else { // タグが存在しない、若しくはfalseに設定されていればtrueにする
                container.set(this.silentJoin, PersistentDataType.BOOLEAN, true);
                player.sendMessage(Component.text("あなたのサーバー参加通知をオフにしました"));
            }

            return true;
        }

        return false;
    }
}
