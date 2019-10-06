package dal;
import bo.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO implements IDAO<Integer, Agence> {

    private static final String INSERT_QUERY = "INSERT INTO agence (code, adresse) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE agence SET code = ?, adresse = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM agence WHERE id = ?";
    private static final String FIND_QUERY = "SELECT * FROM agence WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM agence";
    private static final String FIND_ALL_AGENCE_QUERY = "SELECT * FROM compte WHERE id_agence = ?";

    @Override
    public void create(Agence agence) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(2, agence.getCode());
                ps.setString(3, agence.getAddress());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        agence.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(Agence agence) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setString(1, agence.getCode());
                ps.setString(2, agence.getAddress());
                ps.setInt(3, agence.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(Agence agence) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, agence.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Agence findById(Integer id) throws SQLException, IOException, ClassNotFoundException {
        Agence agence = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        agence = new Agence();
                        agence.setId(rs.getInt("id"));
                        agence.setCode(rs.getString("code"));
                        agence.setAddress(rs.getString("adresse"));
                    }
                }
            }
        }
        return agence;
    }

    @Override
    public List<Agence> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Agence> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Agence agence = new Agence();
                        agence.setId(rs.getInt("id"));
                        agence.setCode(rs.getString("code"));
                        agence.setAddress(rs.getString("adresse"));

                        list.add(agence);
                    }
                }
            }
        }
        return list;
    }

    public List<Compte> findCompte(int id) throws SQLException, IOException, ClassNotFoundException {
        List<Compte> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_AGENCE_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        switch (rs.getInt("type")) {
                            case 1 :
                                CompteEpargne compteEpagne = new CompteEpargne();
                                compteEpagne.setId(rs.getInt("id"));
                                compteEpagne.setSolde(rs.getFloat("solde"));
                                compteEpagne.setType(rs.getInt("type"));
                                compteEpagne.setTauxInteret(rs.getInt("taux_interet"));
                                list.add(compteEpagne);
                                break;
                            case 2 :
                                CompteSimple compteSimple = new CompteSimple();
                                compteSimple.setId(rs.getInt("id"));
                                compteSimple.setSolde(rs.getFloat("solde"));
                                compteSimple.setType(rs.getInt("type"));
                                compteSimple.setDecouvert(rs.getFloat("decouvert"));
                                list.add(compteSimple);
                                break;
                            case 3 :
                                ComptePayant comptePayant = new ComptePayant();
                                comptePayant.setId(rs.getInt("id"));
                                comptePayant.setSolde(rs.getFloat("solde"));
                                comptePayant.setType(rs.getInt("type"));
                                list.add(comptePayant);
                                break;
                        }
                        
                    }
                }
            }
        }
        return list;
    }

    public boolean exist(int id) throws SQLException, IOException, ClassNotFoundException  {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {

                    //return true si existe
                    return rs.next();
                }
            }
        }

        return false;
    }
}