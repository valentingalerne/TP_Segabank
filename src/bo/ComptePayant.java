package bo;

import java.util.ArrayList;

public class ComptePayant extends Compte {

    public static final float FRAIS_TRANSACTION = 0.05f;

    public ComptePayant(){

    }

    public ComptePayant(int id, float solde) {
        super(id, solde);
    }

    @Override
    public void versement(float montant) {
        setSolde(getSolde() + (montant - (montant * FRAIS_TRANSACTION)));
    }

    @Override
    public void retrait(float montant) {
        float montantAvecFrais = (montant + (montant * FRAIS_TRANSACTION));
        if (getSolde() - montantAvecFrais >= 0) {
            setSolde(getSolde() - montantAvecFrais);
        } else {
            System.out.println("Retrait impossible, vous n'avez pas assez d'argent");
        }

    }

    @Override
    public void log(ArrayList<String> logs) {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComptePayant{");
        sb.append("id=").append(getId());
        sb.append(", solde=").append(getSolde());
        sb.append("}\n");
        return sb.toString();
    }
}
