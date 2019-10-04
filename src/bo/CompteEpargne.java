package bo;

import java.util.ArrayList;

public class CompteEpargne extends Compte {

    private float tauxInteret;

    public CompteEpargne(int id, float solde, float tauxInteret) {
        super(id, solde);
        this.tauxInteret = tauxInteret;
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
