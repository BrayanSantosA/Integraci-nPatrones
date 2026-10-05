package mediator;

public class SelectorDeFormato extends Componente {
    public void elegir(String formato) {
        System.out.println("[UI] SelectorDeFormato -> " + formato);
        notificar("FORMATO_CAMBIADO", formato);
    }
}
