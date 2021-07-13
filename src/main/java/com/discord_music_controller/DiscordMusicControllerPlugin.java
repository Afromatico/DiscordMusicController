package com.discord_music_controller;

import java.awt.image.BufferedImage;
import java.util.UUID;
import javax.inject.Inject;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
//import net.runelite.api.Client;
import net.runelite.client.account.AccountSession;
import net.runelite.client.account.SessionManager;
//import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.ws.WSClient;


@Slf4j
@PluginDescriptor(
	name = "Discord Music Controller"
)
public class DiscordMusicControllerPlugin extends Plugin{

	private static final BufferedImage ICON = ImageUtil.loadImageResource(DiscordMusicControllerPlugin.class, "icon.png");

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private DiscordMusicControllerConfig config;

	@Inject
	private SessionManager sessionManager;

	@Inject
	private WSClient wsClient;

	@Provides
	DiscordMusicControllerConfig provideConfig(ConfigManager configManager){
		return configManager.getConfig(DiscordMusicControllerConfig.class);
	}

	private NavigationButton navButton;
	private boolean addedButton = false;

	@Override
	protected void startUp(){
		DiscordMusicController controllerPanel = new DiscordMusicController(this);
		navButton = NavigationButton.builder()
				.tooltip("Music Controller")
				.icon(ICON)
				.priority(7)
				.panel(controllerPanel)
				.build();

		if (config.alwaysShowIcon()){
			if (!addedButton){
				clientToolbar.addNavigation(navButton);
			}
		}

		if (!wsClient.sessionExists()){
			AccountSession accountSession = sessionManager.getAccountSession();
			// Use the existing account session, if it exists, otherwise generate a new session id
			UUID uuid = accountSession != null ? accountSession.getUuid() : UUID.randomUUID();
			wsClient.changeSession(uuid);
		}
	}

	@Subscribe
	protected void onConfigChanged(final ConfigChanged c){

		if (!c.getGroup().equals("music-controller")){
			return;
		}

		if (config.alwaysShowIcon()){
			if (!addedButton){
				clientToolbar.addNavigation(navButton);
			}
		}
		else{
			if (addedButton){
				clientToolbar.removeNavigation(navButton);
			}
		}

		addedButton = config.alwaysShowIcon();
	}

	@Override
	protected void shutDown(){
		clientToolbar.removeNavigation(navButton);
	}

}
