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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.Objects;

@DefaultQualifier(NonNull.class)
public final class JoinListener implements Listener {

    private final NamespacedKey silentJoin;

    public JoinListener(final NamespacedKey silentJoin) {
        this.silentJoin = silentJoin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        var container = player.getPersistentDataContainer();
        if (container.has(this.silentJoin, PersistentDataType.BOOLEAN)) {
            var isSilentJoin = Objects.requireNonNull(container.get(silentJoin, PersistentDataType.BOOLEAN));

            // タグがtrueに設定されていれば参加メッセージを空にする。
            if (Boolean.TRUE.equals(isSilentJoin)) {
                event.joinMessage(Component.empty());
                var playerMessage = Component.text("あなたはサーバー参加を他のプレイヤーに通知していません。")
                        .append(Component.newline())
                        .append(Component.text("変更するには/ninjaコマンドを実行してください。"));
                player.sendMessage(playerMessage);
            }
        }
    }
}
