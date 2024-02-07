package net.micphone.ai.untitled;

import org.bukkit.plugin.java.JavaPlugin;

public final class Untitled extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("prompt").setExecutor(new PromptCommandExecutor());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
