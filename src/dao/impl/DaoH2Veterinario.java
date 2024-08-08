package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoH2Veterinario implements IDao<Veterinario> {
    public static final Logger logger = Logger.getLogger(DaoH2Veterinario.class);
    public static final String INSERT = "INSERT INTO VETERINARIOS VALUES(DEFAULT,?,?,?,?)";
    public static final String SELECT_ALL = "SELECT * FROM VETERINARIOS";

    @Override
    public Veterinario guardar(Veterinario veterinario) {
        Connection connection = null;
        Veterinario veterinarioARetornar = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, veterinario.getNumeroLicencia());
            preparedStatement.setString(2, veterinario.getNombre());
            preparedStatement.setString(3, veterinario.getApellido());
            preparedStatement.setString(4, veterinario.getEspecialidad());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                veterinarioARetornar = new Veterinario(id, veterinario.getNumeroLicencia(), veterinario.getNombre(),
                        veterinario.getApellido(), veterinario.getEspecialidad());
            }
            logger.info("veterinario "+ veterinarioARetornar);

        }catch (Exception e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                } finally {
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return veterinarioARetornar;
    }

    @Override
    public List<Veterinario> listaTodos() {
        Connection connection = null;
        List<Veterinario> veterinarios = new ArrayList<>();
        Veterinario veterinarioDesdeLaDB = null;
        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String numeroSpecialidad = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                String espcialidad = resultSet.getString(5);
                veterinarioDesdeLaDB = new Veterinario(id, numeroSpecialidad, nombre, apellido, espcialidad);
                // vamos cargando la lista
                veterinarios.add(veterinarioDesdeLaDB);
                logger.info("veterninario "+ veterinarioDesdeLaDB);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        System.out.println(veterinarios);
        return veterinarios;
    }
}
