package test;

import dao.impl.DaoH2Veterinario;
import model.Veterinario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceTest {

    private static final Logger logger = Logger.getLogger(VeterinarioServiceTest.class);
    private static VeterinarioService veterinarioService = new VeterinarioService(new DaoH2Veterinario());

    @BeforeAll
    static void crearTabla(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection =  DriverManager.getConnection("jdbc:h2:./veterinarios;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que se listen todos los veterinarios")
    void listar() {
        //DADO
        // Veterinario veterinario = new Veterinario("LIC-002", "Moises","Zapata","Cirujano");
        //CUANDO
        // Veterinario veterinarioDesdeBD = veterinarioService.guardar(veterinario);
        List<Veterinario> veterinarios = veterinarioService.listar();
        assertNotNull(veterinarios);
    }

    @Test
    @DisplayName("Testear que se agregue un veterinario de manera correcta")
    void guardar() {
        //DADO
        Veterinario veterinario = new Veterinario("LIC-002", "Moises","Zapata","Cirujano");
        //CUANDO
        Veterinario veterinarioDesdeBD = veterinarioService.guardar(veterinario);
        // entonces
        assertNotNull(veterinarioDesdeBD);
    }
}