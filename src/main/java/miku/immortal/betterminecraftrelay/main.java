/*
 * license: Uhh just use it ig and give me credit please
 */
package miku.immortal.betterminecraftrelay;
// Made by an idiot
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin implements Listener{
    public static Properties relay = null;
    @Override
    public void onEnable(){
    	
		try {
			relay = loadProperties();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        getServer().getPluginManager().registerEvents(new MtoD(), this);
        final String token = (String) relay.get("token");
        System.out.println("Token: "+token);
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();
        final String channelID = (String) relay.get("channelID");
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (!message.getAuthor().isPresent()){
                System.out.println("shouldn't send");
            } else {
                if (message.getChannelId().asString().equals(channelID)) {
                	Bukkit.broadcastMessage("<"+message.getUserData().username()+"> " + message.getContent());
                	message.getAttachments().forEach(attachement -> {
                			System.out.println(attachement.getUrl());
                			try {
								boolean success = Bukkit.getScheduler().callSyncMethod( this, () -> Bukkit.dispatchCommand( Bukkit.getConsoleSender(), "tellraw @a {\"text\":\"Message contains Image\",\"bold\":true,\"italic\":true,\"color\":\"aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/tomap " + attachement.getUrl() +"\"}}" ) ).get();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                			
                		}
                	);
                }
            }});
    }
    public static void saveProperties(Properties p) throws IOException
    {
        FileOutputStream fr = new FileOutputStream("relay.properties");
        p.store(fr, "Properties");
        fr.close();
        System.out.println("After saving properties: " + p);
    }

    static Properties loadProperties()throws IOException
    {
        FileInputStream fi=new FileInputStream("relay.properties");
        Properties p = new Properties();
        p.load(fi);
        fi.close();
        return p;
    }
    

    @Override
    public void onDisable(){
        System.out.println("Disabling Chat Relay");
    }

}
