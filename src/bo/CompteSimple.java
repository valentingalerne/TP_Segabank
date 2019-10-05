package bo;

import java.util.ArrayList;

public class CompteSimple extends Compte {

    private float decouvert;

    public CompteSimple(){

    }

    public CompteSimple(int id, float solde, float decouvert) {
        super(id, solde);
        this.decouvert = decouvert;
    }

    @Override
    public void retrait(float montant) {
        if (getSolde() - montant > (-decouvert)) {
            solde -= montant;
        } else {
            System.out.println("Retrait impossible, découvert atteint");
        }
    }

    @Override
    public void log(ArrayList<String> logs) {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompteSimple{");
        sb.append("id=").append(getId());
        sb.append(", solde=").append(getSolde());
        sb.append(", decouvert=").append(decouvert);
        sb.append('}');
        return sb.toString();
    }

    public float getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(float decouvert) {
        this.decouvert = decouvert;
    }
}
