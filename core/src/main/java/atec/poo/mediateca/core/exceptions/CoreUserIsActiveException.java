package atec.poo.mediateca.core.exceptions;

public class CoreUserIsActiveException extends Exception {
    private final int id;

    public CoreUserIsActiveException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
