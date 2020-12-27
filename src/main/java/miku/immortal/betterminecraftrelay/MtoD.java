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
        System.out.println("test");
        System.out.println("Recieved message: <" + event.getPlayer()+"> " + event.getMessage()+ "\nto send to webhook: https://discord.com/api/webhooks/766753416771797034/YFtm-f60y4LawAkK27r32tUCOFZ2CqjgEJjnGvIESe2F7Ut0xRBop1MFE-pI2WHkqAFk");
        Player player = event.getPlayer();
        String minecraftHead = ("https://crafatar.com/avatars/" + player.getUniqueId());
        TemmieWebhook temmie = new TemmieWebhook(webhook);
        DiscordMessage dm = DiscordMessage.builder()
            .username(player.getDisplayName())
            .content(event.getMessage()) 
            .avatarUrl(minecraftHead)
            .build();
        temmie.sendMessage(dm);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
    	System.out.println("Player died");
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
    	System.out.println("Player died");
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
    	System.out.println("Player Left");
    	TemmieWebhook temmie = new TemmieWebhook(webhook);
        DiscordMessage dm = DiscordMessage.builder()
            .username("Server")
            .content(event.getQuitMessage().substring(2)) 
            .avatarUrl("https://www.filterforge.com/filters/11635.jpg")
            .build();
        temmie.sendMessage(dm);
    }
}
