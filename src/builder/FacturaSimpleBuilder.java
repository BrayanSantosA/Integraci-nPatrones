package builder;

public class FacturaSimpleBuilder extends BaseDocumentBuilder {
    public FacturaSimpleBuilder() {
        tipografia = "Courier";
        color = "#222222";
        iconoEncabezado = "factura";
    }

    @Override
    public DocumentBuilder addFooter(String texto) {
        return super.addFooter("Gracias por su compra | " + texto);
    }
}
