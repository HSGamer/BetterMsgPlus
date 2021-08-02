package me.polishkrowa.BetterMsgPlus;

import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.command.CommandSender;

public class Utils {
    private Utils() {
        // EMPTY
    }

    public static void sendMessage(CommandSender to, CommandSender from, String message) {
        TranslatableComponent incoming = new TranslatableComponent("commands.message.display.incoming");
        incoming.setColor(net.md_5.bungee.api.ChatColor.GRAY);
        incoming.addWith(from.getName());
        incoming.addWith(message);
        to.spigot().sendMessage(incoming);

        TranslatableComponent outgoing = new TranslatableComponent("commands.message.display.outgoing");
        outgoing.setColor(net.md_5.bungee.api.ChatColor.GRAY);
        outgoing.addWith(to.getName());
        outgoing.addWith(message);
        from.spigot().sendMessage(outgoing);
    }
}
