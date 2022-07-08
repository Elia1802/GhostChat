/**
 * @author Elia
 * @plugin GhostMain
 * @Class GhostMain.java
 *
 * @license
 * Copyright (c) 2022 Elia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.elia.ghostchat.events.playerchat.owner;

import de.elia.ghostchat.GhostChat;
import de.elia.ghostchat.plugin.prefix.player.owner.OwnerPrefix;
import de.elia.ghostmain.GhostMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class OwnerChat implements Listener {

    @EventHandler
    public void onOwnerChat(@SuppressWarnings("deprecation") @NotNull AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        String ownerPermissionID = "ghostowner";

        if (GhostMain.getInstance().getPermissionOwnerConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + ownerPermissionID, true)) {
            event.setMessage(OwnerPrefix.getOwnerPrefix() + ChatColor.DARK_RED + player.getName() + ChatColor.DARK_GRAY + ">>" + GhostChat.getInstance().getPrefixOwnerConfiguration().getString("Color") + " " + message);
            event.setFormat(event.getMessage());
        }
    }

}
