package src;

// Clase que valida los movimientos de la notación SAN
public class ValidarMovimientos {

    // atributos
    private String expRegularPieza;
    private String expRegularPeon;
    private String expRegularEnroque;

    // Constructor / inicializar las expresiones regulares
    public ValidarMovimientos() {
        expRegularPieza = "^[KQRBN][a-h]?[1-8]?x?[a-h][1-8](=[QRBN])?[+#]?$";
        expRegularPeon = "^[a-h]x?[a-h][1-8](=[QRBN])?[+#]?$|^[a-h][1-8](=[QRBN])?[+#]?$";
        expRegularEnroque = "^O-O(-O)?[+#]?$";
    }

    // Metodos

    // Método para validar el movimiento en notación SAN
    public void validarMovimiento(String movimiento) throws Error {
        if (movimiento == null || movimiento.isEmpty()) {       // si no hay movimientos retorna un error
            throw new Error(movimiento, "Movimiento vacío");
        }
        if (movimiento.matches("P.*")) {                 // si el movimiento empieza con P, retorna un error (por para los peones la p se obvia)
            throw new Error(movimiento, "No se usa 'P' para peones en SAN");
        }

        // condicional (if) para validar el movimiento
        if (!validarPieza(movimiento) && !validarPeon(movimiento) && !validarEnroque(movimiento)) {     // si no es un movimiento de pieza, peon o enroque, retorna un error
            // Busca patrones de casilla en el movimiento
            java.util.regex.Matcher comprobador = java.util.regex.Pattern.compile("([a-zA-Z]?)([a-h]?)([1-8|0-9])").matcher(movimiento);  // busca patrones que coincidan con la exp regular
            if (comprobador.find()) {       // si coincide con la exp regular, ejecutar el bloque de código
                String col = comprobador.group(2);
                String fila = comprobador.group(3);
                if (!col.isEmpty() && !"abcdefgh".contains(col)) {      //si la columna es vacio o no es de la a a la h, retorna un error
                    throw new Error(movimiento, "Columna inválida (debe ser a-h)");
                }
                if (!fila.isEmpty() && !"12345678".contains(fila)) {    // si la fila es vacio o no es de la 1 a la 8, retorna un error
                    throw new Error(movimiento, "Fila inválida (debe ser 1-8)");
                }
            }
            throw new Error(movimiento, "Movimiento no válido en SAN");   // si no coincide con la exp regular, retorna un error
        }
    }

    // Métodos para validar los movimientos de las piezas, menos el peon
        public boolean validarPieza(String movimiento) {
        return movimiento.matches(expRegularPieza);
    }

    // Métodos para validar los movimientos del peon
    public boolean validarPeon(String movimiento) {
        return movimiento.matches(expRegularPeon);
    }

    // Método para validar el enroque
    public boolean validarEnroque(String movimiento) {
        if (movimiento.contains("x")) {     // si el movimiento contiene una x (captura), no puede ser un enroque
            return false;
        }
        return movimiento.matches(expRegularEnroque);
    }
}