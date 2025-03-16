package Four;
import java.util.ArrayList;
public class Triangulo {
    double a, b, c;

    public Triangulo(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double calcularArea() {
        double semi = calcularPerimetro() / 2;
        return Math.sqrt(semi * (semi - a) * (semi - b) * (semi - c));
    }

    public double calcularPerimetro() {
        return a + b + c;
    }

    public ArrayList<Double> calcularCentroide() {
        ArrayList<Double> cent = new ArrayList<>();
        double d = (Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b);

        cent.add((b + a * d) / 3);
        cent.add((a / 3) * Math.sqrt(1 - Math.pow(d, 2)));

        return cent;
    }
}