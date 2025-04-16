# Mini Framework de Navegação com Telas em Java

## Visão Geral

Esse mini framework permite criar menus e navegação entre páginas usando `JOptionPane`

Cada **Página** contém um título e uma ação (`Runnable`). A navegação é controlada pela classe `Tela`, que gerencia histórico e execução automática com retorno.

---

## Estrutura

- **Pagina**: representa uma "tela"/opção do menu com um título e ação associada.
- **Tela**: gerencia o histórico de páginas, mostra menus e executa ações com `voltar()` automático.
- **ListaDupla**: lista encadeada para opções do menu (pode ser substituída por `List<Pagina>` se preferir).

---

## Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        Tela tela = new Tela();

        Pagina pagina1 = new Pagina("Dizer Olá", () -> tela.mostrarMensagem("Olá", "Seja bem-vindo!"));
        Pagina pagina2 = new Pagina("Perguntar Nome", () -> {
            String nome = tela.pedirInput("Nome", "Qual seu nome?");
            tela.mostrarMensagem("Nome Informado", "Olá, " + nome + "!");
        });

        ListaDupla<Pagina> opcoes = new ListaDupla<>();
        opcoes.adicionar(pagina1);
        opcoes.adicionar(pagina2);
        opcoes.adicionar(Pagina.SAIR);

        Pagina menuPrincipal = new Pagina("Menu Principal", () ->
            tela.mostrarMenu("Menu", "Escolha uma opção:", opcoes)
        );

        tela.irPara(menuPrincipal);
    }
}
