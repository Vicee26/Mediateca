package atec.poo.mediateca.core;

public enum Assiduidade {
    NORMAL("Normal"),
    FALTOSO("Faltoso"),
    CUMPRIDOR("Cumpridor");

    private String nome_assiduidade;

    Assiduidade (String nome_assiduidade) {
        this.nome_assiduidade = nome_assiduidade;
    }

    @Override
    public String toString() {
        return this.nome_assiduidade;
    }
}
