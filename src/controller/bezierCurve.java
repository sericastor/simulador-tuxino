/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author martin
 */
import java.util.Vector;
import model.Move;
import model.Punto;
import processing.core.PApplet;

/**
 *
 * @author Martin Kanayet
 */
public class bezierCurve extends PApplet {

    float x0 = 0;
    float x1 = 0;
    float y0 = 0;
    float y1 = 0;
    Vector puntos = new Vector();
    Vector curva = new Vector();
    Punto act = new Punto(400, 300);

    @Override
    public void setup() {
        size(800, 600);
        background(0);
        stroke(255);
        setPoints();
    }

    @Override
    public void draw() {
        //background(0);
        if (x0 != 0 && y0 != 0) {
        }

    }
    
    /*@Override
    public void mousePressed() {
            if (mouseButton == RIGHT) {
            puntos.addElement(new Punto(mouseX, mouseY));
            point(mouseX, mouseY);
            bezier();
        }
        if (mouseButton == LEFT) {
            
            puntos.addElement(new Punto(mouseX, mouseY));
            point(mouseX, mouseY);
        }
    }*/

    public void setPoints(){
        //Punto central en la pantalla (Inicial)
        puntos.addElement(new Punto(400,300));
        //Calcular otros puntos
        for (Move move : SimulatorController.eventos) {
            puntos.addElement(calcularSgtePunto(act, move));
        }
        bezier();
    }
    
    public Punto calcularSgtePunto(Punto anterior, Move move){
        Punto puntoSiguiente = new Punto(anterior.getX()+ (int) (Float.parseFloat(String.valueOf(Integer.parseInt(move.getVelocity())*Integer.parseInt(move.getSeconds())))*cos(Float.parseFloat(move.getAngle()))), anterior.getY()+ (int) (Float.parseFloat(String.valueOf(Integer.parseInt(move.getVelocity())*Integer.parseInt(move.getSeconds())))*sin(Float.parseFloat(move.getAngle()))));
        act = puntoSiguiente;
        return puntoSiguiente;
    }
    public void bezier() {
        float p = 0.0f, t = 0.0f, x = 0.0f, y = 0.0f, z = 0.0f;
        int i = 0, j = 0, n = puntos.size();

        if (n <= 1) {
            return;
        }
        //Cuanto más grande este bucle mayor número de puntos
        //se calculan, yo utilizo una tasa de incremento de 0.001.
        //para cada iteración de este bucle se calcula un punto a partir
        //del peso de todos los puntos de control.
        for (; p <= 1; p += 0.0001f) {
            x = 0.0f;
            y = 0.0f;
            
            for (i = 0; i < n; i++) {
                //pillo el punto de control actual
                Punto pto = (Punto) puntos.elementAt(i);
                //aplico la binomial!!! con parámetros 
                //(número de puntos menos uno, posición punto actual, valor del paso p)
                t = binomial(n - 1, i, p);
                x += pto.getX() * t;
                y += pto.getY() * t;
                
            }
            //añado el punto calculado al vector de puntos de la curva				
            curva.addElement(new Punto((int) x, (int) y));
            point((int) x, (int) y);
        }
        //dibujo la pantalla con la curva recién calculada
        //repaint();
    }

    /*Función que calcula la binomial par lo cual se utiliza la pirámide
    de taylor*/
    private float binomial(int n, int i, float p) {
        float v = taylor(n, i) * exp(p, i) * exp(1 - p, n - i);
        return (v);
    }
    /*Función que nos da el valor del triangulo de taylor
    correspondientes a la posición de profundiad n, y posición i-ésima
    1
    1 1
    1 2 1 => profundidad = n; elemento de posición 1 = 2*/

    private int taylor(int n, int i) {
        int k = 0, j = 0;
        int t[][] = new int[n + 1][];

        for (; k <= n; k++) {
            t[k] = new int[k + 1];
        }
        /*crear las filas del 0 y el 1 del triangulo de Pascal (valores iniciales)*/
        t[0][0] = 1;
        t[1][0] = 1;
        t[1][1] = 1;

        if (n < 2) {
            return (t[n][i]);
        }
        for (k = 2; k <= n; k++) {/*
            formar triángulo de Pascal
             */
            t[k][0] = 1;
            for (j = 1; j < k; j++) {
                t[k][j] = t[k - 1][j - 1] + t[k - 1][j];
            }
            t[k][j] = 1;
        }
        return (t[n][i]);
    }

    private float exp(float base, int exp) {
        float r = 1.0f;
        int i = 0;

        for (; i < exp; i++) {
            r *= base;
        }
        return r;
    }
}
