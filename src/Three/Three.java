// Complexidade O(n)
package Three;

import java.util.ArrayList;
import java.util.Scanner;

public class Three {
    private Scanner in = new Scanner(System.in);
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public void start() {
        System.out.println("Programa Organizador de Array");
        int n, aux, menor, maior;

        System.out.println("Insira um número inteiro (k)");
        n = in.nextInt();
        in.nextLine();

        System.out.println("Insira uma sequencia de números inteiros separados por um espaço. Ex: 10 2 39 10 494");
        String[] input = in.nextLine().split(" ");

        for (String s : input) {
            Integer newValue = Integer.parseInt(s);
            arrayList.add(newValue);
        }

        menor = 0;
        maior = arrayList.size() - 1;

        while (menor <= maior) {
            while (menor <= maior && arrayList.get(menor) <= n) {
                menor++;
            }

            while (menor <= maior && arrayList.get(maior) > n) {
                maior--;
            }

            if (menor < maior) {
                aux = arrayList.get(menor);
                arrayList.set(menor, arrayList.get(maior));
                arrayList.set(maior, aux);
            }
        }

        System.out.println("Array:" + arrayList);
    }

}
