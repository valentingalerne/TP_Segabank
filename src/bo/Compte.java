package bo;

import java.util.ArrayList;

public abstract class Compte {

    protected int id;
    protected float solde;

    public Compte(){

    }

    public Compte(int id, float solde) {
        this.id = id;
        this.solde = solde;
    }

    public void versement(float montant) {
        solde += montant;
    }
    public void retrait(float montant) {
        if (getSolde() - montant >= 0) {
            solde -= montant;
        } else {
            System.out.println("Retrait impossible, vous n'avez pas assez d'argent");
        }
    }
    public abstract void log(ArrayList<String> logs);

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compte{");
        sb.append("id=").append(id);
        sb.append(", solde=").append(solde);
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }
}
