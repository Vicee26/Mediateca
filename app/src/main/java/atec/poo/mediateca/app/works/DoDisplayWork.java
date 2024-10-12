package atec.poo.mediateca.app.works;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.CoreNoSuchUserException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;


/**
 * 4.3.1. Mostrar Obra.
 */
public class DoDisplayWork extends Comando<LibraryManager> {

    private final LerInteiro id_work;

    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORK);
        this.id_work = new LerInteiro(Message.requestWorkId());


    }


    @Override
    public final void executar() throws DialogException {
        //a função mostra uma obra especificada pelo id
        ui.lerInput(this.id_work);
        try{
            ui.escreveLinha(this.getReceptor().mostraWork(this.id_work.getValor()));
        }catch (CoreNoSuchUserException e){
            throw new NoSuchWorkException(e.getId());
        }
    }

}
