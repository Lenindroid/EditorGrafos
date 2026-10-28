import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Yalu Galicia Hdez.
 *   Estructuras de Datos  
 *   Actualizado: 2026
 * Modelo del grafo
 */

class Grafo {
	private int orden; //orden del grafo
	private ArrayList <Vertice> vertices; 
	//Se usa como representación listas 
        //adyacencia. Ver en clase Vertice
	
	public Grafo(){
		this(10);
	}
	
	public Grafo(int orden){
		this.orden = orden;
		vertices = new ArrayList<> ();
	}

	public void agregarVertice(Point2D p){		
		Vertice v = new Vertice(p);
		vertices.add(v);
                v.setNombre(vertices.indexOf(v)+"");
	}

        public void agregarVertice(Point2D p, String nombre){		
            Vertice v = new Vertice(p);
            vertices.add(v);
             v.setNombre(nombre);
        }

	
	//obtiene el Vertice que contenga el punto p
	public Vertice getVertice(Point2D p)
	{
		for(Vertice v: vertices)
			if(v.getCirculo().contains(p)) return v;
		return null;
	}

        public ArrayList<Vertice> getVertices() {
            return vertices;
        }
        
        
        

	private int getIndex(Point2D p){
                for(int i=0; i< vertices.size(); i++) {
                    if((vertices.get(i)).getCirculo().contains(p))
                            return i;
                }
                return -1;
	}
	
	public void agregarAdyacente(Vertice vo, Vertice vd){
                vo.agregarAdyacente(vd, 1); //peso por default
	}
        
        public void agregarAdyacente(Vertice vo, Vertice vd, int peso){
                vo.agregarAdyacente(vd, peso); //Peso personalizado
	}
	
         //mostrar la lista de vertices
        public String mostrarVertices(){
            String cad = " ";
            for(Vertice v: vertices) 
                cad += v.getNombre()+", ";
            return cad;
        }   
        
        public String mostrarIndiceVertices() {
            String cad = "";
            for (int i = 0; i < vertices.size(); i++) {
                cad += i + " : " + vertices.get(i).getNombre() + "\n";
            }
            return cad;
        }
        
        public String mostrarLista() {
            String cad = "";
            for (Vertice v: vertices) {
                cad += v.getNombre() + ": ";
                for (NodoAdyac w : v.getAdyacentes()) {
                    cad += w.getAdyacente().getNombre() + " (" + w.getPeso() + " de peso) ";
                }
                cad += "\n";
            }            
            return cad;
        }

      

	public void dibujar(Graphics2D g2, JPanel l){
                
       //pintamos los vertices
	 	for(Vertice v: vertices) 
	 		v.dibujar(g2);
	 	
		//TODO: pintar aristas
		//Tener cuidado! solo pintar aristas existentes!
	}
        
        
        public void colorear(Lienzo lienzo) {
            ThreadColores hilo = new ThreadColores(lienzo, this.vertices);
            hilo.start();
        }
        public void BFS(Lienzo lienzo, Vertice inicio) {
            ThreadColores hilo = new ThreadColores(lienzo, this.vertices, "BFS", inicio);
            hilo.start();
        }
        public void DFS(Lienzo lienzo, Vertice inicio) {
            ThreadColores hilo = new ThreadColores(lienzo, this.vertices, "DFS", inicio);
            hilo.start();
        }
      
} //clase