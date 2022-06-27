package br.edu.forcagame.view;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{
    private boolean isRunning = false;
    private final SurfaceHolder surfaceHolder;
    private final GameView gameView;

    public GameLoop(GameView view, SurfaceHolder holder) {
        this.surfaceHolder = holder;
        this.gameView = view;
    }

    public double getAverageFPS() {
        return 0;
    }

    public double getAverageUPS() {
        return 0;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        Canvas canvas;
        //GameLoop propriamente dito.
        while(isRunning){
            try {
                canvas = surfaceHolder.lockCanvas();
                gameView.update();
                gameView.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }
}
