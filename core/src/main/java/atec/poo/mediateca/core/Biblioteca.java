package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Biblioteca {
    private int dia;
    private final HashMap<Integer, User> utentes;
    private final HashMap<Integer, Obra> obras;
    private final HashMap<Integer, Requisicao> requisicoes;
    private int nextUserId;
    private int nextWorkId;
    private int NextRequisicaoId;


    public Biblioteca(){
        this.dia = 0;
        this.utentes = new HashMap<>();
        this.obras = new HashMap<>();
        this.requisicoes = new HashMap<>();
        this.nextUserId = 1;
        this.nextWorkId = 1;
        this.NextRequisicaoId = 1;
    }

    //esta função lista todos os utentes existentes por ordem
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>(this.utentes.values());
        Collections.sort(users);
        return users;
    }

    //esta função lista todas as obras existentes por ordem
    public ArrayList<Obra> getObras() {
        ArrayList<Obra> obras = new ArrayList<>(this.obras.values());
        Collections.sort(obras);
        return obras;
    }

    //função para criar o user
    public int newUser(String nome, String email){
        //cria o user
        User u = new User(this.nextUserId, nome, email);

        //adiciona o user e o id ao hashmap
        this.utentes.put(this.nextUserId, u);

        //aumenta o id para o próximo user
        this.nextUserId ++;
        return u.getId();
    }

    public int newLivro(String titulo, String autor, int preco, Categoria categoria, String ISBN, int stock_total){
        //cria uma nova obra que seja um Livro
        Obra o = new Livro(this.nextWorkId, titulo, autor, preco, categoria, ISBN, stock_total);

        //adiciona a obra e o id ao hashmap
        this.obras.put(this.nextWorkId, o);

        //aumenta o id para a próxima obra
        this.nextWorkId ++;
        return o.getId();
    }

    public int newDVD(String titulo, String realizador, int preco, Categoria categoria, String IGAC,int stock_total){
        //cria uma nova obra que seja um DVD
        Obra o = new DVD(this.nextWorkId, titulo, realizador, preco, categoria, IGAC, stock_total);

        //adiciona a obra e o id ao hashmap
        this.obras.put(this.nextWorkId, o);

        //aumenta o id para a próxima obra
        this.nextWorkId ++;
        return o.getId();
    }

    private void calcula_estado(Requisicao r){
        User u = r.getU();

        /**
         * este if verifica se a entrega está em atraso,
         * se estiver e não for a primeira seguida que o utilizador então vai diminui um valor a um inteiro,
         * se este inteiro for menor ou igual a menos 3 o utilizador passa a ser faltoso
         * O segundo if verifica o contrário e aumenta o contador se este for maior que 5 passa a CUMPRIDOR
         * */

        if(r.getData_entrega() < this.dia && !r.isEntregue()){
            if(u.getEstado() <= -1){
                u.setEstado(u.getEstado()-1);
                if(u.getEstado() <= -3){
                    u.setAssiduidade(Assiduidade.FALTOSO);
                }
            }else{
                u.setEstado(-1);
                u.setAssiduidade(Assiduidade.NORMAL);
            }

        } else if(r.getData_entrega() > this.dia && !r.isEntregue()){
            if(u.getEstado() >=1){
                u.setEstado(u.getEstado() +1);
                if(u.getEstado() >= 5){
                    u.setAssiduidade(Assiduidade.CUMPRIDOR);
                }
            }else{
                u.setEstado(1);
                u.setAssiduidade(Assiduidade.NORMAL);
            }
        }
    }

    private void calcula_multa(){
        /**
         * esta função calcula a multa cada vez que é executada
         * */

        for(Requisicao r : this.requisicoes.values()){
            User u = r.getU();
            if(r.getData_entrega() < this.dia && !r.isEntregue()){
                u.setMulta(u.getMulta() - r.getMulta());
                r.setMulta(5 * (this.dia-r.getData_entrega()));
                u.setMulta(r.getMulta() + u.getMulta());
            }
        }
    }

    private void calcula_comportamento(){
        /**
         * esta função verifica se o utilizador tem alguma entrega em atraso se tiver passa a estar suspenso
         * */

        for(Requisicao r : this.requisicoes.values()){
            User u = r.getU();

            if(r.getData_entrega() < this.dia && !r.isEntregue()){
                u.setComportamento(Comportamento.SUSPENSO);
            }
        }
    }


    public void incrementadia(int n){
        /**
         * esta função aumenta o dia e executa as funções de calcular o comportamento e a multa
         * */
        this.dia += n;
        calcula_comportamento();
        calcula_multa();
    }

    public int mostradia(){
        //apresenta o inteiro referente ao dia atual no programa
        return this.dia;
    }

    public String mostraUser(int id) throws CoreNoSuchUserException{
        //apresenta o user referente a um id, se este não existir lança uma exceção
        if(this.utentes.containsKey(id)){
            return this.utentes.get(id).toString();
        }
        throw new CoreNoSuchUserException(id);
    }

    public String mostraWork(int id) throws CoreNoSuchUserException{
        //apresenta a obra referente a um id, se esta não existir lança uma exceção
        if(this.obras.containsKey(id)){
            return this.obras.get(id).toString();
        }
        throw new CoreNoSuchUserException(id);
    }

    private int calcular_entrega(User u, Obra o){
        //esta função calcula a entrega tendo em conta o stock da obra e a assiduidade do user
        int data_entrega = 0;
        if(o.stock <= 1){
            if(u.getAssiduidade() == Assiduidade.NORMAL){
                data_entrega = 3;
            } else if(u.getAssiduidade() == Assiduidade.FALTOSO){
                data_entrega = 2;
            }else if(u.getAssiduidade() == Assiduidade.CUMPRIDOR){
                data_entrega = 8;
            }
        }

        if(o.stock > 1 && o.stock <=5){
            if(u.getAssiduidade() == Assiduidade.NORMAL){
                data_entrega = 8;
            } else if(u.getAssiduidade() == Assiduidade.CUMPRIDOR){
                data_entrega = 15;
            }else if(u.getAssiduidade() == Assiduidade.FALTOSO){
                data_entrega = 2;
            }
        }

        if(o.stock > 5){
            if(u.getAssiduidade() == Assiduidade.NORMAL){
                data_entrega = 15;
            } else if(u.getAssiduidade() == Assiduidade.CUMPRIDOR){
                data_entrega = 30;
            }else if(u.getAssiduidade() == Assiduidade.FALTOSO){
                data_entrega = 2;
            }
        }

        return data_entrega + this.dia;
    }

    private void  verificar_user(int user) throws CoreNoSuchUserException{
        //verifica no hashmap dos utentes se existe alguma com este id
        if(!utentes.containsKey(user)){
            throw new CoreNoSuchUserException(user);
        }
    }

    private void  verificar_obra(int obra) throws CoreNoSuchWorkException{
        //verifica no hashmap das obras se existe alguma com este id
        if(!obras.containsKey(obra)){
            throw new CoreNoSuchWorkException(obra);
        }
    }

    public int newRequisicao(int user, int obra) throws CoreNoSuchUserException, CoreNoSuchWorkException, CoreRuleFailedException {
        //Verifica se o user existe
        verificar_user(user);

        //Verifica se a obra existe
        verificar_obra(obra);

        //Vai buscar o utilizador através do id
        User u = this.utentes.get(user);

        //Vai buscar a obra através do id
        Obra o = this.obras.get(obra);

        //Executa as Regras
        Rules.executarRegras(u, o, this.requisicoes);

        //calcula os dias que o utilizador tem para entregar a obra
        int data_entrega = this.calcular_entrega(u, o);

        //Cria a requisição
        Requisicao r = new Requisicao(this.NextRequisicaoId, u, o,this.dia, data_entrega);

        //adiciona ao hashmap o id e a requisição
        this.requisicoes.put(this.NextRequisicaoId, r);

        //decrementa o stock
        o.stock --;

        //incrementa o id
        this.NextRequisicaoId++;

        //retorna a data
        return data_entrega;

    }

    public void devolverRequisicao(int user, int obra) throws CoreNoSuchUserException, CoreNoSuchWorkException,
            CoreWorkNotBorrowedByUserException{
        //Verifica se o user existe
        verificar_user(user);

        //Verifica se a obra existe
        verificar_obra(obra);

        //Vai buscar o utilizador através do id
        User u = this.utentes.get(user);

        //Vai buscar a obra através do id
        Obra o = this.obras.get(obra);

        //verifica se a requisição foi requisitada pelo user especifico
        boolean flag_entregue = false;
        for(Requisicao requisicao : this.requisicoes.values()) {
            if(!requisicao.isEntregue()){
                if(requisicao.getO().getId() == o.getId() && requisicao.getU().getId() == u.getId()){
                    flag_entregue = true;
                    //calcula o comportamento do user
                    calcula_estado(requisicao);
                }
            }
        }
        //verifica se não existe nenhuma entrega da obra indicada e do user indicado, se realmente não existir lança a exceção
        if(!flag_entregue){
            throw new CoreWorkNotBorrowedByUserException(o.getId(), u.getId());
        }


        //devolve a entrega
        for(Requisicao requisicao : requisicoes.values()) {
            if(requisicao.getO().getId() == o.getId() && requisicao.getU().getId() == u.getId()){
                requisicao.entregue = true;
            }
        }


        //aumenta o stock existente
        o.stock++;

    }

    public void payFine(int user) throws CoreUserIsActiveException{
        User u = this.utentes.get(user);

        //se o comportamento for ativo ele lança a exceção, porque o utilizador tem de estar suspenso para ter uma multa
        if(u.getComportamento() == Comportamento.ATIVO){
            throw new CoreUserIsActiveException(user);
        }

        //muda a multa para 0, ou seja, foi paga
        u.setMulta(0);
        //muda o comportamento para ativo, mas se este tiver uma obra por entregar em atraso continua suspenso
        u.setComportamento(Comportamento.ATIVO);
        for(Requisicao req : this.requisicoes.values()) {
            if(!(req.isEntregue()) && req.getData_entrega() < this.dia && req.getU().getId() == u.getId()){
                u.setComportamento(Comportamento.SUSPENSO);
            }
        }
    }


    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * //@throws BadEntrySpecificationException
     * //@throws IOException
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Scanner s =new Scanner(new File(filename));
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] elementos= line.split(":",0);
            switch(elementos[0]){
                case "USER":
                    this.newUser(elementos[1],elementos[2]);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                case "BOOK":
                    int preco_b = Integer.parseInt(elementos[3]);
                    Categoria categoria_b = Categoria.valueOf(elementos[4]);
                    int stock_b = Integer.parseInt(elementos[6]);
                    this.newLivro(elementos[1],elementos[2],preco_b,categoria_b,elementos[5],stock_b);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                case "DVD":
                    int preco_d = Integer.parseInt(elementos[3]);
                    Categoria categoria_d = Categoria.valueOf(elementos[4]);
                    int stock_d = Integer.parseInt(elementos[6]);
                    this.newDVD(elementos[1],elementos[2],preco_d,categoria_d,elementos[5],stock_d);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                default:
                    throw new BadEntrySpecificationException("Unknow type of category");
            }
        }
        s.close();
    }
}
