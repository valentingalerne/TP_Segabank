package bo;

import java.util.ArrayList;

public class ComptePayant extends Compte {

    public static final float FRAIS_TRANSACTION = 0.05f;

    public ComptePayant(){

    }

    public ComptePayant(float solde, int idAgence) {
        super(solde, idAgence);
        setType(3);
    }

    @Override
    public void versement(float montant) {
        if (montant > 0) {
            setSolde(getSolde() + (montant - (montant * FRAIS_TRANSACTION)));
            addLog("versement de " + montant + " euros sur le compte d'id " + getId());
        } else {
            System.out.println("Un virement ne peux pas être négatif !");
        }
    }

    @Override
    public void retrait(float montant) {
        if (montant > 0) {
            float montantAvecFrais = (montant + (montant * FRAIS_TRANSACTION));
            if (getSolde() - montantAvecFrais >= 0) {
                setSolde(getSolde() - montantAvecFrais);
            } else {
                System.out.println("Retrait impossible, vous n'avez pas assez d'argent");
            }
            addLog("retrait de " + montant + " euros sur le compte d'id " + getId());
        } else {
            System.out.println("Un virement ne peux pas être négatif !");
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
