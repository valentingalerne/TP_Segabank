package bo;

import java.util.ArrayList;

public abstract class Compte {

    protected int id;
    protected float solde;

    public Compte(int id, float solde) {
    }

    public abstract void versement(int montant);
    public abstract void retrait(int montant);
    public abstract void log(ArrayList<String> logs);

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compte{");
        sb.append("id=").append(id);
        sb.append(", solde=").append(solde);
        sb.append('}');
        return sb.toString();
    }
}
