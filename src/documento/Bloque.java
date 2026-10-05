package documento;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import flyweight.GlifoColocado;
import flyweight.Icono;
import flyweight.Maquetador;


public class Bloque {
    private final TipoBloque tipo;
    private String texto;
    private final String[][] filas;
    private final Icono icono;
    private final String tipografia;
    private final String color;
    private final double escala;
    private List<GlifoColocado> glifos = List.of();

    public Bloque(TipoBloque tipo, String texto, String[][] filas, Icono icono,
                  String tipografia, String color, double escala) {
        this.tipo = tipo;
        this.texto = texto;
        this.filas = filas;
        this.icono = icono;
        this.tipografia = tipografia;
        this.color = color;
        this.escala = escala;
        maquetar();
    }

    private void maquetar() {
        glifos = texto == null ? List.of() : Maquetador.maquetar(texto, tipografia, color, escala);
    }

    public List<String> textos() {
        List<String> r = new ArrayList<>();
        if (texto != null) r.add(texto);
        if (filas != null) for (String[] f : filas) r.addAll(List.of(f));
        return r;
    }

    /** Aplica una transformación al texto y a las celdas; recalcula los glifos. */
    public void transformar(UnaryOperator<String> f) {
        if (texto != null) {
            texto = f.apply(texto);
            maquetar();
        }
        if (filas != null)
            for (String[] fila : filas)
                for (int j = 0; j < fila.length; j++) fila[j] = f.apply(fila[j]);
    }

    public TipoBloque getTipo() { return tipo; }
    public String getTexto() { return texto; }
    public String[][] getFilas() { return filas; }
    public Icono getIcono() { return icono; }
    public List<GlifoColocado> getGlifos() { return glifos; }
}
