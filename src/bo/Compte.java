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

public abstract class Compte implements Log {

    private int id;
    private float solde;
    private int type;
    private Agence agence;

    public Compte() {

    }

    public Compte(float solde) {
        this.solde = solde;
    }

    public void versement(float montant) {
        solde += montant;
        addLog("versement de " + montant + " euros sur le compte d'id " + getId());
    }

    public void retrait(float montant) {
        if (getSolde() - montant >= 0) {
            solde -= montant;
        } else {
            System.out.println("Retrait impossible, vous n'avez pas assez d'argent");
        }
        addLog("retrait de " + montant + " euros sur le compte d'id " + getId());
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

    @Override
    public void addLog(String str) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println(dtf.format(now));

        try (FileWriter writer = new FileWriter(FILE_LOG, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(dtf.format(now));
            bw.write(" = ");
            bw.write(str);
            bw.newLine();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

//        Path outPath = Paths.get(FILE_LOG);
//        try (BufferedReader br = new BufferedReader(Files.newBufferedReader(outPath));
//             BufferedWriter bw = new BufferedWriter(Files.newBufferedWriter(outPath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                bw.write(str);
//                System.out.println(str);
//                bw.newLine();
//            }
//            bw.write(str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public int getIdAgence(){
        return agence.getId();
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

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
}
