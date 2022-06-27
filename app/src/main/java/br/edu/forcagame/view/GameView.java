package br.edu.forcagame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import br.edu.forcagame.R;

/*
 * A GameView é responsável por coordenar todos os elementos do jogo, e também por atualizar
 * e renderizar todos o objetos na tela
*/

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private final GameLoop gameLoop;
    private final Context context;
    public GameView(Context context) {
        super(context);
        this.context = context;
        //O SurfaceHolder é uma classe que contém callbacks que nos dão métodos para controlar a SurfaceView
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        gameLoop = new GameLoop(this, holder);
    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.amber3);
        paint.setColor(color);
        paint.setTextSize(50F);
        canvas.drawText("UPS: " + averageUPS, 100, 50, paint);
    }
    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.amber3);
        paint.setColor(color);
        paint.setTextSize(50F);
        canvas.drawText("FPS: " + averageFPS, 100, 120, paint);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }

    public void update() {
    }
}