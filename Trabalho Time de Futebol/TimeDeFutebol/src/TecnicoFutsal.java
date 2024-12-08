import java.util.Random;
public class TecnicoFutsal{

    private String nome;
    private int idade;
    private PosicaoFutsal posicao;
    private Especialidade especialidade;
    private int pontosEspecialidade;

    TecnicoFutsal(String nome, int idade){
        this.nome= nome;
        this.idade= idade;
        this.posicao=PosicaoFutsal.TECNICO;

        Random rand = new Random();
        boolean condicao = rand.nextBoolean();

        if(condicao){
            this.especialidade=Especialidade.OFENSIVA;
        }
        else{
            this.especialidade=Especialidade.DEFENSIVA;
        }
        this.pontosEspecialidade=rand.nextInt(5)+1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        if(idade >= 25){
            this.idade=idade;
        }
        else{
            System.out.println("ERRO: idade inválida!");
        }
    }
    
    public PosicaoFutsal getPosicao() {
        return posicao;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public int getPontosEspecialidade() {
        return pontosEspecialidade;
    }

    public void setPontosEspecialidade(int pontosEspecialidade) {
        if(pontosEspecialidade >= 1 && pontosEspecialidade <= 5){
            this.pontosEspecialidade = pontosEspecialidade;
        }
        else{
            System.out.println("ERRO: pontos inválidos!");
        }
    }

    public void exibirPerfil(){
        System.out.println("NOME DO TÉCNICO: "+this.getNome());
        System.out.println("IDADE DO TÉCNICO: "+this.getIdade());
        System.out.println("ESPECIALIDADE DO TÉCNICO: "+this.getEspecialidade());
        System.out.println("PONTOS DO TÉCNICO: "+this.getPontosEspecialidade());
    }
}