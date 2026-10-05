package flyweight;

import java.util.ArrayList;
import java.util.List;


public final class Maquetador {
    private static final int COLUMNAS = 80;

    private Maquetador() {
    }

    public static List<GlifoColocado> maquetar(String texto, String tipografia, String color, double escala) {
        List<GlifoColocado> resultado = new ArrayList<>();
        int ancho = (int) (8 * escala);
        int alto = (int) (14 * escala);
        int col = 0, fila = 0;
        for (char c : texto.toCharArray()) {
            if (col == COLUMNAS) {
                col = 0;
                fila++;
            }
            resultado.add(new GlifoColocado(
                    GlifoFactory.getInstancia().getGlifo(c, tipografia), col * ancho, fila * alto, color, escala));
            col++;
        }
        return resultado;
    }
}
