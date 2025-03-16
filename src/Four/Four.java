package Four;

import java.util.ArrayList;
import java.util.Scanner;

public class Four {

        private Scanner in = new Scanner(System.in);
        
        public void start() {
            System.out.println("Programa de Triângulos");
            
            ArrayList<Triangulo> arrayList = new ArrayList<>();

            System.out.println("Insira o número de triangulos que serão inseridos");
            int n = in.nextInt();

            System.out.println("Insira o tamanho dos lados do triangulo");
            for(int i = 1; i<=n; i++){

                System.out.println("Triângulo " + i);

                System.out.print("Lado A: ");
                double a = Double.parseDouble(in.next());
                System.out.print("Lado B: ");
                double b =Double.parseDouble(in.next());
                System.out.print("Lado C: ");
                double c =Double.parseDouble(in.next());

                if (a + b > c && a + c > b && b + c > a) {
                    arrayList.add(new Triangulo(a, b, c));
                } else {
                    System.out.println("Valores inválidos");
                    return;
                }
                arrayList.add(new Triangulo(a,b,c));
            }


            for(int i = 1; i<=n; i++){
                Triangulo tri = arrayList.get(i-1);
                System.out.println("Triangulo "+i);
                System.out.println("S: "+ tri.calcularArea());
                System.out.println("P: "+ tri.calcularPerimetro());
                System.out.println("Centroide: "+ tri.calcularCentroide());
            }
            
    }

}
