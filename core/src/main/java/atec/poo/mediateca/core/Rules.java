package atec.poo.mediateca.core;


import atec.poo.mediateca.core.exceptions.CoreRuleFailedException;

import java.util.HashMap;
import java.lang.Throwable;

public class Rules {

    private static void Regra1(User user, Obra obra, HashMap<Integer, Requisicao> requisicoes) throws CoreRuleFailedException {

        for(Requisicao requisicao : requisicoes.values()) {
            if(requisicao.getO().getId() == obra.getId() && requisicao.getU().getId() == user.getId() && !(requisicao.isEntregue())){
                throw new CoreRuleFailedException(user.getId(), obra.getId(), 1);
            }
        }
    }


    private static void Regra2(User user, Obra obra) throws CoreRuleFailedException {
        if(user.getComportamento() == Comportamento.SUSPENSO){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 2);
        }
    }

    private static void Regra3(User user, Obra obra) throws CoreRuleFailedException {
        if(obra.getStock() <= 0){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 3);
        }
    }

    private static void Regra4(User user, Obra obra, HashMap<Integer, Requisicao> requisicoes) throws CoreRuleFailedException {
        int flag = 0;
        for(Requisicao requisicao : requisicoes.values()) {
            if(requisicao.getU().getId() == user.getId() && !(requisicao.isEntregue()) ){
                flag++;
            }
        }

        if(user.getAssiduidade() == Assiduidade.CUMPRIDOR && flag >=5 ){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 4);
        }
        if(user.getAssiduidade() == Assiduidade.NORMAL && flag >=3 ){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 4);
        }
        if(user.getAssiduidade() == Assiduidade.FALTOSO && flag >=1 ){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 4);
        }
    }

    private static void Regra5(User user, Obra obra) throws CoreRuleFailedException {
        if(obra.getCategoria() == Categoria.REFERENCE){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 5);
        }

    }

    private static void Regra6(User user, Obra obra) throws CoreRuleFailedException {
        if(!(user.getAssiduidade() == Assiduidade.CUMPRIDOR) && obra.getPreco() > 25.00){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 6);
        }

    }

    public static void executarRegras(User user, Obra obra, HashMap<Integer, Requisicao> requisicoes) throws CoreRuleFailedException {
        Regra1(user, obra, requisicoes);
        Regra2(user, obra);
        Regra3(user, obra);
        Regra4(user, obra, requisicoes);
        Regra5(user, obra);
        Regra6(user, obra);
    }

}
