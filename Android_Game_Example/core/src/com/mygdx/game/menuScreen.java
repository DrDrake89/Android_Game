package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.util.Random;

public class menuScreen extends InputAdapter implements Screen {

    // Sprite Sheet
    Texture sheet,sheetBackground,player;
    Stage stage;
    SpriteBatch batch;
    TextureRegion PlayerSelect,playerPosStatic,playerPosL,playerPosD,playerPosR,playerPosU,virus,backGroundImage;
    float speed = 150.0f;
    float playerX = 0;float playerY = 0;
    float previousX;float previousY;
    float randomPosX;float randomPosY;
    int w = 0;int h = 0;
    int tw = 0;int th = 0;
    Rectangle player_rect,virus_rect;
    OrthographicCamera camera = null;
    Circle collisionCircle;

    @Override
    public void show() {
        loadTextures();
        cameraPosition();
        positionVirus();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
    }

    private void positionVirus() {
        //Random position
        Random r = new Random();
        float min = 0;
        float maxY = Gdx.graphics.getHeight()-virus.getRegionHeight();
        float maxX = Gdx.graphics.getWidth()-virus.getRegionWidth();
        randomPosX = min + r.nextFloat() * (maxX - 0);
        randomPosY = min + r.nextFloat() * (maxY - 0);
    }

    private void loadTextures() {
        sheet = new Texture(Gdx.files.internal("Pictures.png"));
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        // Sprites de la nau
        playerPosStatic = new TextureRegion(sheet, 0, 0, 80, 100);
        playerPosStatic.flip(true, true);
        PlayerSelect = playerPosStatic;
        playerPosL = new TextureRegion(sheet, 80, 0, 80, 100);
        playerPosL.flip(false, true);
        playerPosR = new TextureRegion(sheet, 160, 0, 80, 100);
        playerPosR.flip(true, true);
        playerPosD = new TextureRegion(sheet, 310, 0, 80, 100);
        playerPosD.flip(true, true);
        playerPosU = new TextureRegion(sheet, 470, 0, 80, 100);
        playerPosU.flip(true, true);
        virus = new TextureRegion(sheet, 0, 100, 150, 190);
        virus.flip(false, true);
    }

    public void cameraPosition(){
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.position.set(w / 2, h / 2, 0); // Change the height --> h
        camera.update();
        playerX = w-100;
        playerY = h/2;
        sheetBackground = new Texture(Gdx.files.internal("background.png"));
        sheetBackground.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        backGroundImage= new TextureRegion(sheet, 0, 0, 80, 250);
        tw = sheetBackground.getWidth();
        th = sheetBackground.getHeight();
        batch = new SpriteBatch();
        System.out.println(w+" "+h);
        System.out.println("playerX "+playerX+" playerY "+playerY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,0);
        ScreenUtils.clear(1,1,1,0);
        PlayerSelect = playerPosStatic;
        Texture SelPlayer,SelVirus;
        //COLLIDE PROBLEM IT DOESN'T WORK
        /*SelPlayer = new Texture((TextureData) PlayerSelect.getTexture());
        SelVirus = new Texture((TextureData) virus.getTexture());
        virus_rect = new Rectangle(100,100,SelVirus.getWidth(),SelVirus.getHeight());
        player_rect = new Rectangle(100,100,SelPlayer.getWidth(),SelPlayer.getHeight());
         */

        /*if (Intersector.overlaps(SelPlayer,SelVirus)){
            System.out.println("Collides");
        }*/

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
            System.out.println("Right");
            PlayerSelect = playerPosR;
            previousX = playerX;
            playerX-=Gdx.graphics.getDeltaTime()*speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
            System.out.println("Left");
            PlayerSelect = playerPosL;
            previousX = playerX;
            playerX+=Gdx.graphics.getDeltaTime()*speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
            System.out.println("Up");
            PlayerSelect = playerPosU;
            previousY = playerY;
            playerY-=Gdx.graphics.getDeltaTime()*speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
            System.out.println("Down");
            PlayerSelect = playerPosD;
            previousY = playerY;
            playerY+=Gdx.graphics.getDeltaTime()*speed;
        }
        batch.begin();
        batch.draw(sheetBackground, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.draw();
        batch.draw(PlayerSelect,(int)playerX,(int)playerY);
        batch.draw(virus,randomPosX,randomPosY, 120,120);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
