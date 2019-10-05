package bo;

import java.util.ArrayList;

public class CompteEpargne extends Compte {

    private float tauxInteret;

    public CompteEpargne(){

    }

    public CompteEpargne(int id, float solde, float tauxInteret) {
        super(id, solde);
        this.tauxInteret = tauxInteret;
    }

    public void calculInteret() {
        setSolde(getSolde() + (getSolde() * (tauxInteret / 100)));
    }

    @Override
    public void log(ArrayList<String> logs) {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompteEpargne{");
        sb.append("id=").append(getId());
        sb.append(", solde=").append(getSolde());
        sb.append(", tauxInteret=").append(tauxInteret);
        sb.append('}');
        return sb.toString();
    }

    public float getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
