package test;

import dao.impl.DaoEnMemoria;
import dao.impl.DaoH2Veterinario;
import model.Veterinario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VeterinarioServiceEnMemoria {

    private static VeterinarioService veterinarioService = new VeterinarioService(new DaoEnMemoria());

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

    @Test
    @DisplayName("Testear que se listen todos los veterinarios")
    void listar() {
        //DADO
        Veterinario veterinario = new Veterinario("LIC-003", "Moises","Zapata","Cirujano");
        //CUANDO
        Veterinario veterinarioDesdeBD = veterinarioService.guardar(veterinario);
        List<Veterinario> veterinarios = veterinarioService.listar();
        assertNotNull(veterinarios);
    }
}
