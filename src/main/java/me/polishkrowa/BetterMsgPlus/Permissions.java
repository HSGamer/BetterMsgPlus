package me.polishkrowa.BetterMsgPlus;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
    public static final Permission TELL = new Permission("msgplus.tell", PermissionDefault.TRUE);
    public static final Permission REPLY = new Permission("msgplus.reply", PermissionDefault.TRUE);
    public static final Permission TOGGLE = new Permission("msgplus.toggle", PermissionDefault.OP);
    public static final Permission TOGGLE_OTHERS = new Permission("msgplus.toggle.others", PermissionDefault.OP);

    private Permissions() {
        // EMPTY
    }
}
