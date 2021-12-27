package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.MenuElement;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	public Screen menuScreen,mainScreen;

	@Override
	public void create () {
		menuScreen = new menuScreen();
		setScreen(menuScreen);
	}
}
