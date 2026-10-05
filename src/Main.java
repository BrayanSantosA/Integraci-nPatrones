import java.time.LocalDate;
import java.util.List;

import chain.EvaluadorExpresiones;
import chain.ProcesadorHandler;
import chain.SanitizadorPalabras;
import chain.ValidadorSintaxis;
import flyweight.GlifoFactory;
import interpreter.Contexto;
import mediator.BarraDeHerramientasBuilder;
import mediator.BotonExportar;
import mediator.DocumentEditorMediator;
import mediator.SelectorDeFormato;
import mediator.VistaPrevia;

public class Main {
    public static void main(String[] args) {

        Contexto ctx = new Contexto();
        ctx.setVariable("PRECIO_BASE", 100000);
        ctx.setVariable("DESCUENTO", 5000);
        ctx.setTexto("FECHA_ACTUAL", LocalDate.now().toString());


        ProcesadorHandler cadena = new ValidadorSintaxis();
        cadena.setSiguiente(new SanitizadorPalabras(List.of("password", "confidencial")))
              .setSiguiente(new EvaluadorExpresiones());


        SelectorDeFormato selector = new SelectorDeFormato();
        BarraDeHerramientasBuilder barra = new BarraDeHerramientasBuilder();
        VistaPrevia vista = new VistaPrevia();
        BotonExportar boton = new BotonExportar();

        DocumentEditorMediator mediator = new DocumentEditorMediator(selector, barra, vista, boton, cadena, ctx,
                b -> b.addHeader("Resumen financiero")
                      .addParagraph("Fecha de emisión: #{FECHA_ACTUAL}")
                      .addParagraph("Total con IVA y descuento: #{PRECIO_BASE * 1.19 - DESCUENTO} COP")
                      .addParagraph("Nota: la clave de acceso es Password123 (dato confidencial)")
                      .addTable(new String[][]{
                              {"Concepto", "Valor"},
                              {"Precio base", "#{PRECIO_BASE}"},
                              {"IVA 19%", "#{PRECIO_BASE * 0.19}"},
                              {"Total", "#{(PRECIO_BASE + PRECIO_BASE * 0.19) - DESCUENTO}"}})
                      .addFooter("Generado por el Motor de Documentos Inteligentes"));

        System.out.println("=== 1) Reporte ejecutivo en PDF ===");
        barra.seleccionar("REPORTE_EJECUTIVO");
        selector.elegir("PDF");
        boton.click();

        System.out.println("=== 2) El mismo contenido como factura en HTML ===");
        barra.seleccionar("FACTURA_SIMPLE");
        selector.elegir("HTML");
        boton.click();

        System.out.println("=== 3) Factura en Markdown ===");
        selector.elegir("MARKDOWN");
        boton.click();

        System.out.println("=== 4) Documento corrupto: la cadena se interrumpe ===");
        mediator.setDirector(b -> b.addHeader("Documento roto").addParagraph("Total: #{PRECIO_BASE * "));
        boton.click();

        GlifoFactory f = GlifoFactory.getInstancia();
        System.out.println("=== Estadísticas Flyweight ===");
        System.out.println("Solicitudes de elementos gráficos: " + f.solicitudes());
        System.out.println("Instancias realmente creadas:      " + f.instanciasCreadas());
    }
}
