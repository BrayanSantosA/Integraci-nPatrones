package interpreter;

public class ExpresionNumero implements ExpresionTerminal {
    private final double valor;

    public ExpresionNumero(double valor) { this.valor = valor; }

    @Override
    public double interpretar(Contexto contexto) { return valor; }
}
