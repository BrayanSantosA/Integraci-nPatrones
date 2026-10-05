package flyweight;

import java.util.Locale;


public final class Icono implements ElementoGrafico {
    private final String nombre;
    private final String imagen; // simula los bytes de la imagen

    Icono(String nombre) {
        this.nombre = nombre;
        this.imagen = "bitmap:" + nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String dibujar(int x, int y, String color, double escala) {
        return String.format(Locale.US, "icono<%s> en (%d,%d) %s x%.1f", imagen, x, y, color, escala);
    }
}
