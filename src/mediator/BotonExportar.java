package mediator;

public class BotonExportar extends Componente {
    public void click() {
        System.out.println("[UI] BotonExportar -> click");
        notificar("EXPORTAR", null);
    }
}
