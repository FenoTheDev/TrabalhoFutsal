import java.util.Random;

enum Especialidade {
    OFENSIVA("Ofensiva"), DEFENSIVA("Defensiva");

    String especialidade;

    Especialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return this.especialidade;
    }

    public static Especialidade geraEspecialidade() {
        Random rand = new Random();
        int especialidade = rand.nextInt(2);

        if(especialidade == Especialidade.OFENSIVA.ordinal()) {
            return Especialidade.OFENSIVA;
        } 
        else {
           return Especialidade.DEFENSIVA; 
        }
    }
}