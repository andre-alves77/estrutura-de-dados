import java.util.Scanner;
import One.One;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu da Atividade I");

        System.out.println("1. Identificador de Repetição");
        System.out.println("2. Identificador de Soma");
        System.out.println("3. Ordenação baseada em k");
        System.out.println("4. Programa dos triângulos");

        System.out.println("Insira o número da opção desejada.");
        int opcao = in.nextInt();

        if (opcao ==1){
            new One().start();
        }
        else if (opcao == 2){
            new Two.Two().start();
        }
        else if (opcao == 3){
            new Three.Three().start();
        }
        else if (opcao == 4){
            new Four.Four().start();
        }
        
        in.close();
    }
}
