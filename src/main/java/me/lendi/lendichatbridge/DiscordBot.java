package me.lendi.lendichatbridge;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter {
    public static JDA api;
    public static void main() throws LoginException, InterruptedException {
        String discordToken = ConfigHandler.config.discordToken;

        api = JDABuilder.createLight(discordToken, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new DiscordBot())
                .setActivity(Activity.playing("//TODO: Show how many players are online here!"))
                .build();
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (!event.getMessage().getAuthor().isBot() && event.getChannel().getId().equals(ConfigHandler.config.discordChannelID)) {
            MinecraftPlugin.broadcast(event.getMessage().getContentStripped(), event.getAuthor().getName());
        }
    }

    public  static void sendToDiscord (String msg) throws InterruptedException {
        api.awaitReady();
        api.getTextChannelById(ConfigHandler.config.discordChannelID).sendMessage(msg).queue();
    }
}