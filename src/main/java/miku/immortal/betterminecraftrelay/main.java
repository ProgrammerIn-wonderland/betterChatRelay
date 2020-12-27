/*
 * license: Uhh just use it ig and give me credit please
 */
package miku.immortal.betterminecraftrelay;
// Made by an idiot
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.Embed.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{
    final private String webhook = reader("webhook.txt");
    
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new MtoD(), this);
        final String token = reader("token.txt");
        System.out.println("Token: "+token);
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();
        final String channelID = reader("channelID.txt");
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (!message.getAuthor().isPresent()){
                System.out.println("shouldn't send");
            } else {
                if (message.getChannelId().asString().equals(channelID)) {
                	Bukkit.broadcastMessage("<"+message.getUserData().username()+"> " + message.getContent());
                	if(message.getAttachments().size() != 0) {
                		Bukkit.getServer().dispatchCommand(
                		        Bukkit.getConsoleSender(),
                		        "/tellraw @a {\"text\":\"Message contains Image\",\"bold\":true,\"italic\":true,\"color\":\"aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/tomap" + message.getEmbeds().get(0).getUrl() +"\"}}");
                		}
                	}
                }
        });
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
