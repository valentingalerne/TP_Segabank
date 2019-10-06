package bo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CompteSimple extends Compte implements Log {

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
            setSolde(getSolde() - montant);
        } else {
            System.out.println("Retrait impossible, découvert atteint");
        }
        addLog("retrait de " + montant + " euros sur le compte d'id " + getId());
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
        sb.append("}\n");
        return sb.toString();
    }

    public float getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(float decouvert) {
        this.decouvert = decouvert;
    }

}
