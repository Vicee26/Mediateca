package atec.poo.mediateca.core;


public class User implements Comparable<User> {
    private int id;
    private String name;
    private String email;
    private double multa;
    private Comportamento comportamento;
    private Assiduidade assiduidade;
    private int estado;

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public Assiduidade getAssiduidade() {
        return assiduidade;
    }

    public void setAssiduidade(Assiduidade assiduidade) {
        this.assiduidade = assiduidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }



    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.multa = 0.0;
        this.comportamento = Comportamento.ATIVO;
        this.assiduidade = Assiduidade.NORMAL;
    }

    @Override
    public String toString(){
        if(this.comportamento == Comportamento.ATIVO){
            return this.id + " - " + this.name + " - " + this.email + " - " + this.assiduidade + " - " + this.comportamento;
        }
        else{
            return this.id + " - " + this.name + " - " + this.email + " - " + this.assiduidade + " - " + this.comportamento + " - EUR " + this.multa;
        }

    }

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.getName());
    }
}
