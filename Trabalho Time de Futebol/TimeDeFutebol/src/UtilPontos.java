import java.util.Random;

public class UtilPontos {
    public static int lancarDados(int numeroDados) {
        Random random = new Random();
        int resultado = 0;
        for (int i = 0; i < numeroDados; i++) {
            resultado += random.nextInt(6) + 1;
        }
        return resultado;
    }
}
