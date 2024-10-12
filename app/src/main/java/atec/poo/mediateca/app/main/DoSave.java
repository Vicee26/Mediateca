package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.LibraryManager;

import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;


/**
 * Conforme enunciado
 * 4.1.2. Guardar o estado da aplicação
 */
public class DoSave extends Comando<LibraryManager> {



  /**
   * @param receiver
   */
  public DoSave(LibraryManager receiver) {
    super(receiver, Label.SAVE);

  }

  @Override
  public final void executar() throws DialogException {
    ui.escreveLinha("---> Salvar Aplicação num ficheiro - Seriazable");// A apagar. Só indicativo

  }
}
