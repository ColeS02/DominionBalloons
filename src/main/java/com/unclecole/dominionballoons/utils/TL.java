package com.unclecole.dominionballoons.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public enum TL {
	NO_PERMISSION("messages.no-permission", "&cYou don't have the permission to do that."),
	INVALID_ARGUMENTS("messages.invalid-arguments", "&cInvalid Arguments: <command>"),
	GAVE_SWITCHERBALL("messages.gave-switcherball", "&fYou've given %player% %amount% switcher balls!"),
	RECEIVED_SWITCHERBALL("messages.recieved-switcherball", "&fYou received %amount% switcher balls!"),
	SWITCHERBALL_COOLDOWN("messages.switcherball-cooldown", "&fCurrently on cooldown! %time%"),
	GAVE_SWITCHERSTICK("messages.gave-switcherstick", "&fYou've given %player% %amount% switcher sticks!"),
	RECEIVED_SWITCHERSTICK("messages.recieved-switcherstick", "&fYou received %amount% switcher sticks!"),
	SWITCHERSTICK_COOLDOWN("messages.switcherstick-cooldown", "&fCurrently on cooldown! %time%"),
	SWAPPED_PLACES("messages.swapped-places", "&fYou have swapped places"),
	GAVE_GRAPPLER("messages.gave-switcherstick", "&fYou've given %player% %amount% grappler!"),
	RECEIVED_GRAPPLER("messages.recieved-switcherstick", "&fYou received %amount% grappler!"),
	GRAPPLE_COOLDOWN("messages.grapple-cooldown", "&fCurrently on cooldown...");
	private final String path;

	private String def;
	private static ConfigFile config;

	TL(String path, String start) {
		this.path = path;
		this.def = start;
	}

	public String getDefault() {
		return this.def;
	}

	public String getPath() {
		return this.path;
	}

	public void setDefault(String message) {
		this.def = message;
	}

	public void send(CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			sender.sendMessage(PlaceholderAPI.setPlaceholders(player, C.color(getDefault())));
		} else {
			sender.sendMessage(C.strip(getDefault()));
		}
	}

	public static void loadMessages(ConfigFile configFile) {
		config = configFile;
		FileConfiguration data = configFile.getConfig();
		for (TL message : values()) {
			if (!data.contains(message.getPath())) {
				data.set(message.getPath(), message.getDefault());
			}
		}
		configFile.save();
	}


	public void send(CommandSender sender, PlaceHolder... placeHolders) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			sender.sendMessage(PlaceholderAPI.setPlaceholders(player, C.color(getDefault(), placeHolders)));
		} else {
			sender.sendMessage(C.strip(getDefault(), placeHolders));
		}
	}

	public static void message(CommandSender sender, String message) {
		sender.sendMessage(C.color(message));
	}

	public static void message(CommandSender sender, String message, PlaceHolder... placeHolders) {
		sender.sendMessage(C.color(message, placeHolders));
	}

	public static void message(CommandSender sender, List<String> message) {
		message.forEach(m -> sender.sendMessage(C.color(m)));
	}

	public static void message(CommandSender sender, List<String> message, PlaceHolder... placeHolders) {
		message.forEach(m -> sender.sendMessage(C.color(m, placeHolders)));
	}

	public static void log(Level lvl, String message) {
		Bukkit.getLogger().log(lvl, message);
	}
}
