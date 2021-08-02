package me.polishkrowa.BetterMsgPlus;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class MsgPlus extends BasePlugin {
    private final Map<CommandSender, CommandSender> lastReceived = new ConcurrentHashMap<>();
    private final Set<CommandSender> ignored = new HashSet<>();

    @Override
    public void enable() {
        MessageUtils.setPrefix("&f[&bMsgPlus&f] &r");
        registerCommand(new ReplyCommand(this));
        registerCommand(new TellCommand(this));
        registerCommand(new ToggleCommand(this));
    }

    public Map<CommandSender, CommandSender> getLastReceived() {
        return lastReceived;
    }

    public Set<CommandSender> getIgnored() {
        return ignored;
    }
}
