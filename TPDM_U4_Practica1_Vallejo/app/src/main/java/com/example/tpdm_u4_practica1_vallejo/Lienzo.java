package com.example.tpdm_u4_practica1_vallejo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Lienzo extends View {
    Thread hilo;
    Circulo[] circulos;
    public Lienzo(Context context){
        super(context);
        final int cantidadCirculos = (int) (Math.random() * 5) + 5;
        circulos = new Circulo[cantidadCirculos];
        Random random=new Random();
        for (int i = 0; i < cantidadCirculos; i++) {
            int x,y,rad;
            rad = random.nextInt(300) + 10;
            x = random.nextInt(300) + rad;
            y = random.nextInt(300) + rad;

            circulos[i] = new Circulo(x,y,rad);
        }
        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < cantidadCirculos; i ++) {
                        circulos[i].desplazamiento();
                    }
                    try {
                        sleep(60);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    invalidate();
                }
            }
        });
        hilo.start();
    }
    public void limites(Circulo circle, Canvas c){
        if((circle.x + circle.radio ) >= c.getWidth()){
            circle.movimientoX *= -1;
        }
        if((circle.x  - circle.radio) < 1){
            circle.movimientoX *= -1;
        }
        if((circle.y + circle.radio  )>= c.getHeight()){
            circle.movimientoY *= -1;
        }
        if((circle.y - circle.radio ) < 1){
            circle.movimientoY *= -1;
        }
    }
    protected void onDraw(Canvas c){
        Paint p = new Paint();
       for (Circulo circulo : circulos) {
            limites(circulo, c);
            circulo.pintar(c, p);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        return true;
    }
}
