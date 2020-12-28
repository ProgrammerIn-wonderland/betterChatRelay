/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miku.immortal.betterminecraftrelay;

import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;

import java.io.IOException;
import java.util.Properties;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author shah3
 */
public class MtoD implements Listener{
	
    
    final private String webhook = (String) main.relay.get("webhook");
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String minecraftHead = ("https://crafatar.com/avatars/" + player.getUniqueId());
        TemmieWebhook temmie = new TemmieWebhook(webhook);
        if(event.getMessage().contains("@everyone") || event.getMessage().contains("@here")) return;
        DiscordMessage dm = DiscordMessage.builder()
            .username(player.getDisplayName())
            .content(event.getMessage()) 
            .avatarUrl(minecraftHead)
            .build();
        temmie.sendMessage(dm);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
    	TemmieWebhook temmie = new TemmieWebhook(webhook);
        DiscordMessage dm = DiscordMessage.builder()
            .username("Server")
            .content(event.getDeathMessage()) 
            .avatarUrl("https://crafatar.com/avatars/6d959fcce0ca44ff8d49f4a2ae9f8de8")
            .build();
        temmie.sendMessage(dm);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	TemmieWebhook temmie = new TemmieWebhook(webhook);
        DiscordMessage dm = DiscordMessage.builder()
            .username("Server")
            .content(event.getJoinMessage().substring(2)) 
            .avatarUrl("https://www.filterforge.com/filters/11635.jpg")
            .build();
        temmie.sendMessage(dm);
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
    	TemmieWebhook temmie = new TemmieWebhook(webhook);
        DiscordMessage dm = DiscordMessage.builder()
            .username("Server")
            .content(event.getQuitMessage().substring(2)) 
            .avatarUrl("https://www.filterforge.com/filters/11635.jpg")
            .build();
        temmie.sendMessage(dm);
    }
}
