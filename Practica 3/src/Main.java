package src;

//importar modulos usados
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Clase principal del programa/interfaz gráfica
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Árbol de Ajedrez");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes
        JTextArea areaTexto = new JTextArea(5, 40);
        JButton botonAnalizar = new JButton("Analizar Partida");
        PanelArbol panelArbol = new PanelArbol();
        JLabel estadoLabel = new JLabel("Introduce una partida en notacion SAN");

        // Botones de zoom
        JButton zoomInBtn = new JButton("+");
        JButton zoomOutBtn = new JButton("-");
        zoomInBtn.addActionListener(e -> panelArbol.acercar());
        zoomOutBtn.addActionListener(e -> panelArbol.alejar());
        JPanel zoomPanel = new JPanel();
        zoomPanel.setLayout(new GridLayout(2, 1, 5, 5));
        zoomPanel.add(zoomInBtn);
        zoomPanel.add(zoomOutBtn);

        // Añandir barras de desplazamiento (por si el arbol es muy grande)
        JScrollPane barra = new JScrollPane(panelArbol,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // boton de analizar partida
        botonAnalizar.addActionListener(e -> {
            try {
                String texto = areaTexto.getText().trim();      // Crear el area de texto para poner la partida
                ClaseMaestra c1 = new ClaseMaestra(texto);      // Instanciar la clase maestra como c1
                c1.analizarPartida();                           // llamar al metodo analizarPartida para empezar

                ArbolBinario arbol = new ArbolBinario();        // Crear el arbol
                List<String> jugadas = new ArrayList<>();       // Crear una lista (jugadas)para guardar las jugadas
                for (String turno : c1.getTurnos()) {           // Este ciclo (for) recorre los turnos y los añade a la lista de jugadas
                    for (String jugada : turno.split(" ")) {    // separar las jugadas cada que encuentre un espacio
                        jugadas.add(jugada);
                    }
                }
                arbol.insertarPorNiveles(jugadas.toArray(new String[0]));       // añadir las jugadas al arbol
                panelArbol.dibujarArbol(arbol.getRaiz());                       // Dibujar el árbol en el panel
                estadoLabel.setText("Partida válida. Turnos: " + c1.getTurnos().size());        //si la partida es válida, muestra el número de turnos

            } catch (Error ex) {                     // Si la partida no es válida, muestra el error
                estadoLabel.setText("Error: " + ex.getMessage());
                panelArbol.dibujarArbol(null);                 // No dibujar el árbol si hay un error
            }
        });

        // Diseño
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        panelSuperior.add(botonAnalizar, BorderLayout.SOUTH);

        frame.setLayout(new BorderLayout());
        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(barra, BorderLayout.CENTER);
        frame.add(zoomPanel, BorderLayout.EAST);
        frame.add(estadoLabel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}