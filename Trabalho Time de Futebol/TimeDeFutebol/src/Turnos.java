enum Turno {
    PRIMEIRO("Primeiro"), SEGUNDO("Segundo");

    private String descricao;

    Turno(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}