import javax.swing.*;
import java.util.*;

public class Navegador {
    private final Stack<Pagina> historico = new Stack<>();

    public void irPara(Pagina pagina) {
        if (pagina != null && pagina.getAcao() != null) {
            historico.push(pagina);
            executarEAvoltar(pagina.getAcao());
        } else {
            JOptionPane.showMessageDialog(null, "Página inválida ou sem ação.");
        }
    }

    // se caso não quiser voltar automaticamente
    public void irPara(Pagina pagina, Boolean manter) {
        if(manter){
            if (pagina != null && pagina.getAcao() != null) {
                historico.push(pagina);
                executarEAvoltar(pagina.getAcao());
            } else {
                JOptionPane.showMessageDialog(null, "Página inválida ou sem ação.");
            }
        }else{
            irPara(pagina);
        }
    }

    public void voltar() {
        if (historico.size() > 1) {
            historico.pop();
            Pagina anterior = historico.peek();
            executarEAvoltar(anterior.getAcao());
        } else {
            encerrarPrograma();
        }
    }

    public void mostrarMenu(String titulo, String mensagem, ListaDupla<Pagina> opcoes) {
        int tamanho = opcoes.getTamanho();
        String[] titulos = new String[tamanho];

        No<Pagina> atual = opcoes.getInicio();
        for (int i = 0; i < tamanho; i++) {
            titulos[i] = atual.getDado().getTitulo();
            atual = atual.getProximo();
        }

        int escolha = JOptionPane.showOptionDialog(
                null,
                mensagem,
                titulo,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                titulos,
                titulos[0]
        );

        if (escolha >= 0 && escolha < tamanho) {
            atual = opcoes.getInicio();
            for (int i = 0; i < escolha; i++) {
                atual = atual.getProximo();
            }

            Pagina selecionada = atual.getDado();
            if (selecionada == Pagina.SAIR) {
                voltar();
            } else {
                irPara(selecionada);
            }
        }
    }

// alternativa a sobrecarga de metodo
    public <T> T pedirInput(String titulo, String mensagem, Class<T> tipo) {
    String input = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);

    if (input == null) {
        voltar();
        return null;
    }

    if (input.trim().isEmpty()) {
        mostrarMensagem(titulo, "Erro: Preencha o campo");
        return pedirInput(titulo, mensagem, tipo);
    }

    try {
        if (tipo == Integer.class) return (T) Integer.valueOf(input);
        if (tipo == Double.class) return (T) Double.valueOf(input);
        if (tipo == String.class) return (T) input;
        if (tipo == Boolean.class) {
            input = input.trim().toLowerCase();
            if (input.equals("sim") || input.equals("s") || input.equals("1")) return (T) Boolean.TRUE;
            if (input.equals("nao") || input.equals("n") || input.equals("0") || input.equals("não")) return (T) Boolean.FALSE;
            mostrarMensagem(titulo, "Digite 'sim' ou 'não'");
            return pedirInput(titulo, mensagem, tipo);
        }
    } catch (Exception e) {
        mostrarMensagem(titulo, "Erro: Valor inesperado. Tente novamente.");
        return pedirInput(titulo, mensagem, tipo);
    }

    mostrarMensagem("Erro: " + tipo, "Tipo não suportado.");
    return null;
}

    public void mostrarMensagem(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void executarEAvoltar(Runnable acao) {
        try {
            acao.run();
        } catch (Exception e) {
            mostrarMensagem("Erro", e.getMessage());
        } finally {
            voltar();
        }
    }

    private  <T> T preencherComInputs() {
        //metodo que preenche que cria o formulario automaticamente a partir da classe
        return null;
    }

    private void encerrarPrograma() {
        mostrarMensagem("Acabouuuu", "Tchau selmini");
        System.exit(0);
    }
}