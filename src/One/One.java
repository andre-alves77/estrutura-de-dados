package One;
import java.util.ArrayList;
import java.util.Scanner;

public class One {
    private Scanner in = new Scanner(System.in);
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public One(){}


    public void start() {
        int n;
        System.out.println("Identificador de Repetições");

        System.out.println("Insira um número inteiro n maior que 1");
        n = in.nextInt();
        in.nextLine();

        if (n < 2) {
            System.out.println("O valor de n deve ser maior ou igual a 2.");
            return;
        }

        System.out.println("Insira "+ n +" números inteiros no intervalo de 1 a " + (n - 1) + " separados por um espaço.");
        String[] input = in.nextLine().split(" ");

        if (input.length != n) {
            System.out.println("Você deve inserir exatamente " + n + " números.");
            return;
        }

        for (String s : input) {
            Integer newValue = Integer.parseInt(s);
            if (newValue < 1 || newValue >= n) {
                System.out.println("O número " + newValue + " está fora do intervalo permitido (1 a " + (n - 1) + ").");
                return;
            }
            arrayList.add(newValue);
        }

        Integer repetido = verificarRedundancia();

        if (repetido != null) {
            System.out.println("Número repetido = " + repetido);
        } else {
            System.out.println("Nenhum número repetido encontrado.");
        }
    }

    public Integer verificarRedundancia() {
        
        for (int i = 0; i<arrayList.size(); i++){
            for (int j = i+1; j<arrayList.size(); j++){
                if(arrayList.get(j).equals(arrayList.get(i))){
                    return arrayList.get(j);
                }
            }
        }

        return null; 
    }


}
