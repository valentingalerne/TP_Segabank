import bo.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static Scanner sc = new Scanner(System.in);
    private static List<Compte> comptes = new ArrayList<>();

    public static void main(String[] args) {
        dspMainMenu();
    }

    public static void dspMainMenu() {

        int response;
        boolean boolMainMenu = true;
        do {
            if (!boolMainMenu) {
                System.out.println("Mauvais choix, merci de recommencer");
            }
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                    Accueil - SegaBank                |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|1 - Création d'un compte                              |");
            System.out.println("|2 - Suppression d'un compte                           |");
            System.out.println("|3 - Selectionnez un compte (pour retrait ou virement) |");
            System.out.println("|4 - Afficher la liste des comptes                     |");
            System.out.println("|5 - Afficher les comptes d'une agence                 |");
            System.out.println("|6 - Afficher la liste des agences                     |");
            System.out.println("|7 - Quitter                                           |");
            System.out.println("+------------------------------------------------------+");
            System.out.print("Votre choix : ");
            try {
                response = sc.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
            } finally {
                sc.nextLine();
            }

        } while (response < 1 || response > 7);

        switch (response) {
            case 1:
                createCompte();
                dspMainMenu();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                listComptes();
                dspMainMenu();
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    private static void createCompte() {
        int response;
        boolean boolMainMenu = true;

        int id;
        float solde;
        float decouvert;
        float interet;

        do {
            if (!boolMainMenu) {
                System.out.println("Mauvais choix, merci de recommencer");
            }
            System.out.println("+--------------------------------------+");
            System.out.println("|     Création d'un nouveau compte     |");
            System.out.println("+--------------------------------------+");
            System.out.println("|1 - Création d'un compte simple       |");
            System.out.println("|2 - Création d'un compte épargne      |");
            System.out.println("|3 - Création d'un compte payant       |");
            System.out.println("|4 - Retour                            |");
            System.out.println("+--------------------------------------+");
            System.out.print("Votre choix : ");
            try {
                response = sc.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
            } finally {
                sc.nextLine();
            }
        } while (response < 1 || response > 4);

        switch (response) {
            case 1:
                CompteSimple compteSimple;
                System.out.println("Nouveau compte simple ...");
                System.out.print("Saisissez l'id de votre nouveau compte : ");
                id = saisieInt();
                System.out.print("Saisissez le solde de votre nouveau compte : ");
                solde = saisieFloat();
                System.out.print("Saisissez le montant du découvert : ");
                decouvert = saisieFloat();
                compteSimple = new CompteSimple(solde, decouvert);
                System.out.println("Nouveau compte simple créé");
                comptes.add(compteSimple);
                break;
            case 2:
                CompteEpargne compteEpargne;
                System.out.println("Nouveau compte épargne ...");
                System.out.print("Saisissez l'id de votre nouveau compte : ");
                id = saisieInt();
                System.out.print("Saisissez le solde de votre nouveau compte : ");
                solde = saisieFloat();
                System.out.print("Saisissez le taux d'intérêt de votre nouveau comtpe : ");
                interet = saisieFloat();
                compteEpargne = new CompteEpargne(solde, interet);
                System.out.println("Nouveau compte épargne créé");
                comptes.add(compteEpargne);
                break;
            case 3:
                ComptePayant comptePayant;
                System.out.println("Nouveau compte payant ...");
                System.out.print("Saisissez l'id de votre nouveau compte : ");
                id = saisieInt();
                System.out.print("Saisissez le solde de votre nouveau compte : ");
                solde = saisieFloat();
                comptePayant = new ComptePayant(solde);
                System.out.println("Nouveau compte payant créé");
                comptes.add(comptePayant);
                break;
            case 4:
                dspMainMenu();
                break;
        }
    }

    private static void deleteCompte() {

    }

    private static void listComptes() {
        System.out.println(comptes.toString());

    }

    private static void getComptesByAgence() {

    }

    private static void listAgences() {

    }

    private static float saisieFloat() {
        float solde = 0;
        try {
            solde = sc.nextFloat();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }
        return solde;
    }

    private static int saisieInt() {
        int id = 0;
        try {
            id = sc.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        } finally {
            sc.nextLine();
        }
        return id;
    }

}
