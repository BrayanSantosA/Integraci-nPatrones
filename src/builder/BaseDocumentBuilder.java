package motor.builder;

import java.util.ArrayList;
import java.util.List;

import motor.documento.Bloque;
import motor.documento.Contenido;
import motor.documento.TipoBloque;
import motor.flyweight.GlifoFactory;

public abstract class BaseDocumentBuilder implements DocumentBuilder {
    protected String titulo = "Sin título";
    protected String tipografia = "Arial";
    protected String color = "#000000";
    protected double escala = 1.0;
    protected String iconoEncabezado = "documento";
    protected final List<Bloque> bloques = new ArrayList<>();

    @Override
    public DocumentBuilder addHeader(String texto) {
        if (bloques.isEmpty()) titulo = texto;
        bloques.add(new Bloque(TipoBloque.HEADER, texto, null,
                GlifoFactory.getInstancia().getIcono(iconoEncabezado), tipografia, color, escala * 1.5));
        return this;
    }

    @Override
    public DocumentBuilder addParagraph(String texto) {
        bloques.add(new Bloque(TipoBloque.PARRAFO, texto, null, null, tipografia, color, escala));
        return this;
    }

    @Override
    public DocumentBuilder addTable(String[][] filas) {
        bloques.add(new Bloque(TipoBloque.TABLA, null, filas, null, tipografia, color, escala));
        return this;
    }

    @Override
    public DocumentBuilder addFooter(String texto) {
        bloques.add(new Bloque(TipoBloque.FOOTER, texto, null, null, tipografia, color, escala * 0.8));
        return this;
    }

    @Override
    public Contenido build() {
        Contenido c = new Contenido(titulo, new ArrayList<>(bloques));
        bloques.clear();
        titulo = "Sin título";
        return c;
    }
}
