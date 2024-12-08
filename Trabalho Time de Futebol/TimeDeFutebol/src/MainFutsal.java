import java.util.Scanner;
import java.util.ArrayList;

public class MainFutsal {

    public static void main(String[] args) throws InterruptedException {
        CampeonatoFutsal campeonato = new CampeonatoFutsal();
        Scanner in = new Scanner(System.in);
        int nTimes, visualizarGols;

        // Loop para garantir uma entrada válida para o número de times
        while (true) {
            try {
                System.out.print("\n => Digite o número de times: ");
                nTimes = in.nextInt();
                if (nTimes < 2) {
                    System.out.println("Por favor, insira um número de times maior ou igual a 2.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Por favor, insira um número inteiro válido.");
                in.next(); // Limpar a entrada inválida
            }
        }

        MainFutsal.menuGeraCampeonato();
        campeonato.gerarCampeonato(nTimes);

        // Se desejar imprimir as rodadas com resultado
        // campeonato.imprimirRodadas(true);

        campeonato.iniciarCampeonato();
        campeonato.exibirTabelaClassificacao();

        System.out.println("\n >>>>> TIME CAMPEAO: " + campeonato.getCampeao().getNome());

        campeonato.exibirTabelaGoleadores();

        ArrayList<JogadorFutsal> artilharia = campeonato.getArtilheiro();
        System.out.print("\n >>>>> ARTILHEIRO(S): ");
        for (JogadorFutsal jogadorFutsal : artilharia) {
            System.out.print(jogadorFutsal.getNome() + " ");
        }

        System.out.println("\n");

        // Loop para garantir uma entrada válida para visualizar os autores dos gols
        while (true) {
            try {
                System.out.print("\n => Visualizar os autores dos gols <1 - Sim, 0 - Não>? ");
                visualizarGols = in.nextInt();
                if (visualizarGols == 0 || visualizarGols == 1) {
                    break;
                } else {
                    System.out.println("Por favor, insira 1 para Sim ou 0 para Não.");
                }
            } catch (Exception e) {
                System.out.println("Por favor, insira um número inteiro válido.");
                in.next(); // Limpar a entrada inválida
            }
        }

        if (visualizarGols == 1) {
            campeonato.exibirGolsPorTime();
        }

        System.out.println();
        in.close();
    }

    private static void menuGeraCampeonato() throws InterruptedException {
        System.out.print("\n ***** GERANDO CAMPEONATO");
        for (int i = 0; i < 11; i++) {
            System.out.print(" .");
            Thread.sleep(250);
        }
        System.out.println();
    }
}
