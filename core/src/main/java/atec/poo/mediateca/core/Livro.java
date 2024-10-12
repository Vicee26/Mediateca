package atec.poo.mediateca.core;

public class Livro extends Obra{
    private String ISBN;
    private String autor;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Livro(int id, String titulo,  String autor, int preco, Categoria categoria, String ISBN, int stock_total) {
        super(id, titulo,  preco, categoria, stock_total);
        this.autor = autor;
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return  super.id + " - " + this.stock + " de " + this.stock_total + " - BOOK - " + super.titulo + " - " + super.preco + " - " + super.categoria + " - " + this.autor + " - " + this.ISBN;
    }

    @Override
    public int compareTo(Obra o) {
        if(this.getTitulo().compareTo(o.getTitulo()) != 0){
            return this.getTitulo().compareTo(o.getTitulo());
        }

        if(o instanceof Livro){
            if(this.autor.compareTo(((Livro)o).autor) != 0){
                return this.autor.compareTo(((Livro)o).getAutor());
            }
            return -1;
        }


        return 0;
    }

}
