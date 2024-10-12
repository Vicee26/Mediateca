
package atec.poo.mediateca.core;

public enum Categoria {
    SCITECH("Técnicas e/ou científicas"),
    FICTION("Ficção"),
    REFERENCE("Referência");

    private final String nome_categoria;

    Categoria (String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    @Override
    public String toString() {
        return this.nome_categoria;
    }
}
