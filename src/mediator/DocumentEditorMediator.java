package mediator;

import java.util.function.Consumer;

import bridge.Documento;
import bridge.DocumentoContinuo;
import bridge.DocumentoPaginado;
import bridge.HtmlRenderEngine;
import bridge.MarkdownRenderEngine;
import bridge.PdfRenderEngine;
import bridge.RenderizadorEngine;
import builder.DocumentBuilder;
import builder.FacturaSimpleBuilder;
import builder.ReporteEjecutivoBuilder;
import chain.ProcesadorHandler;
import chain.ReporteProceso;
import documento.Contenido;
import interpreter.Contexto;

public class DocumentEditorMediator implements Mediator {
    private final VistaPrevia vista;
    private final ProcesadorHandler cadena;
    private final Contexto contexto;
    private Consumer<DocumentBuilder> director;

    private DocumentBuilder builderActual = new ReporteEjecutivoBuilder();
    private RenderizadorEngine renderizadorActual = new HtmlRenderEngine();

    public DocumentEditorMediator(SelectorDeFormato selector, BarraDeHerramientasBuilder barra,
                                  VistaPrevia vista, BotonExportar boton,
                                  ProcesadorHandler cadena, Contexto contexto,
                                  Consumer<DocumentBuilder> director) {
        this.vista = vista;
        this.cadena = cadena;
        this.contexto = contexto;
        this.director = director;
        selector.setMediator(this);
        barra.setMediator(this);
        vista.setMediator(this);
        boton.setMediator(this);
    }

    public void setDirector(Consumer<DocumentBuilder> director) { this.director = director; }

    @Override
    public void notificar(Componente emisor, String evento, String dato) {
        switch (evento) {
            case "FORMATO_CAMBIADO" -> {
                renderizadorActual = switch (dato) {
                    case "PDF" -> new PdfRenderEngine();
                    case "HTML" -> new HtmlRenderEngine();
                    case "MARKDOWN" -> new MarkdownRenderEngine();
                    default -> throw new IllegalArgumentException("Formato no soportado: " + dato);
                };
                vista.mostrar("[Mediator] Renderizador -> " + renderizadorActual.formato());
            }
            case "BUILDER_CAMBIADO" -> {
                builderActual = switch (dato) {
                    case "REPORTE_EJECUTIVO" -> new ReporteEjecutivoBuilder();
                    case "FACTURA_SIMPLE" -> new FacturaSimpleBuilder();
                    default -> throw new IllegalArgumentException("Tipo no soportado: " + dato);
                };
                vista.mostrar("[Mediator] Builder -> " + builderActual.getClass().getSimpleName());
            }
            case "EXPORTAR" -> exportar();
            default -> { }
        }
    }

    private void exportar() {
        director.accept(builderActual);
        Contenido contenido = builderActual.build();

        ReporteProceso reporte = new ReporteProceso();
        cadena.manejar(contenido, contexto, reporte);
        vista.mostrar("[Mediator] Resultado de la cadena:");
        reporte.getLog().forEach(vista::mostrar);
        if (reporte.isCritico()) {
            vista.mostrar("[Mediator] Exportación cancelada.\n");
            return;
        }

        Documento doc = "PDF".equals(renderizadorActual.formato())
                ? new DocumentoPaginado(contenido, renderizadorActual, 2)
                : new DocumentoContinuo(contenido, renderizadorActual);
        vista.mostrar("[Mediator] " + doc.getClass().getSimpleName() + " + "
                + renderizadorActual.getClass().getSimpleName() + " (" + contenido.totalGlifos() + " glifos)");
        vista.mostrar("----------------------------------------");
        vista.mostrar(doc.exportar());
        vista.mostrar("----------------------------------------\n");
    }
}
