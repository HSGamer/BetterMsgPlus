package me.polishkrowa.BetterMsgPlus;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class ReplyCommand extends Command {
    private final MsgPlus plugin;

    public ReplyCommand(MsgPlus plugin) {
        super("reply", "Command to reply to another player's private message(s)", "/reply <message>", Collections.singletonList("r"));
        this.plugin = plugin;
        setPermission(Permissions.REPLY.getName());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        if (!plugin.getLastReceived().containsKey(sender)) {
            sender.sendMessage(ChatColor.RED + "You have no one to reply to !");
            return false;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "No message was entered !");
            return false;
        }

        String message = String.join(" ", args);
        message = message.trim();
        CommandSender to = plugin.getLastReceived().get(sender);
        Utils.sendMessage(to, sender, message);

        plugin.getLastReceived().put(sender, to);
        plugin.getLastReceived().put(to, sender);
        return true;
    }
}
