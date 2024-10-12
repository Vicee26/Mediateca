package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LibraryManager{

    private Biblioteca _biblioteca;

    public LibraryManager() {
        this._biblioteca = new Biblioteca();
    }


    public String mostraUser(int id) throws CoreNoSuchUserException {
        return this._biblioteca.mostraUser(id);
    }

    public String mostraWork(int id) throws CoreNoSuchUserException {
        return this._biblioteca.mostraWork(id);
    }

    public int newUser(String nome, String email){
        return this._biblioteca.newUser(nome, email);
    }

    public int newLivro(String titulo,  String autor, int preco, Categoria categoria,String ISBN, int stock){
        return this._biblioteca.newLivro(titulo, autor, preco, categoria, ISBN,  stock);
    }

    public int newDVD(String titulo, String realizador, int preco, Categoria categoria, String IGAC, int stock){
        return this._biblioteca.newDVD(titulo,  realizador, preco, categoria, IGAC, stock);
    }


    public ArrayList<User> getUsers() {
        return this._biblioteca.getUsers();
    }

    public ArrayList<Obra> getObras() {
        return this._biblioteca.getObras();
    }

    public int newRequisicao(int user, int obra) throws CoreNoSuchUserException, CoreNoSuchWorkException, CoreRuleFailedException {
       return this._biblioteca.newRequisicao(user, obra);
    }

    public void devolverRequisicao(int user, int obra) throws CoreNoSuchUserException, CoreNoSuchWorkException,
            CoreWorkNotBorrowedByUserException{
        this._biblioteca.devolverRequisicao(user,obra);
    }

    public void payFine(int user) throws CoreUserIsActiveException{
        this._biblioteca.payFine(user);
    }

    /**
     * Recebe ficheiro de entrada
     * @param //datafile
     * @throws ImportFileException
     */



    public void incrementadias(int dias){
        this._biblioteca.incrementadia(dias);
    }

    public int mostraData(){
        return this._biblioteca.mostradia();
    }

    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }

}
