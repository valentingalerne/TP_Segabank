import bo.*;
import dal.*;

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
                selectCompte();
                dspMainMenu();
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
        int idAgence = 0;
        boolean exist = false;

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
                while (!exist) {
                    System.out.print("Saisissez l'id de l'agence : ");
                    idAgence = saisieInt();

                    AgenceDAO agence = new AgenceDAO();
                    exist = agence.exist(idAgence);
                    if (!exist) {
                        System.out.println("L'agence " + idAgence + " n'existe pas !");
                    }
                }
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
                System.out.print("Saisissez le taux d'intérêt de votre nouveau compte : ");
                interet = saisieFloat();
                while (!exist) {
                    System.out.print("Saisissez l'id de l'agence : ");
                    idAgence = saisieInt();

                    AgenceDAO agence = new AgenceDAO();
                    exist = agence.exist(idAgence);
                    if (!exist) {
                        System.out.println("L'agence " + idAgence + " n'existe pas !");
                    }
                }
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
                while (!exist) {
                    System.out.print("Saisissez l'id de l'agence : ");
                    idAgence = saisieInt();

                    AgenceDAO agence = new AgenceDAO();
                    exist = agence.exist(idAgence);
                    if (!exist) {
                        System.out.println("L'agence " + idAgence + " n'existe pas !");
                    }
                }
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

    private static void deleteCompte() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Liste des comptes : ");
        listComptes();

        System.out.print("Saisir l'id du compte à supprimer : ");
        int id = saisieInt();

        CompteDAO compte = new CompteDAO();
        int type = compte.getType(id);

        switch (type) {
            case 1 :
                CompteEpargneDAO compteEpagneDAO = new CompteEpargneDAO();
                CompteEpargne compteEpargne = compteEpagneDAO.findById(id);

                compteEpagneDAO.remove(compteEpargne);
                System.out.println("Compte Supprimé !");
                break;
            case 2 :
                CompteSimpleDAO compteSimpleDAO = new CompteSimpleDAO();
                CompteSimple compteSimple = compteSimpleDAO.findById(id);

                compteSimpleDAO.remove(compteSimple);
                System.out.println("Compte Supprimé !");
                break;
            case 3 :
                ComptePayantDAO comptePayantDAO = new ComptePayantDAO();
                ComptePayant comptePayant = comptePayantDAO.findById(id);

                comptePayantDAO.remove(comptePayant);
                System.out.println("Compte Supprimé !");
                break;
            case -1 :
                System.out.println("Le compte saisie n'existe pas !");
                break;
        }
    }

    private static void selectCompte() throws SQLException, IOException, ClassNotFoundException {
        CompteEpargne compteEpargne = null;
        CompteEpargneDAO compteEpagneDAO = new CompteEpargneDAO();
        CompteSimple compteSimple = null;
        CompteSimpleDAO compteSimpleDAO = new CompteSimpleDAO();
        ComptePayant comptePayant = null;
        ComptePayantDAO comptePayantDAO = new ComptePayantDAO();

        boolean exist = false;
        int id = 0;
        CompteDAO compte = null;

        System.out.println("Liste des comptes : ");
        listComptes();

        while (!exist) {
            System.out.print("Saisir l'id du compte à utiliser : ");
            id = saisieInt();

            compte = new CompteDAO();

            exist = compte.exist(id);
            if (!exist) {
                System.out.println("Ce compte n'existe pas !");
            }
        }
        int type = compte.getType(id);

        switch (type) {
             case 1 :
                compteEpargne = compteEpagneDAO.findById(id);
                break;
            case 2 :
                compteSimple = compteSimpleDAO.findById(id);
                break;
            case 3 :
                comptePayant = comptePayantDAO.findById(id);
                break;
        }

        int choix = 0;
        while (choix != 1 && choix != 2 && choix != 3) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1 - Retrait");
            System.out.println("2 - Virement");
            System.out.println("3 - Sortir");
            System.out.print("Votre choix : ");
            choix = saisieInt();
        }

        switch (choix) {
            case 1 :
                float retrait;

                switch (type) {
                    case 1 :
                        System.out.print("Solde du compte : ");
                        System.out.println(compteEpargne.getSolde());

                        System.out.print("Combien voulez-vous retirer ? ");
                        retrait = saisieFloat();

                        compteEpargne.retrait(retrait);

                        compteEpagneDAO.update(compteEpargne);
                        break;
                    case 2 :
                        System.out.print("Solde du compte : ");
                        System.out.println(compteSimple.getSolde());

                        System.out.print("Combien voulez-vous retirer ? ");
                        retrait = saisieFloat();

                        compteSimple.retrait(retrait);

                        compteSimpleDAO.update(compteSimple);
                        break;
                    case 3 :
                        System.out.print("Solde du compte : ");
                        System.out.println(comptePayant.getSolde());

                        System.out.print("Combien voulez-vous retirer ? ");
                        retrait = saisieFloat();

                        comptePayant.retrait(retrait);

                        comptePayantDAO.update(comptePayant);
                        break;
                }
                break;
            case 2 :
                float virement;

                switch (type) {
                    case 1 :
                        System.out.print("Solde du compte : ");
                        System.out.println(compteEpargne.getSolde());

                        System.out.print("Combien voulez-vous virer ? ");
                        virement = saisieFloat();

                        compteEpargne.versement(virement);

                        compteEpagneDAO.update(compteEpargne);
                        break;
                    case 2 :
                        System.out.print("Solde du compte : ");
                        System.out.println(compteSimple.getSolde());

                        System.out.print("Combien voulez-vous virer ? ");
                        virement = saisieFloat();

                        compteSimple.versement(virement);

                        compteSimpleDAO.update(compteSimple);
                        break;
                    case 3 :
                        System.out.print("Solde du compte : ");
                        System.out.println(comptePayant.getSolde());

                        System.out.print("Combien voulez-vous virer ? ");
                        virement = saisieFloat();

                        comptePayant.versement(virement);

                        comptePayantDAO.update(comptePayant);
                        break;
                }
                break;
        }
    }

    private static void listComptes() throws SQLException, IOException, ClassNotFoundException {
        CompteDAO compte = new CompteDAO();
        List<Compte> list = compte.findAll();
        for (Compte c : list) {
            System.out.println(c.toString());
        }
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
