package bridge;

import documento.Bloque;
import documento.Contenido;

public abstract class Documento {
    protected RenderizadorEngine renderizador;
    protected final Contenido contenido;

    protected Documento(Contenido contenido, RenderizadorEngine renderizador) {
        this.contenido = contenido;
        this.renderizador = renderizador;
    }

    public void setRenderizador(RenderizadorEngine renderizador) {
        this.renderizador = renderizador;
    }

    public abstract String exportar();

    protected String renderizarBloque(Bloque b) {
        return switch (b.getTipo()) {
            case HEADER -> renderizador.encabezado(b);
            case PARRAFO -> renderizador.parrafo(b);
            case TABLA -> renderizador.tabla(b);
            case FOOTER -> renderizador.pie(b);
        };
    }
}
