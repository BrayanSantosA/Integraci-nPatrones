package bridge;

import java.util.stream.Collectors;

import documento.Contenido;

public class DocumentoContinuo extends Documento {
    public DocumentoContinuo(Contenido contenido, RenderizadorEngine renderizador) {
        super(contenido, renderizador);
    }

    @Override
    public String exportar() {
        String cuerpo = contenido.getBloques().stream().map(this::renderizarBloque).collect(Collectors.joining("\n\n"));
        return renderizador.envolver(contenido.getTitulo(), cuerpo);
    }
}
