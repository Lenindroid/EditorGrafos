import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

class Vertice {
        private String nombre;
        private LinkedList <NodoAdyac> adyacentes;
        private boolean visitado;
	private Point2D origen; //punto origen
	private Ellipse2D circulo;  //circulo
	private Color color;
	private final static int diametro = 60;
        
    public Vertice(){
            this(new Point2D.Double(0,0));
    }

    public Vertice(Point2D p){
        this(p, "");
    }

    public Vertice(Point2D p, String nombre){
            double x = p.getX();
            double y = p.getY();
            origen = p;
            color = Color.PINK;
            circulo = new Ellipse2D.Double(x-diametro/2,y-diametro/2,
                diametro,diametro);
            this.nombre = nombre;
            adyacentes = new LinkedList<> ();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
   public void setColor(Color c){
   	 color = c;
   }  

    public Ellipse2D getCirculo() {
        return circulo;
    }

    public void setCirculo(Ellipse2D circulo) {
        this.circulo = circulo;
    }

    public Point2D getOrigen() {
        return origen;
    }

    public void setOrigen(Point2D origen) {
        this.origen = origen;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    

    
    public void dibujar(Graphics2D g2){
   	 	g2.setPaint(color);
   	 	g2.fill(circulo);
   	 	g2.setPaint(Color.BLACK);
                g2.drawString(this.nombre, (float)this.getOrigen().getX(), (float)this.getOrigen().getY());
   }

   public void agregarAdyacente(Vertice vd){
       agregarAdyacente(vd, 1);
   }
   
     public void agregarAdyacente(Vertice vd, int peso){
       NodoAdyac na = new NodoAdyac(this, vd, peso);
       adyacentes.add(na);
   }

    public LinkedList<NodoAdyac> getAdyacentes() {
        return adyacentes;
    }
} //fin clase vertice