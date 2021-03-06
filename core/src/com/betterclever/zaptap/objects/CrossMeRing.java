package com.betterclever.zaptap.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.betterclever.zaptap.utility.Constants;

/**
 * Created by betterclever on 22/12/16.
 */

public class CrossMeRing extends Ring {

    ShapeRenderer renderer;
    int arcNum;
    float rot = 0;
    int speed;
    Color white;
    Color red;
    Color ringColor;
    float time;
    ColorAction colorAction;

    public CrossMeRing(ShapeRenderer renderer, int mode){

        this.renderer = renderer;
        radius = 200;
        white = Color.WHITE.cpy();
        setParamsByMode(mode);

        red = new Color(0.7f,0.3f,0.3f,1);

        ringColor = white;
        colorAction = new ColorAction();
        colorAction.setColor(ringColor);
        colorAction.setDuration(2f);
        colorAction.setEndColor(red);

    }

    private void setParamsByMode(int mode) {

        switch (mode){
            case Constants.EASY_MODE:
                speed = 40;
                arcNum = MathUtils.random(2,5);
                break;
            case Constants.MEDIUM_MODE:
                speed = 60;
                arcNum = MathUtils.random(2,6);
                break;
            case Constants.HARD_MODE:
                speed = MathUtils.random(50,80);
                arcNum = MathUtils.random(3,6);
                break;
            case Constants.INSANE_MODE:
                speed = MathUtils.random(40,120);
                arcNum = MathUtils.random(3,6);
                break;
        }

    }

    public void render(float delta){

        if(!stopped) {
            radius -= 60*delta;
            rot += (speed * delta);
            rot %= 360;

            time += delta;
            colorAction.act(delta);

        }

        renderer.begin(ShapeRenderer.ShapeType.Filled);


        renderer.setColor(ringColor);
        for (int i = 0; i < arcNum ; i++) {
            renderer.arc(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,radius, ( (180/arcNum * 2* i) + rot ),180/arcNum,256);
        }

        renderer.setColor(Color.BLACK);
        renderer.circle(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,radius-7.5f,256);

        renderer.end();

    }

    public int getArcNum() {
        return arcNum;
    }

    public float getRot() {
        return rot;
    }
}
