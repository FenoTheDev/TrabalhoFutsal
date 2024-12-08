import java.util.ArrayList;
import java.util.Random;

public class TimeFutsal {
    private String nome;
    private ArrayList<JogadorFutsal> jogadores;
    private TecnicoFutsal tecnico;
    private Estatisticas estatisticas;

    TimeFutsal(String nome){
        this.jogadores = new ArrayList<>();
        this.nome = nome;
        this.tecnico = new TecnicoFutsal(nome, 0);
        this.estatisticas = new Estatisticas();
    }

    public TecnicoFutsal getTecnico() {
        return this.tecnico;
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<JogadorFutsal> getJogadores() {
        return this.jogadores;
    }

    public void gerarTimeRandom() { 
        String[] nomesJogadores = {"Sniper", "Carlos Personal", "Parrudo", "Neto", "Boga", "Muralha", "China", "Remix", "Gigante", "Fernandinho", "Jovinal", "Formiguinha", "Chamine", "Simba", "Neymar com Gripe", "Joranel", "Jorel", "Pimbinha", "Sidney", "Leanderson", "Lenerson", "Pijolinha"};
        PosicaoFutsal[] posicoes = {PosicaoFutsal.FIXO, PosicaoFutsal.ALA, PosicaoFutsal.ALA, PosicaoFutsal.GOLEIRO, PosicaoFutsal.PIVO}; 

        Random rand = new Random();

        for(int i = 0; i < posicoes.length; i++) {
            String nome = nomesJogadores[rand.nextInt(nomesJogadores.length)] + "_" + (i + 1);
            int idade = rand.nextInt(15) + 18; 
            PosicaoFutsal posicao = posicoes[i];

            JogadorFutsal jogador = new JogadorFutsal(nome, idade, posicao);
            jogadores.add(jogador);
        }

        String nomeTecnico = "Tec_" + nomesJogadores[rand.nextInt(nomesJogadores.length)];
        int idadeTecnico = 35 + rand.nextInt(30); 
        tecnico = new TecnicoFutsal(nomeTecnico, idadeTecnico);

        System.out.println("Time gerado automaticamente:"); 
        for (JogadorFutsal jogador : jogadores) {
            System.out.println("Nome: " + jogador.getNome() + ", Idade: " + jogador.getIdade() + ", Posicionamento: " + jogador.getPosicao() + ", Ataque: " + jogador.getPontosAtaque() + ", Defesa: " + jogador.getPontosDefesa());  
        }
        System.out.println("Técnico: " + tecnico.getNome() + ", Idade: " + tecnico.getIdade()); 
    }

    public void adicionarJogador(JogadorFutsal jogador) {
        jogadores.add(jogador);
    }

    public Estatisticas getEstatisticas() {
        return this.estatisticas;
    }

    public void contratarTecnico(TecnicoFutsal tecnico) {
        this.tecnico = tecnico;
        System.out.println("Técnico: " + tecnico.getNome());
        System.out.println("Contratado para o time: " + this.nome);
    }

    public void registrarVitoria(int vitorias) {
        this.estatisticas.incrementaVitorias(vitorias);
        System.out.println(vitorias + " Registrada(s) número de vitórias para o time: " + this.nome);
    }

    public void registrarDerrota(int derrotas) {
        this.estatisticas.incrementaDerrotas(derrotas);
        System.out.println(derrotas + " Registrada(s) número de derrotas para o time: " + this.nome);
    }

    public void registrarEmpate(int empates) {
        this.estatisticas.incrementaEmpate(empates);
        System.out.println(empates + " Registrada(s) número de empates para o time: " + this.nome);
    }

    public void registrarGols(int golsMarcados, int golsSofridos) {
        this.estatisticas.incrementaGolsMarcados(golsMarcados);
        this.estatisticas.incrementaGolsSofridos(golsSofridos);
        System.out.println("Gols feitos: " + golsMarcados);
        System.out.println("Gols levados: " + golsSofridos);
    }

    public int pontosAtaque() {
        int totalAtaque = 0;
        for (JogadorFutsal jogador : jogadores) {
            totalAtaque += jogador.getPontosAtaque();
        }
        return totalAtaque; 
    }

    public int pontosDefesa() {
        int totalDefesa = 0;
        for (JogadorFutsal jogador : jogadores) {
            totalDefesa += jogador.getPontosDefesa();
        }
        return totalDefesa; 
    }

    private int[] calcularPontosAcumulados() {
        int[] pontosAcumulados = new int[jogadores.size()];
        int acumulado = 0;
        for (int i = 0; i < jogadores.size(); i++) {
            acumulado += jogadores.get(i).getPontosAtaque();
            pontosAcumulados[i] = acumulado;
        }
        return pontosAcumulados;
    }

    public int goleador() {
        int pontosAtaque = pontosAtaque();
        int[] pontosAcumulados = calcularPontosAcumulados();

        Random rand = new Random();
        int dado = rand.nextInt(pontosAtaque) + 1; 

        for (int i = 0; i < pontosAcumulados.length; i++) {
            if (dado <= pontosAcumulados[i]) {
                return i; 
            }
        }
        return -1; 
    }


    public void exibirTime(){
        System.out.println("Nome do time: " + getNome());
        System.out.println("Número de Títulos: " + estatisticas.getTitulos());
        exibirTecnico();
        exibirJogadores();
    }

    public void exibirTecnico(){
        System.out.println("Técnico:");
        System.out.println("  Nome: " + tecnico.getNome());
        System.out.println("  Idade: " + tecnico.getIdade());
        System.out.println("  Especialidade: " + tecnico.getEspecialidade());
        System.out.println("  Pontos de Especialidade: " + tecnico.getPontosEspecialidade());
    }

    public void exibirJogadores() {
        System.out.println("Jogadores:");
        for (JogadorFutsal jogador : jogadores) {
            System.out.println("  Nome: " + jogador.getNome());
            System.out.println("  Idade: " + jogador.getIdade());
            System.out.println("  Número: " + jogador.getNumero());
            System.out.println("  Posição: " + jogador.getPosicao());
            System.out.println("  Pontos de Ataque: " + jogador.getPontosAtaque());
            System.out.println("  Pontos de Defesa: " + jogador.getPontosDefesa());
        }
    }

    public void exibirJogador(int numeroCamisa) {
        for (JogadorFutsal jogador : jogadores) {
            if (jogador.getNumero() == numeroCamisa) {
                System.out.println("Nome: " + jogador.getNome());
                System.out.println("Idade: " + jogador.getIdade());
                System.out.println("Número: " + jogador.getNumero());
                System.out.println("Posição: " + jogador.getPosicao());
                System.out.println("Pontos de Ataque: " + jogador.getPontosAtaque());
                System.out.println("Pontos de Defesa: " + jogador.getPontosDefesa());
                return;
            }
        }
        System.out.println("Jogador com o número " + numeroCamisa + " não encontrado.");
    }

    public void exibirEstatisticas(){
        estatisticas.exibirEstatisticas();
    }

    public void exibirEstatisticasLinha() {
        System.out.print("Títulos: " + estatisticas.getTitulos() + " ");
        System.out.print("Jogos: " + estatisticas.getJogos() + " ");
        System.out.print("Vitórias: " + estatisticas.getVitorias() + " ");
        System.out.print("Empates: " + estatisticas.getEmpate() + " ");
        System.out.print("Derrotas: " + estatisticas.getDerrotas() + " ");
        System.out.print("Gols Marcados: " + estatisticas.getGolsMarcados() + " ");
        System.out.print("Gols Sofridos: " + estatisticas.getGolsSofridos() + " ");
        System.out.print("Saldo de Gols: " + estatisticas.saldoGols() + " ");
        System.out.println("Pontos: " + estatisticas.getPontos());
    }
    public void exibirGols() {
        System.out.println("Gols feitos: " + this.estatisticas.getGolsMarcados());
        System.out.println("Gols levados: " + this.estatisticas.getGolsSofridos());
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setJogadores(ArrayList<JogadorFutsal> jogadores){
        this.jogadores = jogadores;
    }

}
 