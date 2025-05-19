package src;

// Importar modulos usados
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

// Clase que representa el panel donde se dibuja el árbol
public class PanelArbol extends JPanel {

    // Atributos
    private static final int RADIO = 30;        // Radio de los nodos
    private static final int DIST_Y = 300;      // Distancia vertical entre nodos
    private double scale = 1.0;                 // Atributo para el zoom
    private Nodo raiz;

    // Constructor
    public PanelArbol() {
        setPreferredSize(new Dimension(6000, 4500));    // Tamaño del panel
        setBackground(Color.WHITE);                                  // Color de fondo
    }

    // Metodos

    // Método para dibujar el árbol
    public void dibujarArbol(Nodo raiz) {
        this.raiz = raiz;
        repaint(); // Redibuja el panel
    }

    // Métodos para zoom
    public void acercar() {
        scale *= 1.2;
        repaint();
    }
    public void alejar() {
        scale /= 1.2;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.scale(scale, scale);

        if (raiz != null) {
            int ancho = getPreferredSize().width;       
            dibujarNodo(g2d, raiz, ancho / 2, 30, 1000, 0);
        }
    }

    // Método recursivo para dibujar los nodos
    private void dibujarNodo(Graphics2D g2d, Nodo nodo, int x, int y, int offsetX, int lado) {
        if (nodo == null) return;

        // Dibujar el hijo izquierdo (blancas)
        if (nodo.getIzquierdo() != null) {
            int hijoX = x - offsetX;
            int hijoY = y + DIST_Y;
            g2d.draw(new Line2D.Double(x, y, hijoX, hijoY));
            dibujarNodo(g2d, nodo.getIzquierdo(), hijoX, hijoY, offsetX / 2, 1);
        }
        // Dibuja el hijo derecho (negras)
        if (nodo.getDerecho() != null) {
            int hijoX = x + offsetX;
            int hijoY = y + DIST_Y;
            g2d.draw(new Line2D.Double(x, y, hijoX, hijoY));
            dibujarNodo(g2d, nodo.getDerecho(), hijoX, hijoY, offsetX / 2, 2);
        }

        // Dibujar el nodo
        g2d.setColor(determinarColor(nodo, lado));
        g2d.fillOval(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2);

        // Texto del nodo
        g2d.drawString(nodo.getValor(), x - RADIO / 2, y + 5);
    }

    // Metodo para agregar colores a los nodos
    private Color determinarColor(Nodo nodo, int lado) {
        String mov = nodo.getMovimiento();
        if (mov.contains("#")) return Color.GREEN;                               // verde para jaque mate
        if (mov.contains("+")) return Color.CYAN;                                // cyan para jaque
        if (mov.contains("O-O") || mov.contains("O-O-O")) return Color.MAGENTA;// magenta para el enroque
        if (mov.contains("=")) return Color.DARK_GRAY;                          // gris oscuro para las promociones
        if (mov.contains("x")) return Color.RED;                                 // rojo para las capturas
        if (lado == 0) return Color.BLUE;                                          // azul para la raíz
        if (lado == 1) return Color.YELLOW;                                        // amarillo (izquierdo)
        if (lado == 2) return Color.LIGHT_GRAY;                                     // gris claro (derecho)
        return Color.WHITE;
    }
}