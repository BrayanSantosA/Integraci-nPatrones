package mediator;

public class BarraDeHerramientasBuilder extends Componente {
    public void seleccionar(String tipoDocumento) {
        System.out.println("[UI] BarraDeHerramientasBuilder -> " + tipoDocumento);
        notificar("BUILDER_CAMBIADO", tipoDocumento);
    }
}
