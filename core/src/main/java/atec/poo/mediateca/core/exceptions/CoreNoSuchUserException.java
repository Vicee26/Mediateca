package atec.poo.mediateca.core.exceptions;

public class CoreNoSuchUserException extends Exception {
    private int id;

    public CoreNoSuchUserException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
