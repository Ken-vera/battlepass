package me.kenvera.battlepass.data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class DataManager {

    private final JavaPlugin plugin;
    private final HashMap<String, DataManager.Config> configs = new HashMap<>();

    public DataManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public Config getConfig(String name) {
        if (!configs.containsKey(name))
            configs.put(name, new Config(name));

        return configs.get(name);
    }

    /**
     * Save the config by the name(Don't forget the .yml)
     *
     */
    public Config saveConfig(String name) {
        return getConfig(name).save();
    }

    /**
     * Reload the config by the name(Don't forget the .yml)
     *
     */
    public Config reloadConfig(String name) {
        return getConfig(name).reload();
    }

    public class Config {

        private final String name;
        private File file;
        private YamlConfiguration config;

        public Config(String name)
        {
            this.name = name;
        }

        /**
         * Saves the config as long as the config isn't empty
         *
         */
        public Config save() {
            if ((this.config == null) || (this.file == null))
                return this;
            try
            {
                if (Objects.requireNonNull(config.getConfigurationSection("")).getKeys(true).size() != 0)
                    config.save(this.file);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            return this;
        }

        /**
         * Gets the config as a YamlConfiguration
         *
         */
        public YamlConfiguration get() {
            if (this.config == null)
                reload();

            return this.config;
        }

        /**
         * Saves the default config(Will overwrite anything in the current config's file)
         * <p>
         * Don't forget to reload after!
         *
         */
        public Config saveDefaultConfig() {
            file = new File(plugin.getDataFolder(), this.name);

            plugin.saveResource(this.name, false);

            return this;
        }

        /**
         * Reloads the config
         * *
         *
         */
        public Config reload() {
            if (file == null)
                this.file = new File(plugin.getDataFolder(), this.name);

            this.config = YamlConfiguration.loadConfiguration(file);

            Reader defConfigStream;
            try
            {
                defConfigStream = new InputStreamReader(Objects.requireNonNull(plugin.getResource(this.name)), StandardCharsets.UTF_8);

                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.config.setDefaults(defConfig);
            }
            catch (NullPointerException ignored)
            {

            }
            return this;
        }

        /**
         * Copies the config from the resources to the config's default settings.
         * <p>
         * Force = true ----> Will add any new values from the default file
         * <p>
         * Force = false ---> Will NOT add new values from the default file
         *
         */
        public Config copyDefaults(boolean force) {
            get().options().copyDefaults(force);
            return this;
        }

        /**
         * An easy way to set a value into the config
         *
         */
        public Config set(String key, Object value) {
            get().set(key, value);
            return this;
        }

        /**
         * An easy way to get a value from the config
         *
         */
        public Object get(String key) {
            return get().get(key);
        }
    }
}