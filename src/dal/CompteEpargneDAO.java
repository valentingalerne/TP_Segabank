package dal;

import bo.CompteEpargne;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteEpargneDAO implements IDAO<Long, CompteEpargne> {

    private static final String INSERT_QUERY = "INSERT INTO compte_epargne (solde, email) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte_epargne SET name = ?, email = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte_epargne WHERE id = ?";
    private static final String FIND_QUERY = "SELECT * FROM compte_epargne WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte_epargne";

    @Override
    public void create(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, compteEpargne.getSolde());
                ps.setFloat(2, compteEpargne.getTauxInteret());
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
    public CompteEpargne findById(Long id) throws SQLException, IOException, ClassNotFoundException {
        CompteEpargne compteEpargne = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compteEpargne = new CompteEpargne();
                        compteEpargne.setId(rs.getInt("id"));
                        compteEpargne.setSolde(rs.getInt("solde"));
                        compteEpargne.setTauxInteret(rs.getInt("taux_interet"));
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
                        list.add(compteEpargne);
                    }
                }
            }
        }
        return list;
    }
}
