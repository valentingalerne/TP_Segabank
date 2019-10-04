package bo;

import java.util.ArrayList;

public class CompteSimple extends Compte {

    private float decouvert;

    public CompteSimple(int id, float solde, float decouvert) {
        super(id, solde);
        this.decouvert = decouvert;
    }

    @Override
    public void versement(int montant) {

    }

    @Override
    public void retrait(int montant) {

    }

    @Override
    public void log(ArrayList<String> logs) {

    }
}
