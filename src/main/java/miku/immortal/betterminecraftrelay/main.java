/*
 * license: Uhh just use it ig and give me credit please
 */
package miku.immortal.betterminecraftrelay;
// Made by an idiot
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
 
    @Override
    public void onEnable(){
        final String token = reader("token.txt");
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();
        final String channelID = reader("channelID.txt");
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (message.getChannelId().toString() == channelID) {
                Bukkit.broadcastMessage("<"+message.getUserData().username()+"> " + message.getContent());
            }
        });
    }
     @EventHandler
     public void onPlayerChat(AsyncPlayerChatEvent event) {
               
        Player player = event.getPlayer();
        String minecraftHead = ("https://crafatar.com/avatars/" + player.getUniqueId());
        TemmieWebhook temmie = new TemmieWebhook(reader("webhook.txt"));
        DiscordMessage dm = DiscordMessage.builder()
            .username(player.getDisplayName())
            .content(event.getMessage()) 
            .avatarUrl(minecraftHead)
            .build();
        temmie.sendMessage(dm);
     }

    @Override
    public void onDisable(){
        System.out.println("Disabling this plugin works.");
    }
    public static String reader(String filename) {  
        try {
                File myObj = new File(filename);
                Scanner myReader = new Scanner(myObj);  
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    return data;
            }
            myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } 
        return null;
  }

}
