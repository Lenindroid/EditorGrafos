import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

/**
 *
 * @author Yalu Galicia Hdez.
 *	Estructuras de Datos
 *      Actualizado: 2025
 */
public class GrafoVista extends JFrame {

	private JPanel pInformacion, pLetrero, pIzquierdo;
 	private Lienzo lienzo;
	private JButton btLimpiar, btInfo, btMostrarLista, btColorear, btDFS, btBFS;
        private TextField txtInfo;
	private JLabel lbEtiqueta;
	private Grafo grafo; //referencia al grafo

      //constructor
    public GrafoVista(Grafo grafo) {

        super("Editor de GRAFOS ");
        setLocation(100, 100);
        setLayout(new BorderLayout(2,2));

        this.grafo = grafo;
        lienzo = new Lienzo(this.grafo); 
        lienzo.setPreferredSize(new Dimension(600, 500));
	initComponentes();
        pack();
   }


    private void initComponentes(){
            
	    pInformacion = new JPanel();
            pIzquierdo = new JPanel();
	    pLetrero = new JPanel();
            lbEtiqueta = new JLabel ("Agrega vertices dando clic en cualquier punto para");
            btLimpiar = new JButton("Limpiar");
            btMostrarLista = new JButton("Mostrar lista de adyacencia");
            btColorear = new JButton("Colorear");
            btDFS = new JButton("DFS");
            btBFS = new JButton("BFS");
            btLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    grafo = new Grafo();  //Ojo destruye grafo anterior
                    lienzo.limpiar(grafo);
                    txtInfo.setText(" ");
            }});
            
            btMostrarLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(
                        null,
                        grafo.mostrarLista(),
                        "Lista de adyacencia",
                        JOptionPane.INFORMATION_MESSAGE
                    );
            }});
            
            btBFS.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String respuesta = JOptionPane.showInputDialog(
                        GrafoVista.this, 
                        "Selecciona el número (índice) del vértice inicial:\n" + grafo.mostrarIndiceVertices(), 
                        "Vértice Inicial - BFS", 
                        JOptionPane.QUESTION_MESSAGE
                    );

                    if (respuesta != null && !respuesta.trim().isEmpty()) {
                        try {
                            int i = Integer.parseInt(respuesta.trim());

                            if (i >= 0 && i < grafo.getVertices().size()) {
                                Vertice verticeInicio = grafo.getVertices().get(i);
                                grafo.BFS(lienzo, verticeInicio);
                            } else {
                                JOptionPane.showMessageDialog(
                                    GrafoVista.this, 
                                    "¡Ese índice no existe!", 
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(
                                GrafoVista.this, 
                                "Por favor ingresa un número válido.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            });
            btDFS.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String respuesta = JOptionPane.showInputDialog(
                        GrafoVista.this, 
                        "Selecciona el número (índice) del vértice inicial:\n" + grafo.mostrarIndiceVertices(), 
                        "Vértice Inicial - BFS", 
                        JOptionPane.QUESTION_MESSAGE
                    );

                    if (respuesta != null && !respuesta.trim().isEmpty()) {
                        try {
                            int i = Integer.parseInt(respuesta.trim());

                            if (i >= 0 && i < grafo.getVertices().size()) {
                                Vertice verticeInicio = grafo.getVertices().get(i);
                                grafo.DFS(lienzo, verticeInicio);
                            } else {
                                JOptionPane.showMessageDialog(
                                    GrafoVista.this, 
                                    "¡Ese índice no existe!", 
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(
                                GrafoVista.this, 
                                "Por favor ingresa un número válido.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            });

            btInfo = new JButton("Lista vertices");
            btInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtInfo.setText(grafo.mostrarVertices());
            }});
            btColorear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grafo.colorear(lienzo);
            }});
            txtInfo = new TextField("", 20);
            pIzquierdo.setLayout(new BoxLayout(pIzquierdo, BoxLayout.Y_AXIS));
            pInformacion.add(btLimpiar);
            pInformacion.add(btInfo);
            pInformacion.add(txtInfo);
            pLetrero.add(lbEtiqueta);
            pIzquierdo.add(btMostrarLista);
            pIzquierdo.add(btColorear);
            pIzquierdo.add(btBFS);
            pIzquierdo.add(btDFS);
            add(pLetrero, BorderLayout.NORTH); 
            add(lienzo,BorderLayout.CENTER );
            add(pInformacion, BorderLayout.SOUTH);
            add(pIzquierdo, BorderLayout.WEST);
    }    
   
}
