package atec.poo.mediateca.core.exceptions;

public class CoreRuleFailedException extends Exception {
    private int idUser;
    private int idWork;
    private int ruleIndex;

    public int getIdUser() {
        return idUser;
    }


    public int getIdWork() {
        return idWork;
    }


    public int getRuleIndex() {
        return ruleIndex;
    }


    public CoreRuleFailedException(int idUser, int idWork, int ruleIndex) {
        this.idUser = idUser;
        this.idWork = idWork;
        this.ruleIndex = ruleIndex;
    }
}
