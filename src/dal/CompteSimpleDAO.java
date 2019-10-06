package dal;

import bo.Agence;
import bo.CompteSimple;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteSimpleDAO  implements IDAO<Integer, CompteSimple> {

    private static final String INSERT_QUERY = "INSERT INTO compte (solde, decouvert, type, id_agence) VALUES(?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET solde = ?, decouvert = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE id = ? AND type = 2";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE id = ? AND type = 2";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte WHERE type = 2";

    @Override
    public void create(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, compteSimple.getSolde());
                ps.setFloat(2, compteSimple.getDecouvert());
                ps.setInt(3, compteSimple.getType());
                ps.setInt(4, compteSimple.getIdAgence());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        compteSimple.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setFloat(1, compteSimple.getSolde());
                ps.setFloat(2, compteSimple.getDecouvert());
                ps.setInt(3, compteSimple.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, compteSimple.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public CompteSimple findById(Integer id) throws SQLException, IOException, ClassNotFoundException {
        CompteSimple compteSimple = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compteSimple = new CompteSimple();
                        compteSimple.setId(rs.getInt("id"));
                        compteSimple.setSolde(rs.getFloat("solde"));
                        compteSimple.setDecouvert(rs.getFloat("decouvert"));
                        compteSimple.setType(rs.getInt("type"));

                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteSimple.setIdAgence(agence.getId());
                    }
                }
            }
        }
        return compteSimple;
    }

    @Override
    public List<CompteSimple> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<CompteSimple> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CompteSimple compteSimple = new CompteSimple();
                        compteSimple.setId(rs.getInt("id"));
                        compteSimple.setSolde(rs.getFloat("solde"));
                        compteSimple.setDecouvert(rs.getFloat("decouvert"));
                        compteSimple.setType(rs.getInt("type"));

                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteSimple.setIdAgence(agence.getId());

                        list.add(compteSimple);
                    }
                }
            }
        }
        return list;
    }
}
