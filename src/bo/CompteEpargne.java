package bo;

import java.util.ArrayList;

public class CompteEpargne extends Compte {

    private float tauxInteret;

    public CompteEpargne(){

    }

    public CompteEpargne(float solde, float tauxInteret) {
        super(solde);
        this.tauxInteret = tauxInteret;
        setType(3);
    }

    public void calculInteret() {
        float interet = getSolde() + (getSolde() * (tauxInteret / 100));
        setSolde(interet);
        addLog("interets de" + interet + " euros sur le compte d'id " + getId());
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
        sb.append("}\n");
        return sb.toString();
    }

    public float getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
