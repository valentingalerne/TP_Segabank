package bo;

import java.util.ArrayList;

public class CompteSimple extends Compte {

    private float decouvert;

    public CompteSimple(int id, float solde, float decouvert) {
        super(id, solde);
        this.decouvert = decouvert;
    }

    @Override
    public void retrait(int montant) {
        if (getSolde() - montant > decouvert) {
            super.solde -= montant;
        }
    }

    @Override
    public void log(ArrayList<String> logs) {

    }

    public float getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(float decouvert) {
        this.decouvert = decouvert;
    }
}
