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

package de.elia.ghostchat.plugin.config;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.File;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Short;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GhostChatConfig {

    public File file;

    private File filepath;

    private String pathName;

    private YamlConfiguration config;

    private boolean useCustomPath = false;

    private final Plugin plugin;

    private final String fileName;

    public GhostChatConfig(Plugin plugin , String name){
        this(plugin , name , plugin.getDataFolder());
    }

    public GhostChatConfig(Plugin plugin , String name , boolean copyDefaults){
        this(plugin , name , plugin.getDataFolder() , copyDefaults);
    }

    public GhostChatConfig(Plugin plugin , String name , boolean copyDefaults , boolean replace){
        this(plugin , name , plugin.getDataFolder() , copyDefaults , replace);
    }

    public GhostChatConfig(Plugin plugin , String name , File parent){
        this.plugin = plugin;
        this.fileName = name;
        filepath = parent;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        save();
    }

    public GhostChatConfig(Plugin plugin , String name , File parent , boolean copyDefaults){
        this(plugin , name , parent , copyDefaults , false);
    }

    public GhostChatConfig(Plugin plugin , String name , File parent , boolean copyDefaults , boolean replace){
        this.plugin = plugin;
        this.fileName = name;
        filepath = parent;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (copyDefaults) {
            if (!file.exists()) {
                this.plugin.saveResource(name , false);
            }else if (replace) {
                this.plugin.saveResource(name , true);
            }else {
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    }catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        save();
    }

    public GhostChatConfig(@NotNull Plugin plugin , String pathName , String name) {
        this.plugin = plugin;
        this.pathName = pathName;
        this.fileName = name;
        filepath = new File(plugin.getDataFolder() , pathName);
        useCustomPath = true;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        save();
    }

    public GhostChatConfig(Plugin plugin , String pathName , String name , boolean copyDefaults){
        this(plugin , pathName , name , copyDefaults , false);
    }

    public GhostChatConfig(@NotNull Plugin plugin , String pathName , String name , boolean copyDefaults , boolean replace){
        this.plugin = plugin;
        this.pathName = pathName;
        this.fileName = name;
        filepath = new File(plugin.getDataFolder() , pathName);
        useCustomPath = true;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (copyDefaults) {
            if (!file.exists()) {
                this.plugin.saveResource(pathName + name , false);
            }else if (replace) {
                this.plugin.saveResource(pathName + name , true);
            }else {
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    }catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        save();
    }

    @NotNull
    public YamlConfiguration reload() {
        if (filepath == null && useCustomPath) {
            filepath = new File(plugin.getDataFolder() , pathName);
        }else if (filepath == null) {
            filepath = new File(plugin.getDataFolder() , plugin.getDataFolder().getName());
        }

        if (file == null) {
            file = new File(filepath , fileName);
            config = YamlConfiguration.loadConfiguration(file);
            save();
        }
        return config;
    }

    public void load(File file){
        try {
            config.load(file);
            save();
        }catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public void load(String file){
        try {
            config.load(file);
            save();
        }catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public void load(Reader reader){
        try {
            config.load(file);
            save();
        }catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
        }
    }

    @NotNull
    public YamlConfiguration loadConfiguration(File file){
        config = YamlConfiguration.loadConfiguration(file);
        save();
        return config;
    }

    @NotNull
    public YamlConfiguration loadConfiguration(Reader reader){
        config = YamlConfiguration.loadConfiguration(reader);
        save();
        return config;
    }

    public void loadFromString(String contents){
        try {
            config.loadFromString(contents);
            save();
        }catch (InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    @NotNull
    public String saveToString(){
        String string = config.saveToString();
        save();
        return string;
    }

    public void save(){
        try {
            config.save(file);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void save(File file){
        try {
            config.save(file);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void save(String file){
        try {
            config.save(file);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setDefaults(){
        setDefaults(false);
    }

    public void setDefaults(boolean replace){
        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (pathName != null) {
            if (!file.exists()) {
                this.plugin.saveResource(pathName + fileName , false);
            }else if (replace) {
                this.plugin.saveResource(pathName + fileName , true);
            }else {
                if (!file.exists()) {
                    this.plugin.saveResource(fileName , false);
                }else if (replace) {
                    this.plugin.saveResource(fileName , true);
                }
            }
        }

        save();
    }

    public void setDefaults(Configuration defaults){
        config.setDefaults(defaults);
        save();
    }

    public void addDefault(String path , Object value){
        config.addDefault(path , value);
        save();
    }

    public void addDefaults(Map<String , Object> defaults){
        config.addDefaults(defaults);
        save();
    }

    public void addDefaults(Configuration defaults){
        config.addDefaults(defaults);
        save();
    }

    @NotNull
    public Configuration getDefaults(){
        return config.getDefaults();
    }

    public void copyDefaults(boolean value){
        YamlConfigurationOptions options = config.options().copyDefaults(value);
        save();
    }

    @NotNull
    public Boolean getCopyDefaults(){
        return copyDefaults();
    }

    @NotNull
    public Boolean copyDefaults(){
        return config.options().copyDefaults();
    }

    @Deprecated
    @NotNull
    public YamlConfigurationOptions setHeader(String header){
        YamlConfigurationOptions options = config.options().header(header);
        save();
        return options;
    }

    @NotNull
    public YamlConfigurationOptions setHeader(List<String> value){
        YamlConfigurationOptions options = config.options().setHeader(value);
        save();
        return options;
    }

    @NotNull
    public YamlConfigurationOptions setHeader(String... values){
        List<String> headerValues = new ArrayList<>();
        Collections.addAll(headerValues , values);
        YamlConfigurationOptions options = config.options().setHeader(headerValues);
        save();
        return options;
    }

    @Deprecated
    @NotNull
    public String getHeader(){
        return config.options().header();
    }

    @Deprecated
    @NotNull
    public String header(){
        return config.options().header();
    }

    @Deprecated
    @NotNull
    public YamlConfigurationOptions header(String value){
        return config.options().header(value);
    }

    @Deprecated
    @NotNull
    YamlConfigurationOptions copyHeader(boolean value){
        YamlConfigurationOptions options = config.options().copyHeader(value);
        save();
        return options;
    }

    @Deprecated
    @NotNull
    public Boolean getCopyHeader(){
        return copyHeader();
    }

    @Deprecated
    @NotNull
    public Boolean copyHeader(){
        return config.options().copyHeader();
    }

    @NotNull
    public YamlConfigurationOptions setFooter(List<String> value){
        YamlConfigurationOptions options = config.options().setFooter(value);
        save();
        return options;
    }

    @NotNull
    public YamlConfigurationOptions setFooter(String... values){
        List<String> footerValues = new ArrayList<>();
        Collections.addAll(footerValues , values);
        YamlConfigurationOptions options = config.options().setFooter(footerValues);
        save();
        return options;
    }

    @NotNull
    public List<String> getFooter(){
        return config.options().getFooter();
    }

    public void setComments(String path , List<String> comments){
        config.setComments(path , comments);
        save();
    }

    public void setComments(String path , String... comments){
        List<String> commentList = new ArrayList<>();
        Collections.addAll(commentList , comments);
        config.setComments(path , commentList);
        save();
    }

    @NotNull
    public List<String> getComments(String path){
        return !contains(path) ? null : config.getComments(path);
    }

    public void setInlineComments(String path , List<String> comments){
        config.setInlineComments(path , comments);
        save();
    }

    public void setInlineComments(String path , String... comments){
        List<String> commentList = new ArrayList<>();
        Collections.addAll(commentList , comments);
        config.setInlineComments(path , commentList);
        save();
    }

    public List<String> getInlineComments(String path){
        return !contains(path) ? null : config.getInlineComments(path);
    }

    @NotNull
    public YamlConfigurationOptions setParseComments(boolean value){
        return parseComments(value);
    }

    @NotNull
    public YamlConfigurationOptions parseComments(boolean value){
        YamlConfigurationOptions options = config.options().parseComments(value);
        save();
        return options;
    }

    @NotNull
    public Boolean getParseComments(){
        return parseComments();
    }

    @NotNull
    public Boolean parseComments(){
        return config.options().parseComments();
    }

    @NotNull
    public YamlConfigurationOptions indent(int indent){
        YamlConfigurationOptions options = config.options().indent(indent);
        save();
        return options;
    }

    public int getIndent(){
        return config.options().indent();
    }

    @NotNull
    public YamlConfigurationOptions setWidth(int width){
        YamlConfigurationOptions options = config.options().width(width);
        save();
        return options;
    }

    public int getWidth(){
        return config.options().width();
    }

    @NotNull
    public YamlConfigurationOptions setPathSeparator(char separator){
        YamlConfigurationOptions options = config.options().pathSeparator(separator);
        save();
        return options;
    }

    public char getPathSeparator(){
        return config.options().pathSeparator();
    }

    public void clear(){
        clear(true);
    }

    public void clear(boolean deep){
        Map<String , Object> configValues = config.getValues(deep);
        for (Map.Entry<String , Object> entry : configValues.entrySet()) {
            config.set(entry.getKey() , null);
        }
        save();
    }

    public void clearPath(String path){
        config.set(path , null);
        save();
    }

    @NotNull
    public String createPath(ConfigurationSection section , String pathName){
        String string = MemorySection.createPath(section , pathName);
        save();
        return string;
    }

    @NotNull
    public String createPath(ConfigurationSection section , String pathName , ConfigurationSection relativeTo){
        String string = MemorySection.createPath(section , pathName , relativeTo);
        save();
        return string;
    }

    @NotNull
    public String getCurrentPath(){
        return config.getCurrentPath();
    }

    @NotNull
    public Boolean isSet(String path){
        return config.isSet(path);
    }

    @NotNull
    public Boolean isString(String path){
        return config.isString(path);
    }

    @NotNull
    public String getPath(String path){
        return getString(path);
    }

    @NotNull
    public String getString(String path){
        return !contains(path) ? null : config.getString(path);
    }

    @NotNull
    public String getString(String path , String string){
        return !contains(path) ? null : config.getString(path , string);
    }

    @NotNull
    public ConfigurationSection createSection(String path){
        ConfigurationSection section = config.createSection(path);
        save();
        return section;
    }

    @NotNull
    public ConfigurationSection createSection(String path , Map<? , ?> values){
        ConfigurationSection section = config.createSection(path , values);
        save();
        return section;
    }

    @NotNull
    public Boolean isConfigurationSection(String path){
        return config.isConfigurationSection(path);
    }

    @NotNull
    public ConfigurationSection getConfigurationSection(String path){
        return !contains(path) ? null : config.getConfigurationSection(path);
    }

    @NotNull
    public ConfigurationSection getDefaultSection(){
        return config.getDefaultSection();
    }

    @NotNull
    public ConfigurationSection getParent(){
        return config.getParent();
    }

    public void set(String path , Object value){
        config.set(path , value);
        save();
    }

    @NotNull
    public Boolean contains(String path){
        return config.contains(path);
    }

    @NotNull
    public Boolean contains(String path , boolean ignoreDefault){
        return config.contains(path , ignoreDefault);
    }

    @NotNull
    public Object get(String path){
        return !contains(path) ? null : config.get(path);
    }

    @NotNull
    public Object get(String path , Object value){
        return !contains(path) ? null : config.get(path , value);
    }

    @NotNull
    public Object getObject(String path , Class<Object> value){
        return !contains(path) ? null : config.getObject(path , value);
    }

    @NotNull
    public <T> T getObject(String path , Class<T> type , T def){
        return !contains(path) ? null : config.getObject(path , type , def);
    }

    @NotNull
    public Set<String> getKeys(){
        return config.getKeys(true);
    }

    @NotNull
    public Set<String> getKeys(boolean deep){
        return config.getKeys(deep);
    }

    @NotNull
    public ConfigurationSerializable getSerializable(String path , Class<ConfigurationSerializable> type){
        return !contains(path) ? null : config.getSerializable(path , type);
    }

    @NotNull
    public <T extends ConfigurationSerializable> T getSerializable(String path , Class<T> type , T def){
        return !contains(path) ? null : config.getSerializable(path , type , def);
    }

    @NotNull
    public Map<String , Object> getValues(){
        return getValues(true);
    }

    @NotNull
    public Map<String , Object> getValues(boolean deep){
        return getValues(deep);
    }

    public boolean isBoolean(String path){
        return !contains(path) ? null : config.isBoolean(path);
    }

    @NotNull
    public Boolean getBoolean(String path , boolean value){
        return !contains(path) ? null : config.getBoolean(path , value);
    }

    @NotNull
    public List<Boolean> getBooleanList(String path) {
        return config.getBooleanList(path);
    }

    @NotNull
    public Boolean isInt(String path) {
        return config.isInt(path);
    }

    @NotNull
    public Integer getInt(String path) {
        return !contains(path) ? null : config.getInt(path);
    }

    @NotNull
    public Integer getInt(String path, int value) {
        return !contains(path) ? null : config.getInt(path, value);
    }

    @NotNull
    public List<Integer> getIntegerList(String path) {
        return !contains(path) ? null : config.getIntegerList(path);
    }

    @NotNull
    public Boolean isLong(String path) {
        return config.isLong(path);
    }

    @NotNull
    public Long getLong(String path) {
        return !contains(path) ? null : config.getLong(path);
    }

    @NotNull
    public Long getLong(String path, long value) {
        return !contains(path) ? null : config.getLong(path, value);
    }

    @NotNull
    public List<Long> getLongList(String path) {
        return !contains(path) ? null : config.getLongList(path);
    }

    @NotNull
    public Boolean isDouble(String path) {
        return config.isDouble(path);
    }

    @NotNull
    public Double getDouble(String path) {
        return !contains(path) ? null : config.getDouble(path);
    }

    @NotNull
    public Double getDouble(String path, double value) {
        return !contains(path) ? null : config.getDouble(path, value);
    }

    @NotNull
    public List<Double> getDoubleList(String path) {
        return !contains(path) ? null : config.getDoubleList(path);
    }

    @NotNull
    public List<Float> getFloatList(String path) {
        return !contains(path) ? null : config.getFloatList(path);
    }

    @NotNull
    public List<Short> getShortList(String path) {
        return !contains(path) ? null : config.getShortList(path);
    }

    @NotNull
    public List<Byte> getByteList(String path) {
        return config.getByteList(path);
    }

    @NotNull
    public List<Character> getCharacterList(String path) {
        return config.getCharacterList(path);
    }

    @NotNull
    public Boolean isList(String path) {
        return config.isList(path);
    }

    @NotNull
    public List<?> getList(String path) {
        return !contains(path) ? null : config.getList(path);
    }

    @NotNull
    public List<?> getList(String path, List<?> value) {
        return !contains(path) ? null : (config.getList(path, value));
    }

    @NotNull
    public List<Map<?, ?>> getMapList(String path) {
        return !contains(path) ? null : config.getMapList(path);
    }

    @NotNull
    public Boolean isOfflinePlayer(String path) {
        return config.isOfflinePlayer(path);
    }

    @NotNull
    public OfflinePlayer getOfflinePlayer(String path) {
        return !contains(path) ? null : config.getOfflinePlayer(path);
    }

    @NotNull
    public OfflinePlayer getOfflinePlayer(String path, OfflinePlayer def) {
        return !contains(path) ? null : config.getOfflinePlayer(path, def);
    }

    @NotNull
    public Boolean isItemStack(String path) {
        return config.isItemStack(path);
    }

    @NotNull
    public ItemStack getItemStack(String path) {
        return !contains(path) ? null : config.getItemStack(path);
    }

    @NotNull
    public ItemStack getItemStack(String path, ItemStack value) {
        return !contains(path) ? null : (config.getItemStack(path, value));
    }

    @NotNull
    public Boolean isLocation(String path) {
        return config.isLocation(path);
    }

    @NotNull
    public Location getLocation(String path) {
        return !contains(path) ? null : config.getLocation(path);
    }

    @NotNull
    public Location getLocation(String path, Location def) {
        return !contains(path) ? null : config.getLocation(path, def);
    }

    @NotNull
    public Boolean isVector(String path) {
        return config.isVector(path);
    }
    @NotNull
    public Vector getVector(String path) {
        return !contains(path) ? null : config.getVector(path);
    }

    @NotNull
    public Vector getVector(String path, Vector value) {
        return !contains(path) ? null : config.getVector(path, value);
    }

    @NotNull
    public Boolean isColor(String path) {
        return config.isColor(path);
    }

    @NotNull
    public Color getColor(String path, Color value) {
        return !contains(path) ? null : (config.getColor(path, value));
    }

    @NotNull
    public String getName() {
        return getConfigName();
    }

    @NotNull
    public String getConfigName() {
        return config.getName();
    }

    @NotNull
    public FileConfigurationOptions options() {
        return getOptions();
    }

    @NotNull
    public YamlConfigurationOptions getOptions() {
        return config.options();
    }

    @NotNull
    public Configuration getRoot() {
        return config.getRoot();
    }

    @NotNull
    public String toString() {
        return config.toString();
    }
}
