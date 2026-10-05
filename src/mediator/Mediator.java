package mediator;

public interface Mediator {
    void notificar(Componente emisor, String evento, String dato);
}
