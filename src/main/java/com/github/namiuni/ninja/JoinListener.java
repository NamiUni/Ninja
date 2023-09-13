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
