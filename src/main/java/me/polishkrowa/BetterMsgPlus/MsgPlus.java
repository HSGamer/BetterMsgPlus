package me.polishkrowa.BetterMsgPlus;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MsgPlus extends BasePlugin {
    private final Map<CommandSender, CommandSender> lastReceived = new ConcurrentHashMap<>();

    @Override
    public void enable() {
        registerCommand(new ReplyCommand(this));
        registerCommand(new TellCommand(this));
    }

    public Map<CommandSender, CommandSender> getLastReceived() {
        return lastReceived;
    }
}
