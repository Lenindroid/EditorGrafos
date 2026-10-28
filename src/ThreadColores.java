import java.awt.*;
import java.util.*;

/*
 *Esta clase es la que va a permitir colorear los vertices.
 *Recibe como parametro la lista de vertices y el panel(lienzo)
 *donde se dibujan
 */
public class ThreadColores extends Thread {

	private Lienzo lienzo;
	private ArrayList <Vertice> lista;
        private String tipo;
	private Color[] colores = { Color.RED, Color.red,
	 Color.cyan, Color.yellow, Color.orange,
	 Color.green,Color.LIGHT_GRAY, Color.GRAY,
	 Color.pink, Color.BLACK, Color.DARK_GRAY
	};
	private Color color; //color actual
        private Vertice verticeInicial;

	public ThreadColores(Lienzo lienzo, ArrayList <Vertice> lista)
	{
		this.lienzo = lienzo;
		this.lista = lista;
		color = Color.yellow; //por default;
                this.tipo = "NORMAL";
	}
        
        public ThreadColores(Lienzo lienzo, ArrayList <Vertice> lista, String tipo, Vertice inicial)
	{
		this.lienzo = lienzo;
		this.lista = lista;
		color = Color.yellow; //por default;
                this.tipo = tipo;
                this.verticeInicial = inicial;
	}

	public void run(){
		//obtiene numero aleatorio de 0 a 10
		// y lo relaciona con colores
		color = colores[(int) (Math.random()*10)];
		//para cada vertice, cambia su color y lo dibuja
                switch (tipo) {
                    case "NORMAL":
                        for( Vertice v: lista)
                        {
                                v.setColor(color); //establece nuevo color
                                try {
                                /* Repinta los vertices del lienzo
                                 * uno a uno cambiando el color
                                  * a intervalos de 1 segundos. El metodo
                                  * repaint() llama automaticamente
                                  * al m�todo paintComponent del lienzo
                                  * el cual est� sobreescrito
                                 */
                                lienzo.repaint();
                                sleep(1000);
                                }catch (InterruptedException ex){}
                        }
                        break;
                     case "BFS":
                        runBFS(verticeInicial);
                        break;
                    case "DFS":
                        runDFS(verticeInicial);
                        break;
                        }
		
	}
        
        
        public void runBFS(Vertice verticeInicial) {
            ArrayList<Vertice> recorrido = new ArrayList<>();
            Queue<Vertice> cola = new LinkedList<>();

            cola.offer(verticeInicial);
            verticeInicial.setVisitado(true);

            while (!cola.isEmpty()) {
                Vertice verticeActual = cola.poll();
                recorrido.add(verticeActual);

                verticeActual.setColor(color);
                lienzo.repaint();

                try {
                    sleep(1000); 
                } catch (InterruptedException ex) {
                    break;
                }

                for (NodoAdyac na : verticeActual.getAdyacentes()) {
                    Vertice verticeAdyacente = na.getAdyacente();

                    if (!verticeAdyacente.isVisitado()) {
                        cola.offer(verticeAdyacente);
                        verticeAdyacente.setVisitado(true);
                    }
                }
            }
        }
        
        public void runDFS(Vertice verticeInicial) {
            verticeInicial.setVisitado(true);

            verticeInicial.setColor(color);
            lienzo.repaint();

            try {
                sleep(1000); 
            } catch (InterruptedException ex) {
                return;
            }
            for (NodoAdyac na : verticeInicial.getAdyacentes()) {
                Vertice verticeAdyacente = na.getAdyacente();

                if (!verticeAdyacente.isVisitado()) {
                    runDFS(verticeAdyacente); 
                }
            }
        }
}