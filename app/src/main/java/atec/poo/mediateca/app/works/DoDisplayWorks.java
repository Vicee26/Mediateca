package atec.poo.mediateca.app.works;


import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.mediateca.core.User;

import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;

/**
 * 4.3.2. Listar Obras
 */
public class DoDisplayWorks extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoDisplayWorks(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORKS);
    }

    @Override
    public final void executar() throws DialogException {
        //a função mostra todas as obras que estão registadas
        ArrayList<Obra> obras = this.getReceptor().getObras();
        for (Obra obra : obras) {
            ui.escreveLinha(obra.toString());
        }

    }
}