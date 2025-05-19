package src;

// Importar modulos usados
import java.util.ArrayList;
import java.util.List;

// Clase encargada de la logica del programa
public class ClaseMaestra {

    // Atributos
    private String textoSAN;
    private List<String> turnos;
    private ValidarMovimientos validador;

    // Constructor
    public ClaseMaestra(String textoSAN) {
        this.textoSAN = textoSAN;
        this.turnos = new ArrayList<>();            // Inicializa la lista de turnos
        this.validador = new ValidarMovimientos();  // Inicializa el validador de movimientos
    }

    // Metodos

    public void analizarPartida() throws Error {
        this.turnos.clear();    // Limpia la lista de turnos antes de analizar una nueva partida (o la primera)
        String[] fragmentos = this.textoSAN.trim().split("\\s+");   // Divide el texto en fragmentos separados por espacios
        int turnoEsperado = 1;      // Inicializa el turno esperado en 1 (primer turno)

        //ciclo (for) para recorrer los fragmentos
        for (int i = 0; i < fragmentos.length; ) {
            if (!fragmentos[i].matches("(\\d+)\\.")) {  // Verifica si el fragmento es un numero de turno (ej. "1." "2." "3.")
                throw new Error(fragmentos[i], "Formato inválido: se esperaba un número de turno");
            }

            // Extrae el numero de turno (y lo convierte a int, porq era un String) y lo compara con el esperado
            int turno = Integer.parseInt(fragmentos[i].replaceAll("(\\d+)\\..*", "$1"));
            if (turno != turnoEsperado++) {     // Si el turno no coincide con el esperado, devuelve un error
                throw new Error(fragmentos[i], "Número de turno incorrecto. Esperado: " + (turnoEsperado-1));
            }

            // Extrae las jugadas blancas
            String blancas = null;  // Inicializa la jugada blanca en vacio
            // Si hay un fragmento siguiente, lo asigna a blancas
            if (i + 1 < fragmentos.length) {
                blancas = fragmentos[i + 1];
            }
            // Extrae las jugadas negras
            String negras = null;   // Inicializa la jugada negra en vacio
            // Si hay un fragmento siguiente y no es un numero de turno, lo asigna a negras
            if (i + 2 < fragmentos.length && !fragmentos[i + 2].matches("(\\d+)\\.")) { 
                negras = fragmentos[i + 2];
            }

            // Valida las jugadas blancas y negras
            if (blancas == null) {  // Si no hay jugada blanca, devuelve un error
                throw new Error("Falta jugada de blancas en el turno " + turno);    
            }
            // Si si hay jugada blanca, la valida
            validador.validarMovimiento(blancas);

            // Si hay jugada negra, la valida y añade las dos jugadas a la lista de turnos
            if (negras != null) {
                validador.validarMovimiento(negras);
                this.turnos.add(blancas + " " + negras);    // añade las jugadas separadas por un espacio
                i = i + 3;  // Incrementa i en 3 (1 para el turno y 2 para las jugadas)
            } else {    // Si no hay jugada negra, añade solo la jugada blanca
                this.turnos.add(blancas);
                break;  // Termina el ciclo porque si no hay jugada negra, es porque ya terminó la partida
            }
        }
    }

    // Getters
    public List<String> getTurnos() {
        return turnos;
    }
    public String getTextoSAN() {
        return textoSAN;
    }
    public ValidarMovimientos getValidador() {
        return validador;
    }
}