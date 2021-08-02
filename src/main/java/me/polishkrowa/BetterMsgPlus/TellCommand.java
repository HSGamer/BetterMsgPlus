package me.polishkrowa.BetterMsgPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TellCommand extends Command {
    private final MsgPlus plugin;

    public TellCommand(MsgPlus plugin) {
        super("tell", "Command to private message players", "/tell <player> <message>", Collections.singletonList("msg"));
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "No player name was entered. Correct usage: /msg <player-name> <message>");
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "No message was entered !");
            return false;
        }

        Player to = Bukkit.getPlayerExact(args[0]);
        if (to == null) {
            sender.sendMessage(ChatColor.RED + "No Player Found !");
            return false;
        }

        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        Utils.sendMessage(to, sender, message);

        plugin.getLastReceived().put(sender, to);
        plugin.getLastReceived().put(to, sender);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (args.length <= 1) {
            return super.tabComplete(sender, alias, args);
        } else {
            return Collections.emptyList();
        }
    }
}
