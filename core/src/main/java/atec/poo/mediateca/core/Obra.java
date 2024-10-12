package atec.poo.mediateca.core;

public abstract class Obra implements Comparable<Obra> {
    protected int id;
    protected String titulo;
    protected Categoria categoria;
    protected int preco;
    protected int stock_total;
    protected int stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Obra(int id, String titulo, int preco, Categoria categoria, int stock_total) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.categoria = categoria;
        this.stock_total = stock_total;
        this.stock = stock_total;
    }

    /*@Override
    public int compareTo(Obra o) {
        return this.titulo.compareTo(o.titulo);
    }*/

}
