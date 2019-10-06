package testBo;

import bo.CompteSimple;
import bo.Compte;

public class TestCompteSimple {

    public static void main(String[] args) {

        CompteSimple compteSimple;

        System.out.println("==================================================");
        System.out.printf("%nTest unitaire - création d'un compte simple%n");
        compteSimple = new CompteSimple(1250.47f, 400f, 2);
        System.out.println(compteSimple.toString());
        System.out.println("==================================================");

        System.out.printf("%nTest unitaire - versement de 600.00€ sur un compte simple%n");
        System.out.println("Solde avant versement : " + compteSimple.getSolde() + "€");
        compteSimple.versement(600f);
        System.out.println("Solde après versement : " + compteSimple.getSolde() + "€");
        System.out.println("==================================================");

        System.out.printf("%nTest unitaire - retrait de 480.47€ sur un compte simple%n");
        System.out.println("Solde avant retrait : " + compteSimple.getSolde() + "€");
        compteSimple.retrait(480.47f);
        System.out.println("Solde après retrait : " + compteSimple.getSolde() + "€");
        System.out.println("==================================================");

        System.out.printf("%nTest unitaire - retrait de 1600€ (inférieur au découvert)%n");
        System.out.println("Solde avant retrait découvert : " + compteSimple.getSolde() + "€");
        compteSimple.retrait(1600.00f);
        System.out.println("Solde après retrait : " + compteSimple.getSolde() + "€");
        System.out.println("==================================================");

        System.out.printf("%nTest unitaire - retrait de 270€ (supérieur au découvert)%n");
        System.out.println("Solde avant retrait découvert : " + compteSimple.getSolde() + "€");
        compteSimple.retrait(270.00f);
        System.out.println("Solde après retrait découvert : " + compteSimple.getSolde() + "€");
        System.out.println("==================================================");


    }

}
