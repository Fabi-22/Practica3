package src;

// Clase para manejar los errores
public class Error extends Exception {

    // Atributos
    private String movimientoInvalido;
    private String motivo;

    // Constructor solo con mensaje
    public Error(String message) {
        super(message);
    }

    // Constructor con movimiento y motivo
    public Error(String movimiento, String motivo) {
        super("Movimiento inválido: " + movimiento + " - Motivo: " + motivo);
        this.movimientoInvalido = movimiento;
        this.motivo = motivo;
    }

    // Getters
    public String getMovimientoInvalido() {
        return movimientoInvalido;
    }

    public String getMotivo() {
        return motivo;
    }
}