package dal;

import bo.Agence;
import bo.CompteEpargne;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteEpargneDAO implements IDAO<Integer, CompteEpargne> {

    private static final String INSERT_QUERY = "INSERT INTO compte (solde, taux_interet, type, id_agence) VALUES(?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET solde = ?, taux_interet = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE id = ? AND type = 1";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE id = ? AND type = 1";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte WHERE type = 1";

    @Override
    public void create(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, compteEpargne.getSolde());
                ps.setFloat(2, compteEpargne.getTauxInteret());
                ps.setInt(3, compteEpargne.getType());
                ps.setInt(4, compteEpargne.getIdAgence());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        compteEpargne.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setFloat(1, compteEpargne.getSolde());
                ps.setFloat(2, compteEpargne.getTauxInteret());
                ps.setInt(3, compteEpargne.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, compteEpargne.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public CompteEpargne findById(Integer id) throws SQLException, IOException, ClassNotFoundException {
        CompteEpargne compteEpargne = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compteEpargne = new CompteEpargne();
                        compteEpargne.setId(rs.getInt("id"));
                        compteEpargne.setSolde(rs.getFloat("solde"));
                        compteEpargne.setTauxInteret(rs.getFloat("taux_interet"));
                        compteEpargne.setType(rs.getInt("type"));

                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteEpargne.setIdAgence(agence.getId());
                    }
                }
            }
        }
        return compteEpargne;
    }

    @Override
    public List<CompteEpargne> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<CompteEpargne> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CompteEpargne compteEpargne = new CompteEpargne();
                        compteEpargne.setId(rs.getInt("id"));
                        compteEpargne.setSolde(rs.getInt("solde"));
                        compteEpargne.setTauxInteret(rs.getInt("taux_interet"));
                        compteEpargne.setType(rs.getInt("type"));

                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteEpargne.setIdAgence(agence.getId());

                        list.add(compteEpargne);
                    }
                }
            }
        }
        return list;
    }
}