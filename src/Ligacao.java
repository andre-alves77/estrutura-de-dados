public class Ligacao {
    private static Integer proxId = 0;
    private Integer id;
    private Cidade origem;
    private Cidade destino;
    private double distanciaKm;
    private double fatorTrafego;
    private int qtdPedagios;
    private boolean maoDupla;

    private int gerarId(){
        return proxId++;
    }

    public Ligacao(Cidade origem, Cidade destino, double distancia, double fatorTrafego, int qtdPedagios, boolean maoDupla){
        if(distancia < 0){
            throw new RuntimeException("A distância não pode ser menor que zero");
        }else if(fatorTrafego < 0){
            throw new RuntimeException("O fator não pode ser menor que zero");
        }else if(qtdPedagios < 0){
            throw new RuntimeException("A quantidade de pedagios não pode ser menor que zero");
        }

        this.id = gerarId();
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distancia;
        this.fatorTrafego = fatorTrafego;
        this.qtdPedagios = qtdPedagios;
        this.maoDupla = maoDupla;
    }

    // Contrutor para a ligação de volta
    public Ligacao(int id, Cidade origem, Cidade destino, double distancia, double fatorTrafego, int qtdPedagios, boolean maoDupla){
        if (id >= proxId){
            throw new RuntimeException("O id da ligação de volta pertencer a outra ligação");
        }else if(distancia < 0){
            throw new RuntimeException("A distância não pode ser menor que zero");
        }else if(fatorTrafego < 0 || fatorTrafego > 2){
            throw new RuntimeException("O fator não pode ser menor que zero ou maior que 2");
        }else if(qtdPedagios < 0){
            throw new RuntimeException("A quantidade de pedagios não pode ser menor que zero");
        }

        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distancia;
        this.fatorTrafego = fatorTrafego;
        this.qtdPedagios = qtdPedagios;
        this.maoDupla = maoDupla;
    }

    public Cidade getOrigem() {
        return origem;
    }

    public Cidade getDestino() {
        return destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public double getFatorTrafego() {
        return fatorTrafego;
    }

    public void setFatorTrafego(double fatorTrafego) {
        this.fatorTrafego = fatorTrafego;
    }

    public int getQtdPedagios() {
        return qtdPedagios;
    }

    public void setQtdPedagios(int qtdPedagios) {
        this.qtdPedagios = qtdPedagios;
    }

    public boolean isMaoDupla() {
        return maoDupla;
    }

    public void setMaoDupla(boolean maoDupla) {}


    public Double calcularTempo(){
        return (distanciaKm * fatorTrafego) + (qtdPedagios * 2);
    }

    public Integer getId() {
        return id;
    }
}