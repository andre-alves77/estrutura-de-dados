import java.util.Objects;

// eu não quis implmentar a logica de remover conexão
public class Cidade {
    private String nome;
    private ListaDupla<Ligacao> conexoes = new ListaDupla<>();

    public Cidade(String nome){
        if(nome.isEmpty()){
            throw new RuntimeException("O nome da cidade deve ser preenchido");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    public ListaDupla<Ligacao> obterConexoes(){
        ListaDupla<Ligacao> con = new ListaDupla<>();
        for(No<Ligacao> no = conexoes.getInicio(); no!=null;no=no.getProximo()){
            con.inserir(no.getDado());
        }
        return con;
    }


    public Ligacao procurarMelhorConexao(String nomeDestino) {
        Ligacao melhorLigacao = null;
        double menorTempo = Double.MAX_VALUE;

        for (No<Ligacao> no = conexoes.getInicio(); no != null; no = no.getProximo()) {
            Ligacao ligacao = no.getDado();
            if (ligacao.getDestino().getNome().equals(nomeDestino)) {
                double tempo = ligacao.calcularTempo();
                if (tempo < menorTempo) {
                    melhorLigacao = ligacao;
                    menorTempo = tempo;
                }
            }
        }

        if (melhorLigacao == null) {
            throw new RuntimeException("Nenhuma conexão encontrada de " + nome + " até " + nomeDestino);
        }

        return melhorLigacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(nome, cidade.nome);
    }

    @Override
    public String toString() {
        return nome;
    }

    public void adicionarConexaoDupla(Cidade destino, Cidade partida, Double distancia, Double fator, Integer pedagios, Boolean isMaoDupla, Double distanciaVolta,Double fatorVolta, Integer pedagiosVolta) {
        Ligacao ligacao = this.adicionarConexao(partida,destino,distancia,fator, pedagios,isMaoDupla);
        destino.adicionarConexao(destino,partida,distanciaVolta,fatorVolta, pedagiosVolta,isMaoDupla, ligacao);

    }

    public Ligacao adicionarConexao(Cidade partida, Cidade destino, Double distancia, Double fator, Integer pedagios, Boolean isMaoDupla) {
        if (nome.equals(partida.getNome()) && destino.getNome().equals(partida.getNome())){
            throw new RuntimeException("Erro: A conexão com destino e partida iguais não pode ser realizada");
        }
        Ligacao ligacao = new Ligacao(partida,destino,distancia,fator, pedagios,isMaoDupla);
        conexoes.inserir(ligacao);
        return ligacao;
    }

    private Ligacao adicionarConexao(Cidade partida, Cidade destino, Double distancia, Double fator, Integer pedagios, Boolean isMaoDupla, Ligacao ligacaoIda) {
        if (nome.equals(partida.getNome()) && destino.getNome().equals(partida.getNome())){
            throw new RuntimeException("Erro: A conexão com destino e partida iguais não pode ser realizada");
        }
        Ligacao ligacao = new Ligacao(ligacaoIda.getId(),partida,destino,distancia,fator, pedagios,isMaoDupla);
        conexoes.inserir(ligacao);
        return ligacao;
    }
}
