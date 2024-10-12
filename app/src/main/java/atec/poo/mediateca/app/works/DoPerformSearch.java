package atec.poo.mediateca.app.works;


import atec.poo.mediateca.core.DVD;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Livro;
import atec.poo.mediateca.core.Obra;
import atec.poo.ui.Comando;
import atec.poo.ui.LerString;

import java.util.ArrayList;

/**
 * Conforme Enunciado
 * 4.3.3. Pesquisar Obras
 */
public class DoPerformSearch extends Comando<LibraryManager> {
  private final LerString pesquisa;

  /**
   * @param receiver
   */
  public DoPerformSearch(LibraryManager receiver) {
    super(receiver, Label.PERFORM_SEARCH);
    this.pesquisa = new LerString(Message.requestSearchTerm(), null);
  }

  @Override
  public final void executar() {
    //faz pesquisa de uma obra pelo titulo ou autor, com o termo inserido

    ui.lerInput(this.pesquisa);
    String pesquisaTermo = this.pesquisa.getValor();
    ArrayList<Obra> obras = this.getReceptor().getObras();
    ArrayList<Obra> matchWorks = new ArrayList<>();

    for (Obra obra : obras) {
      if(obra instanceof DVD){
        if(obra.getTitulo().toLowerCase().contains(pesquisaTermo) || ((DVD) obra).getRealizador().toLowerCase().contains(pesquisaTermo)){
          matchWorks.add(obra);
        }
      }
      if(obra instanceof Livro){
        if(obra.getTitulo().toLowerCase().contains(pesquisaTermo) || ((Livro) obra).getAutor().toLowerCase().contains(pesquisaTermo)){
          matchWorks.add(obra);
        }
      }
    }

    if (matchWorks.isEmpty()) {
      ui.escreveLinha("Não foram encontradas obras com o termo "+ pesquisaTermo+".");
    }
    else {
      for (Obra obra : matchWorks) {
        ui.escreveLinha(obra.toString());
      }
    }

  }

}
