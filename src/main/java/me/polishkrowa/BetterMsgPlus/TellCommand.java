package me.polishkrowa.BetterMsgPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TellCommand extends Command {
    private final MsgPlus plugin;

    public TellCommand(MsgPlus plugin) {
        super("tell", "Command to private message players", "/tell <player> <message>", Collections.singletonList("msg"));
        this.plugin = plugin;
        setPermission(Permissions.TELL.getName());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
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
        if (args.length == 1) {
            String start = args[0].trim().toLowerCase(Locale.ROOT);
            Stream<String> stream = Bukkit.getOnlinePlayers().stream().map(Player::getName);
            if (!start.isEmpty()) {
                stream = stream.filter(s -> s.toLowerCase(Locale.ROOT).startsWith(start));
            }
            return stream.collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
