package motor.builder;

public class ReporteEjecutivoBuilder extends BaseDocumentBuilder {
    public ReporteEjecutivoBuilder() {
        tipografia = "Georgia";
        color = "#1F3A5F";
        iconoEncabezado = "grafico";
    }

    @Override
    public DocumentBuilder addHeader(String texto) {
        return super.addHeader(texto.toUpperCase());
    }

    @Override
    public DocumentBuilder addFooter(String texto) {
        return super.addFooter("Reporte ejecutivo | " + texto);
    }
}
