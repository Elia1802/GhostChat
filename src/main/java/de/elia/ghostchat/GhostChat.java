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

package de.elia.ghostchat;

import de.elia.ghostchat.events.insultchat.de.GermanInsultChat;
import de.elia.ghostchat.events.insultchat.eng.EnglishInsultChat;
import de.elia.ghostchat.events.playerchat.admin.AdminChat;
import de.elia.ghostchat.events.playerchat.developer.DeveloperChat;
import de.elia.ghostchat.events.playerchat.moderator.ModeratorChat;
import de.elia.ghostchat.events.playerchat.owner.OwnerChat;
import de.elia.ghostchat.events.playerchat.player.PlayerChat;
import de.elia.ghostchat.plugin.config.GhostChatConfig;
import de.elia.ghostchat.plugin.prefix.error.ErrorPrefix;
import de.elia.ghostchat.plugin.updater.UpdateEvent;
import de.elia.ghostmain.GhostMain;
import de.elia.ghostmain.all.plugins.prefix.Prefix;
import de.elia.ghostmain.all.plugins.updater.Updater;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class GhostChat extends JavaPlugin {

    public static final GhostMain ghostMain = (GhostMain) Bukkit.getPluginManager().getPlugin("GhostMain");

    private static GhostChat instance;

    private static final MiniMessage mm = MiniMessage.miniMessage();

    public static final List<String> GERMAN = new ArrayList<String>();

    public static final List<String> ENGLISH = new ArrayList<String>();

    public final List<String> prefixOwner = new ArrayList<String>();

    public final List<String> prefixAdmin = new ArrayList<String>();

    public final List<String> prefixDeveloper = new ArrayList<String>();

    public final List<String> prefixModerator = new ArrayList<String>();

    public final List<String> prefixPlayer = new ArrayList<String>();

    public final GhostChatConfig prefixOwnerConfiguration = new GhostChatConfig(this , "prefix/owner/" , "PrefixOwnerConfiguration.yml");

    public final GhostChatConfig prefixAdminConfiguration = new GhostChatConfig(this , "prefix/admin/" , "PrefixAdminConfiguration.yml");

    public final GhostChatConfig prefixDeveloperConfiguration = new GhostChatConfig(this , "prefix/developer/" , "PrefixDeveloperConfiguration.yml");

    public final GhostChatConfig prefixModeratorConfiguration = new GhostChatConfig(this , "prefix/moderator/" , "PrefixModeratorConfiguration.yml");

    public final GhostChatConfig prefixPlayerConfiguration = new GhostChatConfig(this , "prefix/player/" , "PrefixPlayerConfiguration.yml");

    @Override
    public void onEnable() {
        if (ghostMain != null) {
            instance = this;
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "Start GhostChat");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Minecraft Version 1.18 - 1.18.2");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "load Events");
                    Bukkit.getPluginManager().registerEvents(new OwnerChat() , this);
                    Bukkit.getPluginManager().registerEvents(new AdminChat() , this);
                    Bukkit.getPluginManager().registerEvents(new DeveloperChat() , this);
                    Bukkit.getPluginManager().registerEvents(new ModeratorChat() , this);
                    Bukkit.getPluginManager().registerEvents(new PlayerChat() , this);
                    Bukkit.getPluginManager().registerEvents(new GermanInsultChat(), this);
                    Bukkit.getPluginManager().registerEvents(new EnglishInsultChat() , this);
                    Bukkit.getPluginManager().registerEvents(new UpdateEvent() , this);
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Events loaded");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "load Config");
                    //Prefix OwnerConfiguration
                    prefixOwner.add("Here you can change the role name from the role Owner");
                    GhostChat.getInstance().getPrefixOwnerConfiguration().setHeader(prefixOwner);
                    GhostChat.getInstance().getPrefixOwnerConfiguration().addDefault("Roles Name" , "§4Ghost Owner");
                    GhostChat.getInstance().getPrefixOwnerConfiguration().addDefault("Color" , "§4");
                    GhostChat.getInstance().getPrefixOwnerConfiguration().options().copyDefaults(true);
                    GhostChat.getInstance().getPrefixOwnerConfiguration().save();
                    //Prefix AdminConfiguration
                    prefixAdmin.add("Here you can change the role name from the role Admin");
                    GhostChat.getInstance().getPrefixAdminConfiguration().setHeader(prefixAdmin);
                    GhostChat.getInstance().getPrefixAdminConfiguration().addDefault("Roles Name" , "§cGhost Admin");
                    GhostChat.getInstance().getPrefixAdminConfiguration().addDefault("Color" , "§c");
                    GhostChat.getInstance().getPrefixAdminConfiguration().options().copyDefaults(true);
                    GhostChat.getInstance().getPrefixAdminConfiguration().save();
                    //Prefix DeveloperConfiguration
                    prefixDeveloper.add("Here you can change the role name from the role Developer");
                    GhostChat.getInstance().getPrefixDeveloperConfiguration().setHeader(prefixDeveloper);
                    GhostChat.getInstance().getPrefixDeveloperConfiguration().addDefault("Roles Name" , "§5Ghost Developer");
                    GhostChat.getInstance().getPrefixDeveloperConfiguration().addDefault("Color" , "§5");
                    GhostChat.getInstance().getPrefixDeveloperConfiguration().options().copyDefaults(true);
                    GhostChat.getInstance().getPrefixDeveloperConfiguration().save();
                    //Prefix ModeratorConfiguration
                    prefixModerator.add("Here you can change the role name from the role Moderator");
                    GhostChat.getInstance().getPrefixModeratorConfiguration().setHeader(prefixModerator);
                    GhostChat.getInstance().getPrefixModeratorConfiguration().addDefault("Roles Name" , "§3Ghost Moderator");
                    GhostChat.getInstance().getPrefixModeratorConfiguration().addDefault("Color" , "§3");
                    GhostChat.getInstance().getPrefixModeratorConfiguration().options().copyDefaults(true);
                    GhostChat.getInstance().getPrefixModeratorConfiguration().save();
                    //Prefix PlayerConfiguration
                    prefixPlayer.add("Here you can change the role name from the role Player");
                    GhostChat.getInstance().getPrefixPlayerConfiguration().setHeader(prefixPlayer);
                    GhostChat.getInstance().getPrefixPlayerConfiguration().addDefault("Roles Name" , "§aGhost Player");
                    GhostChat.getInstance().getPrefixPlayerConfiguration().addDefault("Color" , "§a");
                    GhostChat.getInstance().getPrefixPlayerConfiguration().options().copyDefaults(true);
                    GhostChat.getInstance().getPrefixPlayerConfiguration().save();
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Config loaded");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "load Lists");
                    GERMAN.add("analritter");
                    GERMAN.add("arsch");
                    GERMAN.add("arschficker");
                    GERMAN.add("arschlecker");
                    GERMAN.add("arschloch");
                    GERMAN.add("bimbo");
                    GERMAN.add("bratze");
                    GERMAN.add("bumsen");
                    GERMAN.add("bonze");
                    GERMAN.add("dödel");
                    GERMAN.add("fick");
                    GERMAN.add("ficken");
                    GERMAN.add("flittchen");
                    GERMAN.add("fotze");
                    GERMAN.add("fratze");
                    GERMAN.add("hackfresse");
                    GERMAN.add("hure");
                    GERMAN.add("hurensohn");
                    GERMAN.add("ische");
                    GERMAN.add("kackbratze");
                    GERMAN.add("kacke");
                    GERMAN.add("kacken");
                    GERMAN.add("kackwurst");
                    GERMAN.add("kampflesbe");
                    GERMAN.add("kanake");
                    GERMAN.add("kimme");
                    GERMAN.add("lümmel");
                    GERMAN.add("MILF");
                    GERMAN.add("möpse");
                    GERMAN.add("morgenlatte");
                    GERMAN.add("möse");
                    GERMAN.add("mufti");
                    GERMAN.add("muschi");
                    GERMAN.add("nackt");
                    GERMAN.add("neger");
                    GERMAN.add("nigger");
                    GERMAN.add("nippel");
                    GERMAN.add("nutte");
                    GERMAN.add("onanieren");
                    GERMAN.add("orgasmus");
                    GERMAN.add("penis");
                    GERMAN.add("pimmel");
                    GERMAN.add("pimpern");
                    GERMAN.add("pinkeln");
                    GERMAN.add("pissen");
                    GERMAN.add("pisser");
                    GERMAN.add("popel");
                    GERMAN.add("poppen");
                    GERMAN.add("porno");
                    GERMAN.add("reudig");
                    GERMAN.add("rosette");
                    GERMAN.add("schabracke");
                    GERMAN.add("schlampe");
                    GERMAN.add("scheiße");
                    GERMAN.add("scheisser");
                    GERMAN.add("scheisse");
                    GERMAN.add("scheise");
                    GERMAN.add("schiesser");
                    GERMAN.add("schnackeln");
                    GERMAN.add("schnackseln");
                    GERMAN.add("schwanzlutscher");
                    GERMAN.add("schwuchtel");
                    GERMAN.add("tittchen");
                    GERMAN.add("titten");
                    GERMAN.add("vögeln");
                    GERMAN.add("vollpfosten");
                    GERMAN.add("wichse");
                    GERMAN.add("wichsen");
                    GERMAN.add("wichser");
                    //English
                    ENGLISH.add("2g1c");
                    ENGLISH.add("2 girls 1 cup");
                    ENGLISH.add("acrotomophilia");
                    ENGLISH.add("alabama hot pocket");
                    ENGLISH.add("alaskan pipeline");
                    ENGLISH.add("anal");
                    ENGLISH.add("anilingus");
                    ENGLISH.add("anus");
                    ENGLISH.add("apeshit");
                    ENGLISH.add("arsehole");
                    ENGLISH.add("ass");
                    ENGLISH.add("asshole");
                    ENGLISH.add("assmunch");
                    ENGLISH.add("auto erotic");
                    ENGLISH.add("autoerotic");
                    ENGLISH.add("babeland");
                    ENGLISH.add("baby batter");
                    ENGLISH.add("baby juice");
                    ENGLISH.add("ball gag");
                    ENGLISH.add("ball gravy");
                    ENGLISH.add("ball kicking");
                    ENGLISH.add("ball licking");
                    ENGLISH.add("ball sack");
                    ENGLISH.add("ball sucking");
                    ENGLISH.add("bangbros");
                    ENGLISH.add("bangbus");
                    ENGLISH.add("bareback");
                    ENGLISH.add("barely legal");
                    ENGLISH.add("barenaked");
                    ENGLISH.add("bastard");
                    ENGLISH.add("bastardo");
                    ENGLISH.add("bastinado");
                    ENGLISH.add("bbw");
                    ENGLISH.add("bdsm");
                    ENGLISH.add("beaner");
                    ENGLISH.add("beaners");
                    ENGLISH.add("beaver cleaver");
                    ENGLISH.add("beaver lips");
                    ENGLISH.add("beastiality");
                    ENGLISH.add("bestiality");
                    ENGLISH.add("big black");
                    ENGLISH.add("big breasts");
                    ENGLISH.add("big knockers");
                    ENGLISH.add("big tits");
                    ENGLISH.add("bimbos");
                    ENGLISH.add("birdlock");
                    ENGLISH.add("bitch");
                    ENGLISH.add("bitches");
                    ENGLISH.add("black cock");
                    ENGLISH.add("blonde action");
                    ENGLISH.add("blonde on blonde action");
                    ENGLISH.add("blowjob");
                    ENGLISH.add("blow job");
                    ENGLISH.add("blow your load");
                    ENGLISH.add("blue waffle");
                    ENGLISH.add("blumpkin");
                    ENGLISH.add("bollocks");
                    ENGLISH.add("bondage");
                    ENGLISH.add("boner");
                    ENGLISH.add("boob");
                    ENGLISH.add("boobs");
                    ENGLISH.add("booty call");
                    ENGLISH.add("brown showers");
                    ENGLISH.add("brunette action");
                    ENGLISH.add("bukkake");
                    ENGLISH.add("bulldyke");
                    ENGLISH.add("bullet vibe");
                    ENGLISH.add("bullshit");
                    ENGLISH.add("bung hole");
                    ENGLISH.add("bunghole");
                    ENGLISH.add("busty");
                    ENGLISH.add("butt");
                    ENGLISH.add("butthole");
                    ENGLISH.add("buttcheeks");
                    ENGLISH.add("camel toe");
                    ENGLISH.add("camgirl");
                    ENGLISH.add("camslut");
                    ENGLISH.add("camwhore");
                    ENGLISH.add("carpet muncher");
                    ENGLISH.add("carpetmuncher");
                    ENGLISH.add("chocolate rosebuds");
                    ENGLISH.add("cialis");
                    ENGLISH.add("circlejerk");
                    ENGLISH.add("cleveland steamer");
                    ENGLISH.add("clit");
                    ENGLISH.add("clitoris");
                    ENGLISH.add("clover clamps");
                    ENGLISH.add("clusterfuck");
                    ENGLISH.add("cock");
                    ENGLISH.add("cocks");
                    ENGLISH.add("coprolagnia");
                    ENGLISH.add("coprophilia");
                    ENGLISH.add("cornhole");
                    ENGLISH.add("coon");
                    ENGLISH.add("coons");
                    ENGLISH.add("creampie");
                    ENGLISH.add("cum");
                    ENGLISH.add("cumming");
                    ENGLISH.add("cumshot");
                    ENGLISH.add("cumshots");
                    ENGLISH.add("cunnilingus");
                    ENGLISH.add("cunt");
                    ENGLISH.add("darkie");
                    ENGLISH.add("date rape");
                    ENGLISH.add("daterape");
                    ENGLISH.add("deep throat");
                    ENGLISH.add("deepthroat");
                    ENGLISH.add("dendrophilia");
                    ENGLISH.add("dick");
                    ENGLISH.add("dildo");
                    ENGLISH.add("dingleberry");
                    ENGLISH.add("dingleberries");
                    ENGLISH.add("dirty pillows");
                    ENGLISH.add("dirty sanchez");
                    ENGLISH.add("doggie style");
                    ENGLISH.add("doggiestyle");
                    ENGLISH.add("doggy style");
                    ENGLISH.add("doggystyle");
                    ENGLISH.add("dog style");
                    ENGLISH.add("dolcett");
                    ENGLISH.add("domination");
                    ENGLISH.add("dominatrix");
                    ENGLISH.add("dommes");
                    ENGLISH.add("donkey punch");
                    ENGLISH.add("double dong");
                    ENGLISH.add("double penetration");
                    ENGLISH.add("dp action");
                    ENGLISH.add("dry hump");
                    ENGLISH.add("dvda");
                    ENGLISH.add("eat my ass");
                    ENGLISH.add("ecchi");
                    ENGLISH.add("ejaculation");
                    ENGLISH.add("erotic");
                    ENGLISH.add("erotism");
                    ENGLISH.add("escort");
                    ENGLISH.add("eunuch");
                    ENGLISH.add("fag");
                    ENGLISH.add("faggot");
                    ENGLISH.add("fecal");
                    ENGLISH.add("felch");
                    ENGLISH.add("fellatio");
                    ENGLISH.add("feltch");
                    ENGLISH.add("female squirting");
                    ENGLISH.add("femdom");
                    ENGLISH.add("figging");
                    ENGLISH.add("fingerbang");
                    ENGLISH.add("fingering");
                    ENGLISH.add("fisting");
                    ENGLISH.add("foot fetish");
                    ENGLISH.add("footjob");
                    ENGLISH.add("frotting");
                    ENGLISH.add("fuck");
                    ENGLISH.add("fuck buttons");
                    ENGLISH.add("fuckin");
                    ENGLISH.add("fucking");
                    ENGLISH.add("fucktards");
                    ENGLISH.add("fudge packer");
                    ENGLISH.add("fudgepacker");
                    ENGLISH.add("futanari");
                    ENGLISH.add("gangbang");
                    ENGLISH.add("gang bang");
                    ENGLISH.add("gay sex");
                    ENGLISH.add("genitals");
                    ENGLISH.add("giant cock");
                    ENGLISH.add("girl on");
                    ENGLISH.add("girl on top");
                    ENGLISH.add("girls gone wild");
                    ENGLISH.add("goatcx");
                    ENGLISH.add("goatse");
                    ENGLISH.add("god damn");
                    ENGLISH.add("gokkun");
                    ENGLISH.add("golden shower");
                    ENGLISH.add("goodpoop");
                    ENGLISH.add("goo girl");
                    ENGLISH.add("goregasm");
                    ENGLISH.add("grope");
                    ENGLISH.add("group sex");
                    ENGLISH.add("g-spot");
                    ENGLISH.add("guro");
                    ENGLISH.add("hand job");
                    ENGLISH.add("handjob");
                    ENGLISH.add("hard core");
                    ENGLISH.add("hardcore");
                    ENGLISH.add("hentai");
                    ENGLISH.add("homoerotic");
                    ENGLISH.add("honkey");
                    ENGLISH.add("hooker");
                    ENGLISH.add("horny");
                    ENGLISH.add("hot carl");
                    ENGLISH.add("hot chick");
                    ENGLISH.add("how to kill");
                    ENGLISH.add("how to murder");
                    ENGLISH.add("huge fat");
                    ENGLISH.add("humping");
                    ENGLISH.add("incest");
                    ENGLISH.add("intercourse");
                    ENGLISH.add("jack off");
                    ENGLISH.add("jail bait");
                    ENGLISH.add("jailbait");
                    ENGLISH.add("jelly donut");
                    ENGLISH.add("jerk off");
                    ENGLISH.add("jigaboo");
                    ENGLISH.add("jiggaboo");
                    ENGLISH.add("jiggerboo");
                    ENGLISH.add("jizz");
                    ENGLISH.add("juggs");
                    ENGLISH.add("kike");
                    ENGLISH.add("kinbaku");
                    ENGLISH.add("kinkster");
                    ENGLISH.add("kinky");
                    ENGLISH.add("knobbing");
                    ENGLISH.add("leather restraint");
                    ENGLISH.add("leather straight jacket");
                    ENGLISH.add("lemon party");
                    ENGLISH.add("livesex");
                    ENGLISH.add("lolita");
                    ENGLISH.add("lovemaking");
                    ENGLISH.add("make me come");
                    ENGLISH.add("male squirting");
                    ENGLISH.add("masturbate");
                    ENGLISH.add("masturbating");
                    ENGLISH.add("masturbation");
                    ENGLISH.add("menage a trois");
                    ENGLISH.add("milf");
                    ENGLISH.add("missionary position");
                    ENGLISH.add("mong");
                    ENGLISH.add("motherfucker");
                    ENGLISH.add("mound of venus");
                    ENGLISH.add("mr hands");
                    ENGLISH.add("muff diver");
                    ENGLISH.add("muffdiving");
                    ENGLISH.add("nambla");
                    ENGLISH.add("nawashi");
                    ENGLISH.add("negro");
                    ENGLISH.add("neonazi");
                    ENGLISH.add("nigga");
                    ENGLISH.add("nigger");
                    ENGLISH.add("nig nog");
                    ENGLISH.add("nimphomania");
                    ENGLISH.add("nipple");
                    ENGLISH.add("nipples");
                    ENGLISH.add("nsfw");
                    ENGLISH.add("nsfw images");
                    ENGLISH.add("nude");
                    ENGLISH.add("nudity");
                    ENGLISH.add("nutten");
                    ENGLISH.add("nympho");
                    ENGLISH.add("nymphomania");
                    ENGLISH.add("octopussy");
                    ENGLISH.add("omorashi");
                    ENGLISH.add("one cup two girls");
                    ENGLISH.add("one guy one jar");
                    ENGLISH.add("orgasm");
                    ENGLISH.add("orgy");
                    ENGLISH.add("paedophile");
                    ENGLISH.add("paki");
                    ENGLISH.add("panties");
                    ENGLISH.add("panty");
                    ENGLISH.add("pedobear");
                    ENGLISH.add("pedophile");
                    ENGLISH.add("pegging");
                    ENGLISH.add("penis");
                    ENGLISH.add("phone sex");
                    ENGLISH.add("piece of shit");
                    ENGLISH.add("pikey");
                    ENGLISH.add("pissing");
                    ENGLISH.add("piss pig");
                    ENGLISH.add("pisspig");
                    ENGLISH.add("playboy");
                    ENGLISH.add("pleasure chest");
                    ENGLISH.add("pole smoker");
                    ENGLISH.add("ponyplay");
                    ENGLISH.add("poof");
                    ENGLISH.add("poon");
                    ENGLISH.add("poontang");
                    ENGLISH.add("punany");
                    ENGLISH.add("poop chute");
                    ENGLISH.add("poopchute");
                    ENGLISH.add("porn");
                    ENGLISH.add("porno");
                    ENGLISH.add("pornography");
                    ENGLISH.add("prince albert piercing");
                    ENGLISH.add("pthc");
                    ENGLISH.add("pubes");
                    ENGLISH.add("pussy");
                    ENGLISH.add("queaf");
                    ENGLISH.add("queef");
                    ENGLISH.add("quim");
                    ENGLISH.add("raghead");
                    ENGLISH.add("raging boner");
                    ENGLISH.add("rape");
                    ENGLISH.add("raping");
                    ENGLISH.add("rapist");
                    ENGLISH.add("rectum");
                    ENGLISH.add("reverse cowgirl");
                    ENGLISH.add("rimjob");
                    ENGLISH.add("rimming");
                    ENGLISH.add("rosy palm");
                    ENGLISH.add("rosy palm and her 5 sisters");
                    ENGLISH.add("rosy palm and her five sisters");
                    ENGLISH.add("rusty trombone");
                    ENGLISH.add("sadism");
                    ENGLISH.add("santorum");
                    ENGLISH.add("scat");
                    ENGLISH.add("schlong");
                    ENGLISH.add("scissoring");
                    ENGLISH.add("semen");
                    ENGLISH.add("sex");
                    ENGLISH.add("sexcam");
                    ENGLISH.add("sexo");
                    ENGLISH.add("sexy");
                    ENGLISH.add("sexual");
                    ENGLISH.add("sexually");
                    ENGLISH.add("shaved beaver");
                    ENGLISH.add("shaved pussy");
                    ENGLISH.add("shemale");
                    ENGLISH.add("shibari");
                    ENGLISH.add("shit");
                    ENGLISH.add("shitblimp");
                    ENGLISH.add("shitty");
                    ENGLISH.add("shota");
                    ENGLISH.add("shrimping");
                    ENGLISH.add("skeet");
                    ENGLISH.add("slanteye");
                    ENGLISH.add("slut");
                    ENGLISH.add("s&m");
                    ENGLISH.add("smut");
                    ENGLISH.add("snatch");
                    ENGLISH.add("snowballing");
                    ENGLISH.add("sodomize");
                    ENGLISH.add("sodomy");
                    ENGLISH.add("spastic");
                    ENGLISH.add("spic");
                    ENGLISH.add("splooge");
                    ENGLISH.add("splooge moose");
                    ENGLISH.add("spooge");
                    ENGLISH.add("spread legs");
                    ENGLISH.add("spunk");
                    ENGLISH.add("strap on");
                    ENGLISH.add("strapon");
                    ENGLISH.add("strappado");
                    ENGLISH.add("strip club");
                    ENGLISH.add("style doggy");
                    ENGLISH.add("suck");
                    ENGLISH.add("sucks");
                    ENGLISH.add("suicide girls");
                    ENGLISH.add("sultry women");
                    ENGLISH.add("swastika");
                    ENGLISH.add("swinger");
                    ENGLISH.add("tainted love");
                    ENGLISH.add("taste my");
                    ENGLISH.add("tea bagging");
                    ENGLISH.add("threesome");
                    ENGLISH.add("throating");
                    ENGLISH.add("thumbzilla");
                    ENGLISH.add("tied up");
                    ENGLISH.add("tight white");
                    ENGLISH.add("tit");
                    ENGLISH.add("tits");
                    ENGLISH.add("titties");
                    ENGLISH.add("titty");
                    ENGLISH.add("tongue in a");
                    ENGLISH.add("topless");
                    ENGLISH.add("tosser");
                    ENGLISH.add("towelhead");
                    ENGLISH.add("tranny");
                    ENGLISH.add("tribadism");
                    ENGLISH.add("tub girl");
                    ENGLISH.add("tubgirl");
                    ENGLISH.add("tushy");
                    ENGLISH.add("twat");
                    ENGLISH.add("twink");
                    ENGLISH.add("twinkie");
                    ENGLISH.add("two girls one cup");
                    ENGLISH.add("undressing");
                    ENGLISH.add("upskirt");
                    ENGLISH.add("urethra play");
                    ENGLISH.add("urophilia");
                    ENGLISH.add("vagina");
                    ENGLISH.add("venus mound");
                    ENGLISH.add("viagra");
                    ENGLISH.add("vibrator");
                    ENGLISH.add("violet wand");
                    ENGLISH.add("vorarephilia");
                    ENGLISH.add("voyeur");
                    ENGLISH.add("voyeurweb");
                    ENGLISH.add("voyuer");
                    ENGLISH.add("vulva");
                    ENGLISH.add("wank");
                    ENGLISH.add("wetback");
                    ENGLISH.add("wet dream");
                    ENGLISH.add("white power");
                    ENGLISH.add("whore");
                    ENGLISH.add("worldsex");
                    ENGLISH.add("wrapping men");
                    ENGLISH.add("wrinkled starfish");
                    ENGLISH.add("xx");
                    ENGLISH.add("xxx");
                    ENGLISH.add("yaoi");
                    ENGLISH.add("yellow showers");
                    ENGLISH.add("yiffy");
                    ENGLISH.add("zoophilia");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Lists loaded");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "checks for Updates");
                    new Updater(this , 0).getVersion(version -> {
                        if (!this.getDescription().getVersion().equals(version)) {
                            Bukkit.getLogger().info(Prefix.getGhostLogger() + "There is not a new Update for the Ghostchat available!");
                        }else {
                            Bukkit.getLogger().warning(Prefix.getGhostLogger() + "There is a new Update for the Ghostchat System available!");
                        }
                    });
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Update checks");
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "GhostChat startet");
        }else {
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "GhostMain plugin is missing!");
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "The plugin will not work without this plugin!");
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "This plugin is therefore deactivated!");
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "Download GhostMain: https://www.spigotmc.org/resources/ghost-main.102115/");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @NotNull
    public static MiniMessage getMiniMessage(){
        return mm;
    }

    @NotNull
    public GhostChatConfig getPrefixOwnerConfiguration(){
        return prefixOwnerConfiguration;
    }

    @NotNull
    public GhostChatConfig getPrefixAdminConfiguration() {
        return prefixAdminConfiguration;
    }

    @NotNull
    public GhostChatConfig getPrefixDeveloperConfiguration() {
        return prefixDeveloperConfiguration;
    }

    @NotNull
    public GhostChatConfig getPrefixModeratorConfiguration() {
        return prefixModeratorConfiguration;
    }

    @NotNull
    public GhostChatConfig getPrefixPlayerConfiguration() {
        return prefixPlayerConfiguration;
    }

    @NotNull
    public static GhostChat getInstance() {
        return instance;
    }
}
