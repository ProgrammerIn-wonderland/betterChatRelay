/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miku.immortal.betterminecraftrelay;

import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author shah3
 */
public class MtoD implements Listener{
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        System.out.println("test");
        System.out.println("Recieved message: <" + event.getPlayer()+"> " + event.getMessage()+ "\nto send to webhook: https://discord.com/api/webhooks/766753416771797034/YFtm-f60y4LawAkK27r32tUCOFZ2CqjgEJjnGvIESe2F7Ut0xRBop1MFE-pI2WHkqAFk");
        Player player = event.getPlayer();
        String minecraftHead = ("https://crafatar.com/avatars/" + player.getUniqueId());
        TemmieWebhook temmie = new TemmieWebhook("https://discord.com/api/webhooks/766753416771797034/YFtm-f60y4LawAkK27r32tUCOFZ2CqjgEJjnGvIESe2F7Ut0xRBop1MFE-pI2WHkqAFk");
        DiscordMessage dm = DiscordMessage.builder()
            .username(player.getDisplayName())
            .content(event.getMessage()) 
            .avatarUrl(minecraftHead)
            .build();
        temmie.sendMessage(dm);
    }
}
