package bo;

import java.util.ArrayList;

public class ComptePayant extends Compte {

    public static final float FRAIS_TRANSACTION = 0.05f;

    public ComptePayant(int id, float solde) {
        super(id, solde);
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
