package atec.poo.mediateca.core;

public class DVD extends Obra{
    private String IGAC;
    private String realizador;

    public String getIGAC() {
        return IGAC;
    }

    public void setIGAC(String IGAC) {
        this.IGAC = IGAC;
    }

    public String getRealizador() {
        return realizador;
    }

    public void setRealizador(String realizador) {
        this.realizador = realizador;
    }

    public DVD(int id, String titulo, String realizador, int preco, Categoria categoria, String IGAC,  int stock_total) {
        super(id, titulo, preco, categoria, stock_total);
        this.IGAC = IGAC;
        this.realizador = realizador;
    }

    @Override
    public String toString() {
        return  super.id + " - " + this.stock + " de " + this.stock_total + " - DVD - " + super.titulo + " - " + super.preco + " - " + super.categoria + " - " + this.realizador + " - " + this.IGAC;
    }

    @Override
    public int compareTo(Obra o) {
        if(this.getTitulo().compareTo(o.getTitulo()) != 0){
            return this.getTitulo().compareTo(o.getTitulo());
        }

        if(o instanceof DVD){
            if(this.realizador.compareTo(((DVD)o).realizador) != 0){
                return this.realizador.compareTo(((DVD)o).getRealizador());
            }
            return -1;
        }


        return 0;
    }

}
