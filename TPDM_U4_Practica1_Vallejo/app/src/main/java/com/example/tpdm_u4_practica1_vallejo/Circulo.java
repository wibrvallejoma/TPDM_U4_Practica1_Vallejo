package com.example.tpdm_u4_practica1_vallejo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Circulo {
    int x, y;
    int radio;
    int r, g, b;
    int movimientoX, movimientoY;


    public Circulo(int x, int y, int radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        r = (int) (Math.random() * 255);
        g = (int) (Math.random() * 255);
        b = (int) (Math.random() * 255);

        Random random=new Random();
        movimientoX=(random.nextInt(50)-25);
        movimientoY=(random.nextInt(50)-25);
    }


    public  void desplazamiento(){
        x += movimientoX;
        y += movimientoY;
    }

    public void pintar(Canvas c, Paint p){
        p.setColor(Color.rgb(r,g,b));
        c.drawCircle(x, y, radio, p);
    }

}
