import bo.*;
import dal.CompteEpargneDAO;
import dal.ComptePayantDAO;
import dal.CompteSimpleDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        dspMainMenu();
    }

    public static void dspMainMenu() throws SQLException, IOException, ClassNotFoundException {

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
                deleteCompte();
                dspMainMenu();
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

    private static void createCompte() throws SQLException, IOException, ClassNotFoundException {
        int response;
        boolean boolMainMenu = true;

        float solde;
        float decouvert;
        float interet;
        int idAgence;

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
                CompteSimpleDAO compteSimpleDAO;

                System.out.println("Nouveau compte simple ...");
                System.out.print("Saisissez le solde de votre nouveau compte : ");
                solde = saisieFloat();
                System.out.print("Saisissez le montant du découvert : ");
                decouvert = saisieFloat();
                System.out.print("Saisissez l'id de l'agence : ");
                idAgence = saisieInt();
                compteSimple = new CompteSimple(solde, decouvert, idAgence);
                compteSimpleDAO = new CompteSimpleDAO();
                compteSimpleDAO.create(compteSimple);
                System.out.println("Nouveau compte simple créé");
                break;
            case 2:
                CompteEpargne compteEpargne;
                CompteEpargneDAO compteEpargneDAO;

                System.out.println("Nouveau compte épargne ...");
                System.out.print("Saisissez le solde de votre nouveau compte : ");
                solde = saisieFloat();
                System.out.print("Saisissez le taux d'intérêt de votre nouveau comtpe : ");
                interet = saisieFloat();
                System.out.print("Saisissez l'id de l'agence : ");
                idAgence = saisieInt();
                compteEpargne = new CompteEpargne(solde, interet, idAgence);
                compteEpargneDAO = new CompteEpargneDAO();
                compteEpargneDAO.create(compteEpargne);
                System.out.println("Nouveau compte épargne créé");
                break;
            case 3:
                ComptePayant comptePayant;
                ComptePayantDAO comptePayantDAO;

                System.out.println("Nouveau compte payant ...");
                System.out.print("Saisissez le solde de votre nouveau compte : ");
                solde = saisieFloat();
                System.out.print("Saisissez l'id de l'agence : ");
                idAgence = saisieInt();
                comptePayant = new ComptePayant(solde, idAgence);
                comptePayantDAO = new ComptePayantDAO();
                comptePayantDAO.create(comptePayant);
                System.out.println("Nouveau compte payant créé");
                break;
            case 4:
                dspMainMenu();
                break;
        }
    }

    private static void deleteCompte() {

    }

    private static void listComptes() {

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
