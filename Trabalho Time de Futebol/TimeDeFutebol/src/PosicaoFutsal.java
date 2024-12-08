import java.util.ArrayList;

enum PosicaoFutsal {
    GOLEIRO("Goleiro"), FIXO("Fixo"), ALA("Ala"), PIVO("Pivo"), TECNICO("Tecnico");

    String posicao;

    PosicaoFutsal(String posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return this.posicao;
    }

    public static ArrayList<PosicaoFutsal> posicoesTime() {
        ArrayList<PosicaoFutsal> posicoes = new ArrayList<PosicaoFutsal>();

        posicoes.add(GOLEIRO);
        posicoes.add(FIXO);
        posicoes.add(ALA);
        posicoes.add(ALA);
        posicoes.add(PIVO);

        return posicoes;
    }
}