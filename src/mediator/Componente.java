package mediator;

public abstract class Componente {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) { this.mediator = mediator; }

    protected void notificar(String evento, String dato) {
        if (mediator != null) mediator.notificar(this, evento, dato);
    }
}
