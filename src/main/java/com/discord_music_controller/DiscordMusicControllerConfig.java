package com.discord_music_controller;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("music-controller")
public interface DiscordMusicControllerConfig extends Config{
	@ConfigItem(
		keyName = "alwaysShowIcon",
			name = "Always show sidebar",
			description = "<html>Controls whether the sidebar icon is always shown</html>"
	)

	default boolean alwaysShowIcon(){
		return false;
	}
}
