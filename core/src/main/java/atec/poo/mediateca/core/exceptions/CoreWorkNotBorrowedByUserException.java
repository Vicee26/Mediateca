package atec.poo.mediateca.core.exceptions;

public class CoreWorkNotBorrowedByUserException extends Exception {
    private final int id_user;
    private final int id_obra;

    public CoreWorkNotBorrowedByUserException(int id_user, int id_obra) {
        this.id_user = id_user;
        this.id_obra = id_obra;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_obra() {
        return id_obra;
    }
}
