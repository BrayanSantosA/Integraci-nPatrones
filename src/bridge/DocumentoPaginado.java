package bridge;

import java.util.List;

import documento.Bloque;
import documento.Contenido;

public class DocumentoPaginado extends Documento {
    private final int bloquesPorPagina;

    public DocumentoPaginado(Contenido contenido, RenderizadorEngine renderizador, int bloquesPorPagina) {
        super(contenido, renderizador);
        this.bloquesPorPagina = bloquesPorPagina;
    }

    @Override
    public String exportar() {
        List<Bloque> bloques = contenido.getBloques();
        StringBuilder sb = new StringBuilder();
        int pagina = 1;
        for (int i = 0; i < bloques.size(); i++) {
            sb.append(renderizarBloque(bloques.get(i))).append("\n");
            if ((i + 1) % bloquesPorPagina == 0 || i == bloques.size() - 1) {
                sb.append(renderizador.separadorPagina(pagina++)).append("\n");
            }
        }
        return renderizador.envolver(contenido.getTitulo(), sb.toString().stripTrailing());
    }
}
