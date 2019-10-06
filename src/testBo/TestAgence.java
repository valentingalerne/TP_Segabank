package testBo;

import bo.*;

import java.util.ArrayList;
import java.util.List;

public class TestAgence {

    public static void main(String[] args) {

        Agence agence1;
        List<Compte> comptes = new ArrayList<>();
        CompteEpargne compteEpargne = new CompteEpargne(4700f, 3, 1);
        CompteSimple compteSimple = new CompteSimple(1890f, 450f, 1);
        ComptePayant comptePayant = new ComptePayant(3250f, 2);

        comptes.add(compteEpargne);
        comptes.add(compteSimple);
        comptes.add(comptePayant);


        System.out.println("==================================================");
        System.out.printf("%nTest unitaire - création d'une agence%n");
        agence1 = new Agence(1, "123A", "3 rue des chemins", comptes);
        System.out.println(agence1.toString());
        System.out.println("==================================================");

    }
}
