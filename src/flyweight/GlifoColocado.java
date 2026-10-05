package flyweight;


public record GlifoColocado(ElementoGrafico elemento, int x, int y, String color, double escala) {
    public String dibujar() {
        return elemento.dibujar(x, y, color, escala);
    }
}
