    public class Estatisticas {
        private int titulos;
        private int jogos;
        private int vitorias;
        private int empates;
        private int derrotas;
        private int gols_marcados;
        private int gols_sofridos;
        private int pontos;
    
        public void reiniciarEstatisticas() {
            this.titulos = 0;
            this.jogos = 0;
            this.vitorias = 0;
            this.empates = 0;
            this.derrotas = 0;
            this.gols_marcados = 0;
            this.gols_sofridos = 0;
            this.pontos = 0;  
        }
    
        public void incrementarTitulos(int titulos) {
            this.titulos += titulos;
        }
    
        public void incrementaGolsMarcados(int gols_marcados) {
            this.gols_marcados += gols_marcados;
        }
    
        public void incrementaGolsSofridos(int gols_sofridos) {
            this.gols_sofridos += gols_sofridos;
        }
    
        public void incrementaVitorias(int vitorias) {
            this.vitorias += vitorias;
            this.jogos++;
            this.pontos += 3;
        }
        
        public void incrementaDerrotas(int derrotas) {
            this.derrotas += derrotas;
            this.jogos++;
        }
    
        public void incrementaEmpate(int empates) {
            this.empates += empates;
            this.jogos++;
            this.pontos += 1;
        }
    
        public int saldoGols() {
            return this.gols_marcados - this.gols_sofridos;
        }
        public int getGolsMarcados(){
            return this.gols_marcados;
        }
        public int getGolsSofridos(){
            return this.gols_sofridos;
        }
    
        public void incrementaPontos(int pontos) {
            this.pontos += pontos;
        }
    
        public void exibirEstatisticas() {
            System.out.println("Graficos do time:");
            System.out.println("Titulos: " + this.titulos);
            System.out.println("Vitorias: " + this.vitorias);
            System.out.println("Derrotas: " + this.derrotas);
            System.out.println("Empates: " + this.empates);
            System.out.println("Pontos: " + this.pontos);
            System.out.println("Gols Marcados: " + this.gols_marcados);
            System.out.println("Gols Sofridos: " + this.gols_sofridos);
            System.out.println("Saldo de Gols: " + saldoGols());
            System.out.println("Jogos jogados: " + this.jogos);
        }
    
        public int getTitulos() {
            return this.titulos;
        }
        
        public int getEmpate() {
            return this.empates;
        }
    
        public int getPontos() {
            return this.pontos;
        }
    
        public int getVitorias() {
            return this.vitorias;
        }
    
        public int getDerrotas() {
            return this.derrotas;
        }
    
        public int getJogos() {
            return this.jogos;
        }
        
        @Override
        public String toString() {
            return "Estatísticas do Time:\n" +
                   "Títulos: " + this.titulos + "\n" +
                   "Jogos: " + this.jogos + "\n" +
                   "Pontos: " + this.pontos + "\n" +
                   "Vitórias: " + this.vitorias + "\n" +
                   "Empates: " + this.empates + "\n" +
                   "Derrotas: " + this.derrotas + "\n" +
                   "Gols Marcados: " + this.gols_marcados + "\n" +
                   "Gols Sofridos: " + this.gols_sofridos + "\n" +
                   "Saldo de Gols: " + this.saldoGols();
        }
    }
    
    
    

