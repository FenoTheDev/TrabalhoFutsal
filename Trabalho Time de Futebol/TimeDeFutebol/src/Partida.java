public class Partida {
    private TimeFutsal timeA;
    private TimeFutsal timeB;
    private Resultado resultado;

    Partida(TimeFutsal timeA, TimeFutsal timeB) {
        if (timeA != null && timeB != null) {
            this.timeA = timeA;
            this.timeB = timeB;
        }
        this.resultado = new Resultado();
    }

    public Resultado getResultado() {
        return this.resultado;
    }

    public Resultado realizarPartida(int turnos) {
        int ataqueA = this.timeA.pontosAtaque();
        int defesaA = this.timeA.pontosDefesa();

        int ataqueB = this.timeB.pontosAtaque();
        int defesaB = this.timeB.pontosDefesa();

        int dadosA, dadosB;

        for (int i = 0; i < turnos; i++) {
            if (i % 2 == 0) {
                dadosA = UtilPontos.lancarDados(ataqueA);
                dadosB = UtilPontos.lancarDados(defesaB);

                if (dadosA > dadosB) {
                    this.resultado.incrementaGolsA();
                    timeA.registrarGols(1, 0); // Incrementa um gol para timeA
                    timeB.registrarGols(0, 1); // Incrementa um gol sofrido para timeB
                    int indiceGoleador = timeA.goleador();
                    if (indiceGoleador != -1) {
                        timeA.getJogadores().get(indiceGoleador).registrarGol();
                    }
                }
            } else {
                dadosA = UtilPontos.lancarDados(defesaA);
                dadosB = UtilPontos.lancarDados(ataqueB);

                if (dadosB > dadosA) {
                    this.resultado.incrementaGolsB();
                    timeB.registrarGols(1, 0); // Incrementa um gol para timeB
                    timeA.registrarGols(0, 1); // Incrementa um gol sofrido para timeA
                    int indiceGoleador = timeB.goleador();
                    if (indiceGoleador != -1) {
                        timeB.getJogadores().get(indiceGoleador).registrarGol();
                    }
                }
            }
        }
        return resultado;
    }

    public void exibirPartida(boolean comResultado) {
        if (comResultado) {
            System.out.println(this.timeA.getNome() + " [" + this.resultado.getGolsA() + "] x [" + this.resultado.getGolsB() + "] " + this.timeB.getNome());
        } else {
            System.out.println(this.timeA.getNome() + " [ ] x [ ] " + this.timeB.getNome());
        }
    }

    public TimeFutsal getTimeA() {
        return this.timeA;
    }

    public TimeFutsal getTimeB() {
        return this.timeB;
    }
}
