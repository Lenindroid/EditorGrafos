import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * @author  Yalu Galicia Hdez.
 *     Actualizado: 2026
*/

// Lienzo para dibujar el grafo
class Lienzo extends JPanel {
    private Ellipse2D actual;
    private Vertice vo, vd;
    private Grafo grafo;
    private JLabel listaVertices;
    private boolean buscoVd = false;

    // Constructor
    public Lienzo(Grafo g1) {
        grafo = g1; // asignamos la referencia para manejarla
        actual = null;
        vo = vd = null;
        setLayout(null);
        listaVertices = new JLabel("Lista de vertices: ");
        this.add(listaVertices);
        listaVertices.setBounds(50, 360, 300, 200);
        
        // Agregamos código para que el lienzo "oiga" al ratón
        addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent evento) {
                Point2D x = evento.getPoint();
                if (grafo.getVertice(x) == null) { // no existe vértice
                    String nombre = javax.swing.JOptionPane.showInputDialog(Lienzo.this, 
                        "Por favor, ingresa el nombre del vértice a crear:", 
                        "Entrada de datos", 
                        javax.swing.JOptionPane.QUESTION_MESSAGE);
                    if (nombre != null) {
                        if (!nombre.trim().isEmpty()) {
                            grafo.agregarVertice(x, nombre);
                        } else {
                            grafo.agregarVertice(x); 
                        }
                    }    
                }
                listaVertices.setText("Lista: " + grafo.mostrarVertices());
                repaint();
            }

            // Es llamado cuando se presiona el ratón sobre un componente
            // Se utiliza para indicar el inicio de la arista
            public void mousePressed(MouseEvent evento) {
                Point2D x = evento.getPoint();
                vo = grafo.getVertice(x);
            }

            // Es llamado cuando se suelta el ratón sobre un componente
            // Se utiliza para indicar el fin de la arista
            public void mouseReleased(MouseEvent evento) {
                Point2D x = evento.getPoint();
                vd = grafo.getVertice(x);
                if (vo != null && vd != null) {
                    if (vo == vd) grafo.agregarAdyacente(vo, vd);
                    else {
                        JTextField txtPeso = new JTextField("1", 5); // Valor por defecto 1
                        JCheckBox chkBidireccional = new JCheckBox("¿Es bidireccional?", true); // Marcado por defecto

                        JPanel panelArista = new JPanel(new GridLayout(0, 1, 5, 5));
                        panelArista.add(new JLabel("Configurar Arista de " + vo.getNombre() + " a " + vd.getNombre()));
                        panelArista.add(new JLabel("Peso de la arista:"));
                        panelArista.add(txtPeso);
                        panelArista.add(chkBidireccional);

                        int resultado = JOptionPane.showConfirmDialog(
                            Lienzo.this, 
                            panelArista, 
                            "Nueva Arista", 
                            JOptionPane.OK_CANCEL_OPTION, 
                            JOptionPane.QUESTION_MESSAGE
                        );
                        if (resultado == JOptionPane.OK_OPTION) {
                            boolean esBidireccional = chkBidireccional.isSelected();
                            try {
                                int peso = Integer.parseInt(txtPeso.getText());
                                //le doy con el peso
                                grafo.agregarAdyacente(vo, vd, peso);
                                if (esBidireccional) grafo.agregarAdyacente(vd, vo, peso);
                            } catch (NumberFormatException e) {
                                grafo.agregarAdyacente(vo, vd);
                                if (esBidireccional) grafo.agregarAdyacente(vd, vo);
                            }
                        }
                    }
                    System.out.println(grafo.mostrarLista());
                }
                
                vo = null;
                vd = null;
                repaint();
            }  
        }); // fin de la clase interna

        // Ahora "oírá" cuando se mueva el ratón
        addMouseMotionListener(new MouseMotionAdapter() {
            // Va a cambiar de icono del cursor al pasar por los vértices
            public void mouseMoved(MouseEvent evento) {
                if (grafo.getVertice(evento.getPoint()) == null) {
                    setCursor(Cursor.getDefaultCursor());
                    setToolTipText(null);
                } else {
                    JPanel miToolTip = new JPanel() {
                        {
                            ToolTipManager.sharedInstance().registerComponent(this);
                        }
                    };
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    Vertice v = grafo.getVertice(evento.getPoint());
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                    setToolTipText(v.getNombre());
                }
            }
        }); // fin de la clase interna Motion
    } // fin constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; 
        setBackground(Color.white);
        grafo.dibujar(g2, this);
    }        

    public void limpiar(Grafo g1) {
        // cambiamos el grafo
        grafo = g1;    
        repaint();        
    }
} // clase