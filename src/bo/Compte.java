package bo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Compte {

    public static final String FILE_LOG = "./resources/log.csv";
    private int id;
    private float solde;
    private int type;
    private int idAgence;

    public Compte() {

    }

    public Compte(float solde, int idAgence) {
        this.solde = solde;
        this.idAgence = idAgence;
    }

    public void versement(float montant) {
        if (montant > 0) {
            solde += montant;
            addLog("versement de " + montant + " euros sur le compte d'id " + getId());
        } else {
            System.out.println("Un virement ne peux pas être négatif !");
        }
    }

    public void retrait(float montant) {
        if (montant > 0) {
            if (getSolde() - montant >= 0) {
                solde -= montant;
                addLog("retrait de " + montant + " euros sur le compte d'id " + getId());
            } else {
                System.out.println("Retrait impossible, vous n'avez pas assez d'argent");
            }
        } else {
            System.out.println("Un retrait ne peux pas être négatif !");
        }
    }

    public abstract void log(ArrayList<String> logs);

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compte{");
        sb.append("id=").append(id);
        sb.append(", solde=").append(solde);
        sb.append("}\n");
        return sb.toString();
    }

    public void addLog(String str) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try (FileWriter writer = new FileWriter(FILE_LOG, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(dtf.format(now));
            bw.write(" = ");
            bw.write(str);
            bw.newLine();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }
}
