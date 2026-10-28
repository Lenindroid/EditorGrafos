
import java.awt.*;
import java.awt.geom.*;

class NodoAdyac {

    private Vertice adyacente;
    private int peso;
    private Line2D arista;  //linea
    private Color color;

    public NodoAdyac(Vertice Vo, Vertice Vd, int peso) {
        adyacente =  Vd;
        arista = new Line2D.Double(Vo.getOrigen(), Vd.getOrigen());
        this.peso = peso;
        color = Color.BLACK;
    }

    public NodoAdyac(Vertice Vo, Vertice Vd) {
        this(Vo, Vd, 1);  //costo por default
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Color getColor() {
        return color;
    }

    public Vertice getAdyacente() {
        return adyacente;
    }
    
    

    public void setColor(Color color) {
        this.color = color;
    }

    public void dibujar(Graphics2D g2) {
        g2.setPaint(color);
        g2.draw(arista);
        g2.setPaint(Color.BLACK);
    }
}
