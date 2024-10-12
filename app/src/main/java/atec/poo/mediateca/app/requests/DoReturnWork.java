package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;

import atec.poo.mediateca.app.exceptions.UserIsActiveException;
import atec.poo.mediateca.app.exceptions.WorkNotBorrowedByUserException;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.*;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;

import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Comando<LibraryManager> {

    private final LerInteiro user;
    private final LerInteiro obra;
    private final LerString a;

    /**
     * //@param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(receiver, Label.RETURN_WORK);
        this.user = new LerInteiro(Message.requestUserId());
        this.obra = new LerInteiro(Message.requestWorkId());
        this.a = new LerString(Message.requestFinePaymentChoice(), null);
    }

    @Override
    public final void executar() throws DialogException {
        //lê os inputs
        ui.lerInput(this.user);
        ui.lerInput(this.obra);

        /**
         * a função tenta devolver um requisição se o programa não encontrar nenhum problema e lançar uma exceção,
         * então o programa devolve a obra e pergunta ao user se quer pagar a multa senão tiver multa nenhuma o programa dá erro
         * e lança uma exceção que diz que o user está ativo e não suspenso, ou seja, não tem multa
         * */
        try {
            this.getReceptor().devolverRequisicao(this.user.getValor(), this.obra.getValor());
            ui.lerInput(this.a);
            if(this.a.getValor().contentEquals("s") || this.a.getValor().contentEquals("S")){
                this.getReceptor().payFine(this.user.getValor());
            }
        } catch (CoreNoSuchUserException u) {
            throw new NoSuchUserException(u.getId());
        } catch (CoreNoSuchWorkException o) {
            throw new NoSuchWorkException(o.getId());
        } catch (CoreWorkNotBorrowedByUserException w){
            throw new WorkNotBorrowedByUserException(w.getId_user(), w.getId_obra());
        } catch (CoreUserIsActiveException e) {
            throw new UserIsActiveException(e.getId());
        }

    }

}
