package me.lendi.lendichatbridge;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class MinecraftPlugin extends Plugin implements Listener {
    @Override
    public void onEnable() {
        // You should not put an enable message in your plugin.
        // BungeeCord already does so
        super.getProxy().getPluginManager().registerListener(this, this);

        getLogger().info("DiscordBridge is running!");
        getLogger().info("Working dir: " + System.getProperty("user.dir"));
        ConfigHandler.get_config(true);

        try {
            DiscordBot.main();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public void onChat(ChatEvent event) throws InterruptedException {
        if (event.getMessage().charAt(0) != '/') {
            DiscordBot.sendToDiscord("<"+event.getSender()+"> "+event.getMessage());

        }
    }

    public static void broadcast(String msg, String sender)
    {
        TextComponent message = new TextComponent("DISCORD:"+'<'+sender+"> "+msg);
        //message.setColor(net.md_5.bungee.api.ChatColor.RED);
        //message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org"));
        ProxyServer.getInstance().broadcast(message);
    }
}
