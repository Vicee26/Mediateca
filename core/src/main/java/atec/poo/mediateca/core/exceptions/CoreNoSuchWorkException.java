package atec.poo.mediateca.core.exceptions;

public class CoreNoSuchWorkException extends Exception{
    private int id;

    public CoreNoSuchWorkException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
