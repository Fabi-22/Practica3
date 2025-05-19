package src;

// clase que representa cada nodo (cada circulo) del árbol
public class Nodo {

    // Atributos / cada nodo tiene un movimiento, y puede tener un hijo izquierdo (blancas) y un hijo derecho (negras)
    private String movimiento;
    private Nodo izquierdo;
    private Nodo derecho;

    // Constructor
    public Nodo(String movimiento) {
        this.movimiento = movimiento;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Getters y Setters
    public String getMovimiento() {
        return movimiento;
    }
    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public String getValor() {
        return getMovimiento();
    }
}