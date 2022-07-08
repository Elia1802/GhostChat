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

package de.elia.ghostchat.events.insultchat.eng;

import de.elia.ghostchat.GhostChat;
import de.elia.ghostmain.all.plugins.prefix.Prefix;
import de.elia.ghostmain.GhostMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.lang.SuppressWarnings;

public class EnglishInsultChat implements Listener {

    @EventHandler
    public void onEnglishInsultChat(@SuppressWarnings("deprecation") AsyncPlayerChatEvent event){
        for (String insult : GhostChat.ENGLISH){
            Player player = event.getPlayer();

            String ownerPermissionID = "ghostowner";
            String adminPermissionID = "ghostadmin";
            String developerPermissionID = "ghostdeveloper";
            String moderatorPermissionID = "ghostmoderator";

            if (insult.equalsIgnoreCase(event.getMessage())){
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Coming Soon");
            }
            if (GhostMain.getInstance().getPermissionOwnerConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + ownerPermissionID , true)) {
                event.setMessage(event.getMessage().replaceAll("(?i)" + insult , ChatColor.RED + "******" + ChatColor.DARK_RED));
                event.setFormat(event.getMessage());
            }else if (GhostMain.getInstance().getPermissionAdminConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + adminPermissionID ,true)) {
                event.setMessage(event.getMessage().replaceAll("(?i)" + insult , ChatColor.RED + "******" + ChatColor.RED));
                event.setFormat(event.getMessage());
            }else if (GhostMain.getInstance().getPermissionDeveloperConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + developerPermissionID ,true)) {
                event.setMessage(event.getMessage().replaceAll("(?i)" + insult , ChatColor.RED + "******" + ChatColor.DARK_PURPLE));
                event.setFormat(event.getMessage());
            }else if (GhostMain.getInstance().getPermissionModeratorConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + moderatorPermissionID ,true)) {
                event.setMessage(event.getMessage().replaceAll("(?i)" + insult , ChatColor.RED + "******" + ChatColor.DARK_AQUA));
                event.setFormat(event.getMessage());
            }else if (!GhostMain.getInstance().getPermissionOwnerConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + ownerPermissionID , true)) {
                if (!GhostMain.getInstance().getPermissionAdminConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + adminPermissionID ,true)) {
                    if (!GhostMain.getInstance().getPermissionDeveloperConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + developerPermissionID ,true)) {
                        if (!GhostMain.getInstance().getPermissionModeratorConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + moderatorPermissionID ,true)) {
                            if (!player.isOp()){
                                event.setMessage(event.getMessage().replaceAll("(?i)" + insult , ChatColor.RED + "******" + ChatColor.GREEN));
                                event.setFormat(event.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

}
