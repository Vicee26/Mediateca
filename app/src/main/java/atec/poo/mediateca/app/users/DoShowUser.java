package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.CoreNoSuchUserException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme Enunciado
 * 4.2.2. Mostrar Utente.
 */
public class DoShowUser extends Comando<LibraryManager> {

    private final LerInteiro id_user;

    /**
     * @param receiver
     */
    public DoShowUser(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER);
        this.id_user = new LerInteiro(Message.requestUserId());
    }

    @Override
    public final void executar() throws DialogException {
        //a função mostra um user especificado pelo id
        ui.lerInput(this.id_user);
        try {
            ui.escreveLinha(this.getReceptor().mostraUser(this.id_user.getValor()));
        } catch (CoreNoSuchUserException e) {
            throw new NoSuchUserException(e.getId());
        }
    }

}
