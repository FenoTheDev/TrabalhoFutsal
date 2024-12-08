import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class CampeonatoFutsal {
    private ArrayList<TimeFutsal> times;
    private ArrayList<Rodada> rodadas;

    CampeonatoFutsal() {
        this.times = new ArrayList<TimeFutsal>();
        this.rodadas = new ArrayList<Rodada>();
    }

    public void gerarCampeonato() {
        if(this.times.size() > 0) {
            this.gerarRodadas();
        }
    }

    public void gerarCampeonato(int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            TimeFutsal time = new TimeFutsal("Time " + (i + 1));
            time.gerarTimeRandom();
            times.add(time);
        }
        this.gerarRodadas(); // Gera as rodadas do campeonato
    }
    


    public int getNTimes() {
        return this.times.size();
    }

    private void gerarRodadas() {
        ArrayList<Integer> indicesTimes = new ArrayList<Integer>();
        this.rodadas.clear();

        int nTimes = this.getNTimes();
        for(int i = 0; i < nTimes; i++) {
            indicesTimes.add(i);
        }

        if(nTimes % 2 == 1) {
            indicesTimes.add(0,-1);
        }

        int t = indicesTimes.size();
        int m = indicesTimes.size() / 2;
        for (int i = 0; i < t - 1; i++) {
            Rodada rodada = new Rodada();
            for (int j = 0; j < m; j++) {
                //Clube está de fora nessa rodada?              
                if (indicesTimes.get(j) == -1)
                    continue;

                //Teste para ajustar o mando de campo
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    TimeFutsal timeA = this.times.get(indicesTimes.get(t - j - 1));
                    TimeFutsal timeB = this.times.get(indicesTimes.get(j));

                    Partida partida = new Partida(timeA, timeB);
                    rodada.adicionarPartida(partida);
                } else {
                    TimeFutsal timeA = this.times.get(indicesTimes.get(j));
                    TimeFutsal timeB = this.times.get(indicesTimes.get(t - j - 1));

                    Partida partida = new Partida(timeA, timeB);
                    rodada.adicionarPartida(partida);
                }
            }
            this.rodadas.add(rodada);    
            indicesTimes.add(1, indicesTimes.remove(indicesTimes.size()-1));
        }

        // adicionar as partidas e ajustar o segundo turno
        int nRodadas = this.rodadas.size();
        for(int i = 0; i < nRodadas; i ++) {
            Rodada rodada = new Rodada();
            ArrayList<Partida> partidas = this.rodadas.get(i).getPartidas();
            for (Partida partida : partidas) {
                Partida aux = new Partida(partida.getTimeB(), partida.getTimeA());
                rodada.adicionarPartida(aux);
            }            

            this.rodadas.add(rodada);
        }
    }
    public void imprimirRodadas(boolean comResultado) throws InterruptedException {
        int index = 1;
    
        for (Rodada rodada : rodadas) {
            System.out.println("\n----------- RODADA " + index++ + " -----------");
            rodada.exibirPartidasRodada(comResultado);
        }
    }
    

    public void exibirTabelaClassificacao() {
        ArrayList<TimeFutsal> aux = this.classificacao();
        
        System.out.print("\n\n");
        System.out.println("********************** TABELA DE CLASSIFICACAO *********************");
        System.out.println("--------------------------------------------------------------------");
        System.out.println(" TIME     PT     J      V       E       D       GM      GS      SG");
        System.out.println("--------------------------------------------------------------------");        
        for (TimeFutsal time : aux) {
            System.out.print(time.getNome() + "   ");
            time.exibirEstatisticasLinha();
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------");        
    }

    public void exibirTabelaGoleadores() {
        ArrayList<JogadorFutsal> goleadores = this.goleadoresCampeonato();
        this.ordenarGoleadores(goleadores); // Ordena do maior para o menor número de gols
    
        System.out.println("\n*************** TABELA DE GOLEADORES ***************");
        System.out.printf("%-15s %-10s %-10s\n", "NOME", "GOLS", "TIME");
        System.out.println("--------------------------------------------------");
    
        for (JogadorFutsal jogador : goleadores) {
            TimeFutsal time = encontrarTimeDoJogador(jogador); // Encontra o time
            String nomeTime = (time != null) ? time.getNome() : "Time não encontrado";
            System.out.printf("%-15s %-10d %-10s\n", jogador.getNome(), jogador.getGolsCampeonato(), nomeTime);
        }
    }
    

    
    private void ordenarGoleadores(ArrayList<JogadorFutsal> goleadores) {
        boolean swapped;
        int nTimes = goleadores.size();
        for(int i = 0; i < nTimes; i++) {
            swapped = false;
            for(int j = 0; j < nTimes - i - 1; j++) {
                if(goleadores.get(j+1).getGolsCampeonato() > goleadores.get(j).getGolsCampeonato()) {
                    Collections.swap(goleadores,j,j+1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public ArrayList<JogadorFutsal> goleadoresCampeonato() {
        ArrayList<JogadorFutsal> goleadores = new ArrayList<>();
        for (TimeFutsal time : this.times) {
            for (JogadorFutsal jogador : time.getJogadores()) {
                if (jogador.getGolsCampeonato() > 0) { // Verifica se o jogador tem gols
                    goleadores.add(jogador);
                }
            }
        }
        return goleadores;
    }
    


private TimeFutsal encontrarTimeDoJogador(JogadorFutsal jogador) {
    for (TimeFutsal time : this.times) {
        if (time.getJogadores().contains(jogador)) {
            return time;
        }
    }
    return null; // Caso o jogador não esteja em nenhum time
}

public void exibirGolsPorTime() {
    for (TimeFutsal time : this.times) {
        ArrayList<JogadorFutsal> jogadores = time.getJogadores();
        ArrayList<JogadorFutsal> artilheiros = goleadorTime(time);

        System.out.println("\n************** " + time.getNome().toUpperCase() + " **************");
        System.out.println("  JOGADOR       GOLS ");
        System.out.println("------------------------------------");

        for (JogadorFutsal jogador : jogadores) {
            System.out.print(" " + jogador.getNome().substring(0, jogador.getNome().indexOf("_")) + "\t " + jogador.getGolsCampeonato());

            // Marca o jogador como artilheiro apenas se ele tiver gols e estiver na lista de artilheiros
            if (jogador.getGolsCampeonato() > 0 && artilheiros.contains(jogador)) {
                System.out.print(" <ARTILHEIRO>");
            }
            System.out.println();
        }
    }
}


private ArrayList<JogadorFutsal> goleadorTime(TimeFutsal time) {
    ArrayList<JogadorFutsal> lista_jogadores = time.getJogadores();
    ArrayList<JogadorFutsal> goleadores = new ArrayList<>();

    int maiorNumeroGols = 0;

    for (JogadorFutsal jogador : lista_jogadores) {
        int golsJogador = jogador.getGolsCampeonato();
        if (golsJogador > maiorNumeroGols) {
            goleadores.clear(); // Limpa a lista apenas quando encontra um jogador com mais gols
            goleadores.add(jogador);
            maiorNumeroGols = golsJogador;
        } else if (golsJogador == maiorNumeroGols) {
            goleadores.add(jogador); // Adiciona à lista se tiver a mesma quantidade de gols
        }
    }

    return goleadores;
}

    private ArrayList<TimeFutsal> classificacao() {
        ArrayList<TimeFutsal> aux = new ArrayList<TimeFutsal>();
        
        for (TimeFutsal time : this.times) {
            aux.add(time);
        }

        this.ordenarTimes(aux);
        return aux;
    }

    private void ordenarTimes(ArrayList<TimeFutsal> listaTimes) {
        boolean swapped;
        int nTimes = listaTimes.size();
        for(int i = 0; i < nTimes; i++) {
            swapped = false;
            for(int j = 0; j < nTimes - i - 1; j++) {
                if(this.timeB_melhor_timeA(listaTimes.get(j),listaTimes.get(j+1))) {
                    Collections.swap(listaTimes,j,j+1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
    

    public ArrayList<JogadorFutsal> getArtilheiro() {
        ArrayList<JogadorFutsal> goleadores = this.goleadoresCampeonato();
        this.ordenarGoleadores(goleadores);

        ArrayList<JogadorFutsal> artilheiroCampeonato = new ArrayList<JogadorFutsal>();
        int nGols = goleadores.get(0).getGolsCampeonato();
        
        for (JogadorFutsal jogadorFutsal : goleadores) {
            if(nGols == jogadorFutsal.getGolsCampeonato()) {
                artilheiroCampeonato.add(jogadorFutsal);
            } else {
                break;
            }   
        }        

        return artilheiroCampeonato;
    }
    

    public TimeFutsal getCampeao() {
        TimeFutsal campeao = this.times.get(0);

        for(int i = 1; i < this.getNTimes(); i++) {
            TimeFutsal timeB = this.times.get(i);
            if(this.timeB_melhor_timeA(campeao, timeB)) {
                campeao = timeB;
            }
        }

        return campeao;
    }

    private boolean timeB_melhor_timeA(TimeFutsal melhor, TimeFutsal timeB) {
        Estatisticas estatisticaMelhor = melhor.getEstatisticas();
        Estatisticas estatisticaB = timeB.getEstatisticas();

        int pontosMelhor = estatisticaMelhor.getPontos();
        int pontosB = estatisticaB.getPontos();
        
        if(pontosB > pontosMelhor) {
            return true;
        } else if (pontosB == pontosMelhor) {
            int vitoriasMelhor = estatisticaMelhor.getVitorias();
            int vitoriasB = estatisticaB.getVitorias();

            if(vitoriasB > vitoriasMelhor) {
                return true;
            } else if (vitoriasB == vitoriasMelhor) {
                int saldoMelhor = estatisticaMelhor.saldoGols();
                int saldoB = estatisticaB.saldoGols();

                if(saldoB > saldoMelhor) {
                    return true;
                } else if(saldoB == saldoMelhor) {
                    int golsMelhor = estatisticaMelhor.getGolsMarcados();
                    int golsB = estatisticaB.getGolsMarcados();

                    if(golsB > golsMelhor) {
                        return true;
                    } else if(golsB == golsMelhor) {
                        Random rand = new Random();

                        if(rand.nextInt(2) == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }

        return false;
    }

    private void inicializarPontuacoesTimes() {
        for (TimeFutsal time : this.times) {
            time.getEstatisticas().reiniciarEstatisticas();
            for (JogadorFutsal jogador : time.getJogadores()) {
                jogador.reiniciarGolsCampeonato();
            }
        }
    }

    public void iniciarCampeonato() throws InterruptedException {      
        this.exibeInicioCampeonato();        
        this.inicializarPontuacoesTimes();        
        
        int nRodada = 1;
        Turno turno = Turno.PRIMEIRO;

        for (Rodada rodada : rodadas) {
            if(nRodada == 1 || nRodada == this.getNTimes()) {
                System.out.println("\n #####################################");
                System.out.println("            " + turno.toString().toUpperCase() + " TURNO ");
                System.out.println(" #####################################");
            }
            
            System.out.println("\n------------ RODADA " + nRodada + " ------------");

            rodada.realizarRodada();
            rodada.exibirPartidasRodada(true);

            nRodada++;
            Thread.sleep(1000);

            if(nRodada == this.getNTimes() - 1) {
                turno = Turno.SEGUNDO;
            }
        }        
    }
    
    public void exibirArtilheiros() {
        ArrayList<JogadorFutsal> artilheiros = this.getArtilheiro();
    
        System.out.println("\n>>>> ARILHEIRO(S):");
        if (artilheiros.isEmpty()) {
            System.out.println("Nenhum artilheiro encontrado.");
            return;
        }
    
        for (JogadorFutsal jogador : artilheiros) {
            TimeFutsal time = encontrarTimeDoJogador(jogador); // Usa o método para encontrar o time
            String nomeTime = (time != null) ? time.getNome() : "Time não encontrado";
            System.out.printf(" - %s (%s) - Gols: %d\n", jogador.getNome(), nomeTime, jogador.getGolsCampeonato());
        }
    }
    
    

    private void exibeInicioCampeonato() throws InterruptedException {
        System.out.print("\n ***** INICIANDO   CAMPEONATO  BOLA NA TRAVE");
        for(int i = 0; i < 10; i++) {
            System.out.print(" .");
            Thread.sleep(250);
        }
        System.out.println();
    }
} 

