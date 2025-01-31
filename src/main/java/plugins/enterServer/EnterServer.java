package plugins.enterServer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import java.time.Duration;
import org.bukkit.ChatColor;

public final class EnterServer extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Title title = Title.title(
                Component.text(ChatColor.DARK_GREEN + "WITAJ NA SERWERZE"),
                Component.text(ChatColor.DARK_GREEN + player.getName()),
                Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(4), Duration.ofSeconds(1))
        );
        player.showTitle(title);
        actionBarMessageElse(player, "dolaczyl na serwer!", ChatColor.DARK_GREEN);
        event.setJoinMessage(null);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        actionBarMessageElse(player, "rozlaczyl sie!", ChatColor.DARK_RED);
        event.setQuitMessage(null);
    }
    public void actionBarMessageElse(Player player, String message, ChatColor color)
    {
        String acitonBarMess = player.getName()+ " " + message;
        Component othersWelcomeMessage = Component.text(color + acitonBarMess);
        getServer().getOnlinePlayers().forEach(onlinePlayer -> {
            if(onlinePlayer != player)
                onlinePlayer.sendActionBar(othersWelcomeMessage);
        });
    }
}