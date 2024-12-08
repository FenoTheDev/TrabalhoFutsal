import java.util.Random;

public class JogadorFutsal {
    private String nome;
    private int idade;
    private int numero;
    private int gols_totais;
    private int gols_campeonatos;
    private int pontos_ataque;
    private int pontos_defesa;
    private PosicaoFutsal posicao;

    public JogadorFutsal(String nome, int idade, PosicaoFutsal posicao) {
        if (posicao == null) {
            throw new IllegalArgumentException("Posição não pode ser null");
        }
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.numero = new Random().nextInt(99) + 1;
        this.gols_totais = 0;
        this.gols_campeonatos = 0;
        gerarPontos();
    }

    private void gerarPontos() {
        Random rand = new Random();
        switch (posicao) {
            case GOLEIRO:
                pontos_ataque = rand.nextInt(4) + 1;
                pontos_defesa = rand.nextInt(6) + 10;
                break;
            case ALA:
                pontos_ataque = rand.nextInt(8) + 5;
                pontos_defesa = rand.nextInt(8) + 5;
                break;
            case FIXO:
                pontos_ataque = rand.nextInt(8) + 5;
                pontos_defesa = rand.nextInt(6) + 10;
                break;
            case PIVO:
                pontos_ataque = rand.nextInt(6) + 10;
                pontos_defesa = rand.nextInt(4) + 1;
                break;
            default:
                throw new IllegalArgumentException("Posicionamento inválido " + posicao);
        }
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getNumero() {
        return numero;
    }

    public int getGolsTotais() {
        return gols_totais;
    }

    public int getGolsCampeonato() {
        return gols_campeonatos;
    }

    public int getPontosAtaque() {
        return pontos_ataque;
    }

    public int getPontosDefesa() {
        return pontos_defesa;
    }

    public PosicaoFutsal getPosicao() {
        return posicao;
    }

    public void registrarGol() {
        this.gols_totais++;
        this.gols_campeonatos++;
    }

    public void reiniciarGolsCampeonato() {
        this.gols_campeonatos = 0;
    }
}
