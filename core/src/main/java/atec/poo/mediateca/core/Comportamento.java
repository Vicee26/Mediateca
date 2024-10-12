package atec.poo.mediateca.core;

public enum Comportamento {
    ATIVO("Ativo"),
    SUSPENSO("Suspenso");

    private final String nome_comportamento;

    Comportamento (String nome_comportamento) {
        this.nome_comportamento = nome_comportamento;
    }

    @Override
    public String toString() {
        return this.nome_comportamento;
    }
}
