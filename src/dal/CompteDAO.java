package dal;

import bo.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {

    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Compte> list = new ArrayList<>();

        CompteEpargneDAO compteEpargneDAO = new CompteEpargneDAO();
        List<CompteEpargne> listCompteEpargneDAO = compteEpargneDAO.findAll();
        for (CompteEpargne compte : listCompteEpargneDAO) {
            list.add(compte);
        }

        ComptePayantDAO comptePayantDAO = new ComptePayantDAO();
        List<ComptePayant> listComptePayantDAO = comptePayantDAO.findAll();
        for (ComptePayant compte : listComptePayantDAO) {
            list.add(compte);
        }

        CompteSimpleDAO compteSimpleDAO = new CompteSimpleDAO();
        List<CompteSimple> listCompteSimpleDAO = compteSimpleDAO.findAll();
        for (CompteSimple compte : listCompteSimpleDAO) {
            list.add(compte);
        }

        return list;
    }
}
