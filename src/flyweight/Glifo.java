package flyweight;

import java.util.Locale;

public final class Glifo implements ElementoGrafico {
    private final char caracter;
    private final String tipografia;

    Glifo(char caracter, String tipografia) {
        this.caracter = caracter;
        this.tipografia = tipografia;
    }

    @Override
    public String dibujar(int x, int y, String color, double escala) {
        return String.format(Locale.US, "'%c'[%s] en (%d,%d) %s x%.1f", caracter, tipografia, x, y, color, escala);
    }
}
