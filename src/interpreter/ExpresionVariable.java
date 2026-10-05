package interpreter;

public class ExpresionVariable implements ExpresionTerminal {
    private final String nombre;

    public ExpresionVariable(String nombre) { this.nombre = nombre; }

    @Override
    public double interpretar(Contexto contexto) { return contexto.getVariable(nombre); }
}
