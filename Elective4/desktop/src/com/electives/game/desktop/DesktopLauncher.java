package com.electives.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.electives.game.Elective4;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Electives Project v0.1 placeholder";
		config.resizable = true;
		config.useGL30 = true;
		config.width = Elective4.GAME_WIDTH;
		config.height = Elective4.GAME_HEIGHT;
		config.vSyncEnabled = true;
		config.audioDeviceBufferCount = 4096*10;

		new LwjglApplication(new Elective4(), config);
	}
}
