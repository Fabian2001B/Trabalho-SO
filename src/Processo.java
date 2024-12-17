public class Processo {
    private int chegada;
    private int duracao;

    public Processo(int chegada, int duracao) {
        this.chegada = chegada;
        this.duracao = duracao;
    }

    public int getChegada() {
        return chegada;
    }

    public int getDuracao() {
        return duracao;
    }
}