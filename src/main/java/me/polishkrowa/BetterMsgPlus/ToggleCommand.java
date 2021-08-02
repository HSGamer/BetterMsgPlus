package me.polishkrowa.BetterMsgPlus;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.hsgamer.hscore.bukkit.utils.MessageUtils.sendMessage;

public class ToggleCommand extends Command {
    private final MsgPlus plugin;

    public ToggleCommand(MsgPlus plugin) {
        super("msgtoggle", "Toggle receiving private messages", "/msgtoggle", Arrays.asList("telltoggle", "rtoggle"));
        this.plugin = plugin;
        setPermission(Permissions.TOGGLE.getName());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        CommandSender who = sender;
        if (args.length > 0 && sender.hasPermission(Permissions.TOGGLE_OTHERS)) {
            who = Bukkit.getPlayerExact(args[0]);
            if (who == null) {
                sendMessage(sender, "&cNo Player Found!");
                return false;
            }
        }
        Set<CommandSender> ignored = plugin.getIgnored();
        if (ignored.contains(who)) {
            ignored.remove(who);
            sendMessage(sender, "&aNow ignore all private messages");
        } else {
            ignored.add(who);
            sendMessage(sender, "&aNow allow all private messages");
        }
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
