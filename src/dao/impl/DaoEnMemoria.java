package dao.impl;

import dao.IDao;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DaoEnMemoria implements IDao<Veterinario> {
    public static final Logger logger = Logger.getLogger(DaoEnMemoria.class);
    private List<Veterinario> veterinarios = new ArrayList<>();

    @Override
    public Veterinario guardar(Veterinario veterinario) {
        veterinario.setId(veterinarios.size()+1);
        veterinarios.add(veterinario);
        logger.info("veterinario guardado en memoria " +veterinario);
        return veterinario;
    }

    @Override
    public List<Veterinario> listaTodos() {
        return veterinarios;
    }
}
