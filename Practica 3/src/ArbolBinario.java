package src;

//import modulos usados
import java.util.LinkedList;
import java.util.Queue;

// Clase que representa el árbol
public class ArbolBinario {

    // Atributos
    private Nodo raiz;

    // Constructor
    public ArbolBinario() {
        this.raiz = null;
    }

    // setters y getters
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    public Nodo getRaiz() {
        return raiz;
    }

    // Metodos
    public void insertarPorNiveles(String[] jugadas) {      //metodo para insertar las jugadas en el árbol
        if (jugadas.length == 0) return;                    // Si no hay jugadas, no se hace nada
        raiz = new Nodo("Partida");              // Crear la raíz
        Queue<Nodo> cola = new LinkedList<>();              // Crear una cola para recorrer el árbol por niveles
        cola.add(raiz);                                     // que la cola sea la raíz
        int i = 0;                                          // Inicializar una variable i para recorrer las jugadas

        // ciclo (while) para recorrer las jugadas
        while (i < jugadas.length && !cola.isEmpty()) {     // mientras haya jugadas y la cola no esté vacía
            Nodo actual = cola.poll();                      // coger el nodo actual de la cola para trabajar con el

            // if para añadir las jugadas blancas (izquierda)
            if (i < jugadas.length) {
                Nodo izq = new Nodo(jugadas[i++]);
                actual.setIzquierdo(izq);
                cola.add(izq);
            }

            // if para añadir las jugadas negras (derecha)
            if (i < jugadas.length) {
                Nodo der = new Nodo(jugadas[i++]);
                actual.setDerecho(der);
                cola.add(der);
            }
        }
    }

    // Metodo para mostrar el árbol en la consola
    public void mostrarArbol() {
        imprimirRecursivo(raiz, 0); // llamar al metodo imprimirRecursivo para mostrar el árbol desde la raíz (nivel 0)
    }

    // Metodo para imprimir el árbol de forma recursiva
    public void imprimirRecursivo(Nodo nodo, int nivel) {
        // caso base
        if (nodo == null) return;   //si no hay nodos, no se hace nada
        // paso recursivo
        System.out.println("  ".repeat(nivel) + nodo.getMovimiento());  // imprimir el movimiento del nodo con un espacio segun el nivel
        imprimirRecursivo(nodo.getIzquierdo(), nivel + 1);              // imprimir el hijo izquierdo (blancas) y añadir un nivel
        imprimirRecursivo(nodo.getDerecho(), nivel + 1);                // imprimir el hijo derecho (negras) y añadir un nivel
    }
}