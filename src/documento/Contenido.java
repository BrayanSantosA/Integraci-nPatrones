package documento;

import java.util.List;

/** Producto del Builder: estructura jerárquica del documento. */
public class Contenido {
    private final String titulo;
    private final List<Bloque> bloques;

    public Contenido(String titulo, List<Bloque> bloques) {
        this.titulo = titulo;
        this.bloques = bloques;
    }

    public String getTitulo() { return titulo; }
    public List<Bloque> getBloques() { return bloques; }

    public int totalGlifos() {
        return bloques.stream().mapToInt(b -> b.getGlifos().size()).sum();
    }
}
