package me.lendi.lendichatbridge;

import java.io.*;
import java.util.Properties;

import static java.lang.System.out;

public class ConfigHandler {
    public static Config config = new Config();

    public static void get_config(boolean isFirstTry)  {
        //Attempting loading from LendiBridge.properties file
        try {
            Properties mainProperties = new Properties();
            FileInputStream file;
            String path = "./plugins/LendiBridge.properties";
            file = new FileInputStream(path);
            mainProperties.load(file);
            file.close();

            //Basic data
            config.version = mainProperties.getProperty("version");

            //Discord data
            config.discordToken = mainProperties.getProperty("discordToken");
            config.discordChannelID = mainProperties.getProperty("discordChannelID");

            //Minecraft data
            config.minecraftChatPrefix = mainProperties.getProperty("minecraftChatPrefix");
            config.minecraftChatSulfix = mainProperties.getProperty("minecraftChatSulfix");

        } catch (FileNotFoundException e) {
            if (isFirstTry){
                out.println("[ERROR]: Config file missing! Creating one for you...\n" +
                        "[INFO]: Expect errors! To configure LendiBridge, edit the LendiBridge.properties file!");
                make_config();
                get_config(false);
            } else {
                out.println("[ERROR]: Config file missing! Creating new file failed!");
            }

        } catch (IOException e) {
            out.println("[ERROR]: IOException error!");

        } catch (NullPointerException e){
            out.println("[ERROR]: Null pointer exception!");

        }
    }

    public static void make_config(){
        try {
            FileWriter newConfigFile = new FileWriter("./plugins/LendiBridge.properties");
            newConfigFile.write("version=0.0.3\n\n" +
                    "discordToken=Create-a-discord-bot-and-enter-it's-token-here!\n" +
                    "discordChannelID=With-developer-mode,-right-click-on-a-channel-and-copy-your-ID!\n\n" +

                    "minecraftChatPrefix=[DISCORD](This does nothing ATM)\n" +
                    "minecraftChatSulfix= \n");
            newConfigFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Config {
    String version;

    String discordToken;
    String discordChannelID;

    String minecraftChatPrefix;
    String minecraftChatSulfix;
}
