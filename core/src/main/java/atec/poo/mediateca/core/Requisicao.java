package atec.poo.mediateca.core;

public class Requisicao {
    private int id;
    private User u;
    private Obra o;
    private int data_levantamento;
    private int data_entrega;
    public boolean entregue;
    private double multa;

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Obra getO() {
        return o;
    }

    public void setO(Obra o) {
        this.o = o;
    }

    public int getData_levantamento() {
        return data_levantamento;
    }

    public void setData_levantamento(int data_levantamento) {
        this.data_levantamento = data_levantamento;
    }

    public int getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(int data_entrega) {
        this.data_entrega = data_entrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Requisicao(int id, User u, Obra o, int data_levantamento, int data_entrega) {
        this.id = id;
        this.u = u;
        this.o = o;
        this.data_levantamento = data_levantamento;
        this.data_entrega = data_entrega;
        this.entregue = false;
        //this.m = m;
    }
}
