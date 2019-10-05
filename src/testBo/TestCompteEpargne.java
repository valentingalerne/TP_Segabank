package testBo;

import bo.CompteEpargne;

public class TestCompteEpargne {

    public static void main(String[] args) {

        CompteEpargne compteEpargne;

        System.out.println("==================================================");
        System.out.printf("%nTest unitaire - création d'un compte épargne%n");
        compteEpargne = new CompteEpargne(3, 1500.00f, 10);
        System.out.println(compteEpargne.toString());
        System.out.println("==================================================");

        System.out.printf("%nTest unitaire - versement de 500.00€ sur un compte épargne%n");
        System.out.println("Solde avant versement : " + compteEpargne.getSolde() + "€");
        compteEpargne.versement(500f);
        System.out.println("Solde après versement : " + compteEpargne.getSolde() + "€");
        System.out.println("==================================================");
//
        System.out.printf("%nTest unitaire - retrait de 975€ sur un compte épargne%n");
        System.out.println("Solde avant retrait : " + compteEpargne.getSolde() + "€");
        compteEpargne.retrait(975.00f);
        System.out.println("Solde après retrait : " + compteEpargne.getSolde() + "€");
        System.out.println("==================================================");

        System.out.printf("%nTest unitaire - retrait supérieur au solde sur un compte épargne%n");
        System.out.println("Solde avant retrait : " + compteEpargne.getSolde() + "€");
        compteEpargne.retrait(2000.00f);
        System.out.println("Solde après retrait : " + compteEpargne.getSolde() + "€");
        System.out.println("==================================================");

        System.out.printf("%nTest unitaire - calcul intérêt%n");
        System.out.println("Intérêt de " + compteEpargne.getTauxInteret() + "%");
        System.out.println("Solde avant calcul intérêt : " + compteEpargne.getSolde() + "€");
        compteEpargne.calculInteret();
        System.out.println("Solde après calcul intérêt : " + compteEpargne.getSolde() + "€");
        System.out.println("==================================================");


    }
}
