import javax.swing.*;

/**
 *
 * @author Yalu Galicia Hdez.
 *	Estructuras 2025
 */
public class GrafoTest{

    public static void main(String[] args) {
        Grafo grafo = new Grafo(); //instanciamos el grafo
        GrafoVista frame = new GrafoVista(grafo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
