package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.TowerDefence;
import com.mygdx.game.sprites.Tower;
import com.mygdx.game.state.level.FirstLevelState;

import sun.rmi.runtime.Log;


public class MenuState extends State {

    private static final float BTN_PLAY_X = 100f;
    private static final float BTN_PLAY_Y = 100f;

    private Texture background;
    private Texture playBtn;

    private Music music;
    private ImageButton btnPlayMusic;
    private ImageButton btnStartPlay;

    private Texture btnPlayMusicTextureOff;
    private Texture btnPlayMusicTextureOn;

    private TextureRegionDrawable playOnTextureRegionDrawable;
    private TextureRegionDrawable playOffTextureRegionDrawable;

    private Stage stage;

    private boolean isPlayMusic = true;


    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        playBtn = new Texture("Start-icon.png");
        background = new Texture("bg.jpg");

        stage = new Stage(new ScreenViewport());

        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(1f);

        btnPlayMusicTextureOff = new Texture("non_play_music_btn.png");
        btnPlayMusicTextureOn = new Texture("play_music_btn.png");

        playOnTextureRegionDrawable = new TextureRegionDrawable(new TextureRegion(btnPlayMusicTextureOn));
        playOffTextureRegionDrawable = new TextureRegionDrawable(new TextureRegion(btnPlayMusicTextureOff));

        btnPlayMusic = new ImageButton(playOffTextureRegionDrawable);
        btnPlayMusic.setPosition(50, TowerDefence.HEIGHT - 70);
        btnPlayMusic.setSize(BTN_PLAY_X, BTN_PLAY_Y);
        btnPlayMusic.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isPlayMusic) {
                    music.play();
                    btnPlayMusic = new ImageButton(playOnTextureRegionDrawable);
//                    btnPlayMusic.setBackground(playOnTextureRegionDrawable);
                } else {
                    music.stop();
                    btnPlayMusic = new ImageButton(playOffTextureRegionDrawable);
//                    btnPlayMusic.setBackground(playOffTextureRegionDrawable);
                }
                isPlayMusic = !isPlayMusic;
                return true;
            }
        });

        btnStartPlay = new ImageButton(new TextureRegionDrawable(new TextureRegion(playBtn)));
        btnStartPlay.setPosition(TowerDefence.WIDTH / 2, TowerDefence.HEIGHT / 2);
        btnStartPlay.setSize(100, 100);
        btnStartPlay.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                //todo start PlayState
                return true;
            }
        });

        stage.addActor(btnStartPlay);
        stage.addActor(btnPlayMusic);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
       // gameStateManager.set(new FirstLevelState(gameStateManager));
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0 ) ;
        spriteBatch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        btnPlayMusicTextureOff.dispose();
        btnPlayMusicTextureOn.dispose();
        music.dispose();
        playBtn.dispose();
    }
}
