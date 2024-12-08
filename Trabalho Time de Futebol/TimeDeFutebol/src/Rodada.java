import java.util.ArrayList;

public class Rodada {
    ArrayList<Partida> partidas;

    public Rodada() {
        this.partidas = new ArrayList<Partida>();
    }

    public void adicionarPartida(Partida partida) {
        if (partida != null) {
            this.partidas.add(partida);
        }
    }

    public void limparRodada() {
        this.partidas.clear();
    }

    public ArrayList<Resultado> realizarRodada() {
        if (this.partidas.size() > 0) {
            ArrayList<Resultado> resultados = new ArrayList<Resultado>();

            for (Partida partida : partidas) {
                Resultado resultado = partida.realizarPartida(8);
                resultados.add(resultado);

                TimeFutsal timeA = partida.getTimeA();
                TimeFutsal timeB = partida.getTimeB();

                int golsA = resultado.getGolsA();
                int golsB = resultado.getGolsB();

                if (golsA > golsB) {
                    timeA.registrarVitoria(1);
                    timeB.registrarDerrota(1);
                } else if (golsA < golsB) {
                    timeB.registrarVitoria(1);
                    timeA.registrarDerrota(1);
                } else {
                    timeA.registrarEmpate(1);
                    timeB.registrarEmpate(1);
                }

                timeA.registrarGols(golsA, golsB);
                timeB.registrarGols(golsB, golsA);
            }

            return resultados;
        }

        return null;
    }

    public void exibirPartidasRodada(boolean comResultado) {
        for (Partida partida : this.partidas) {
            partida.exibirPartida(comResultado);
        }
    }

    public ArrayList<Partida> getPartidas() {
        return this.partidas;
    }
}
