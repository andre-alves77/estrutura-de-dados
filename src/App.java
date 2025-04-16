public class App {
    private final Navegador navegador = new Navegador();
    private ListaDupla<Cidade> cidades = new ListaDupla<>();

    public void iniciar(){

        Pagina menuPrincipal = new Pagina("Menu Principal", this::menuPrincipal);

        navegador.irPara(menuPrincipal);
    }

    private void menuPrincipal() {
        Pagina cadastrarCidade = new Pagina("Cadastrar Cidade", this::cadastrarCidade);
        Pagina cadastrarLigacoes = new Pagina("Cadastrar Ligação", this::cadastrarLigacoes);
        Pagina listarCidades = new Pagina("Listar Cidades", this::listarCidades);
        Pagina estimarTempo = new Pagina("Estimar Tempo de Entrega", this::estimarTempo);
        Pagina listarLigacoesPorTempo = new Pagina("Listar Diretas por Tempo", this::listarLigacoesPorTempo);

        ListaDupla<Pagina> paginas = new ListaDupla<>();
        paginas.inserir(cadastrarCidade);
        paginas.inserir(cadastrarLigacoes);
        paginas.inserir(listarCidades);
        paginas.inserir(estimarTempo);
        paginas.inserir(listarLigacoesPorTempo);

        navegador.mostrarMenu("ExpressLine", "Escolha uma opção", paginas);

    }

    private void cadastrarLigacoes()  {
        String titulo = "Cadastro de Ligação";
        Cidade partida = selecionarCidade(titulo,"Nome da cidade de partida:");
        Cidade destino = selecionarCidade(titulo,"Nome da cidade de destino:");
        Double distancia = navegador.pedirInput(titulo, "Distância entre " + partida.getNome() +" e " + destino.getNome() + " (KM)", Double.class);
        Double fator = navegador.pedirInput(titulo, "Fator de trafego:", Double.class);
        Integer pedagios = navegador.pedirInput(titulo, "Quantidade de pegágios", Integer.class);
        Boolean isMaoDupla = navegador.pedirInput(titulo, "É um caminho de mão dupla (ida e volta)? s ou n", Boolean.class);

        if(isMaoDupla){
            Double distanciaVolta = navegador.pedirInput(titulo, "Distância de " + destino.getNome() + " até " + partida.getNome(), Double.class);
            Double fatorVolta = navegador.pedirInput(titulo, "Fator de trafego de " + destino.getNome() + " até " + partida.getNome(), Double.class);
            Integer pedagiosVolta = navegador.pedirInput(titulo, "Quantidade de pegágios de " + destino.getNome() + " até " + partida.getNome(), Integer.class);
            partida.adicionarConexaoDupla(destino,partida, distancia, fatorVolta, pedagiosVolta, isMaoDupla,distanciaVolta, fatorVolta, pedagiosVolta);
        }else{
            partida.adicionarConexao(partida,destino, distancia, fator, pedagios, isMaoDupla);
        }

    }

    private void cadastrarCidade() {
        String nome = navegador.pedirInput("Cadastro de Cidade", "Nome:", String.class);

        Cidade cidade = new Cidade(nome);
        for (No<Cidade> c = cidades.getInicio(); c!=null; c = c.getProximo()){
            if (c.getDado().equals(cidade)){
                navegador.mostrarMensagem("Erro", "Cidade já cadastrada");
                return;
            }
        }
            cidades.inserir(cidade);
    }

    private Cidade selecionarCidade(String titulo, String msg) {
        String nome = navegador.pedirInput(titulo, msg, String.class);
        for(No<Cidade> c=cidades.getInicio(); c!= null; c=c.getProximo()){
            if(c.getDado().getNome().equals(nome)){
                return  c.getDado();
            }
        }
        throw new RuntimeException("Nenhuma cidade cadastrada com o nome: " + nome);
    }

    private void listarCidades() {
        String msg="";
        if (cidades.getTamanho() == 0){
            msg = "Não há cidades cadastradas";
        }
        for (No<Cidade> c = cidades.getInicio(); c!=null; c = c.getProximo()){
            msg += c.getDado().toString() + "\n";

            ListaDupla<Ligacao> ligacoes = c.getDado().obterConexoes();

            if (ligacoes.getTamanho()!=0){
                msg += "\t "+ "Conexões diretas:" + "\n";

                for (No<Ligacao> l = ligacoes.getInicio(); l!=null; l = l.getProximo()){
                    msg+="     "+ l.getDado().getDestino().toString() + "\n";
                }
                 msg+="\n";
            }
        }
        navegador.mostrarMensagem("Cidades", msg);
    }

    private void estimarTempo() {
        String titulo = "Procurando melhor conexão";
        Cidade partida = selecionarCidade(titulo,"Nome da cidade de partida:");
        Cidade destino = selecionarCidade(titulo,"Nome da cidade de destino:");

        navegador.mostrarMensagem(titulo,partida.procurarMelhorConexao(destino.getNome()).calcularTempo().toString());
    }

    private void listarLigacoesPorTempo() {
        Double tempo = navegador.pedirInput("Ligações por tempo", "Insira os minutos", Double.class);
        String msg = "Conexões possiveis:\n";
        for(No<Cidade> cidade = cidades.getInicio(); cidade!=null; cidade=cidade.getProximo()){
            for(No<Ligacao> ligacao = cidade.getDado().obterConexoes().getInicio(); ligacao!=null; ligacao=ligacao.getProximo()){
                double tempoLigacao = tempo-ligacao.getDado().calcularTempo();
                if (tempoLigacao >= 0) {
                    msg += "\n"+ligacao.getDado().getOrigem()+" até "+ligacao.getDado().getDestino()+" -- "+ligacao.getDado().calcularTempo()+"min";
                }
            }
        }
        if (msg.equals("Conexões possiveis:\n")){
            navegador.mostrarMensagem("Nenhuma conexão é possivel", msg);
        }else{
        navegador.mostrarMensagem("Ligações por tempo", msg);
        }
    }


}