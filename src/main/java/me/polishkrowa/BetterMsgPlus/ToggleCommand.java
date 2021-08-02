package me.polishkrowa.BetterMsgPlus;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        if (args.length <= 1) {
            return super.tabComplete(sender, alias, args);
        } else {
            return Collections.emptyList();
        }
    }
}
