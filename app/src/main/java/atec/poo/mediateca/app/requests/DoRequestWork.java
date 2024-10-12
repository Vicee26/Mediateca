package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.RuleFailedException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.CoreNoSuchUserException;
import atec.poo.mediateca.core.exceptions.CoreNoSuchWorkException;
import atec.poo.mediateca.core.exceptions.CoreRuleFailedException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.LerOpcao;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;


/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {

    /**
     * //@param receiver
     */
    private final LerInteiro user;
    private final LerInteiro obra;

    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.user = new LerInteiro(Message.requestUserId());
        this.obra = new LerInteiro(Message.requestWorkId());
    }

    @Override
    public final void executar() throws DialogException {
        //lê os inputs
        ui.lerInput(this.user);
        ui.lerInput(this.obra);

        /**
         * a função tenta criar uma nova requisição e devolver a data em que tem de ser entregue,
         * senão conseguir lança uma das exceções conforme o problema que apareça
         * */
        try {
            int dt = this.getReceptor().newRequisicao(this.user.getValor(), this.obra.getValor());
            ui.escreveLinha(Message.workReturnDay(this.obra.getValor(), dt));
        } catch (CoreNoSuchUserException u) {
            throw new NoSuchUserException(u.getId());
        } catch (CoreNoSuchWorkException o) {
            throw new NoSuchWorkException(o.getId());
        } catch (CoreRuleFailedException r) {
            throw new RuleFailedException(r.getIdUser(), r.getIdWork(), r.getRuleIndex());
        }




        

    }
}
