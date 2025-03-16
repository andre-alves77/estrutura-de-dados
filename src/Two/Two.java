    package Two;

    import java.util.ArrayList;
    import java.util.Scanner;

    public class Two {
        private Scanner in = new Scanner(System.in);
        
        public void start() {
            System.out.println("Programa Identificador de A[i] = A[j] + A[k]. Sendo j < i e k < i...");
            
            ArrayList<Integer> arrayList = new ArrayList<>();

            System.out.println("Insira uma sequencia de números inteiros separados por espaço. Ex: 10 2 39 10 494");
            String[] input = in.nextLine().split(" ");
            for (String s : input) {
                Integer newValue = Integer.parseInt(s);
                arrayList.add(newValue);
            }
            verificarSoma(arrayList);
        }

        public void verificarSoma(ArrayList<Integer> arrayList) {
            int qtd = arrayList.size();
            for (int i = 2; i < qtd; i++) {
                for (int j = 0; j <= i - 2; j++) {
                    for (int q = j + 1; q < i; q++) {
                        if (arrayList.get(i) == arrayList.get(j) + arrayList.get(q)) {
                            System.out.println("Existe um elemento que é a soma de dois anteriores.");
                            return;
                        }
                    }
                }
            }
            System.out.println("Nenhum elemento é a soma de dois anteriores.");

        }
    }
