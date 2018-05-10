package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * Created by Slavik on 03.05.2018.
 */

public class Mob {
    private static final int WIDTH=25;
    private static final int HEIGHT=14;

    private int health;
    private int speed;
    private int damage;

    private Texture texture;
    private EnemyType type;
    private Circle bound;
}
