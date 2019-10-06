package dal;

import bo.Agence;
import bo.ComptePayant;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComptePayantDAO implements IDAO<Integer, ComptePayant> {

    private static final String INSERT_QUERY = "INSERT INTO compte (solde, type, id_agence) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET solde = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE id = ? AND type = 3";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE id = ? AND type = 3";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte WHERE type = 3";

    @Override
    public void create(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, comptePayant.getSolde());
                ps.setInt(2, comptePayant.getType());
                ps.setInt(3, comptePayant.getIdAgence());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        comptePayant.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setFloat(1, comptePayant.getSolde());
                ps.setInt(2, comptePayant.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, comptePayant.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public ComptePayant findById(Integer id) throws SQLException, IOException, ClassNotFoundException {
        ComptePayant comptePayant = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        comptePayant = new ComptePayant();
                        comptePayant.setId(rs.getInt("id"));
                        comptePayant.setSolde(rs.getFloat("solde"));
                        comptePayant.setType(rs.getInt("type"));

                        AgenceDAO agenceDAO = null;
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        comptePayant.setIdAgence(agence.getId());
                    }
                }
            }
        }
        return comptePayant;
    }

    @Override
    public List<ComptePayant> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<ComptePayant> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ComptePayant comptePayant = new ComptePayant();
                        comptePayant.setId(rs.getInt("id"));
                        comptePayant.setSolde(rs.getInt("solde"));
                        comptePayant.setType(rs.getInt("type"));

                        AgenceDAO agenceDAO = null;
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        comptePayant.setIdAgence(agence.getId());

                        list.add(comptePayant);
                    }
                }
            }
        }
        return list;
    }
}
