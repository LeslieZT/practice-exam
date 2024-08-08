package service;

import dao.IDao;
import model.Veterinario;

import java.util.List;

public class VeterinarioService {
    private IDao<Veterinario> veterinarioIDao;

    public VeterinarioService(IDao<Veterinario> veterinarioIDao) {
        this.veterinarioIDao = veterinarioIDao;
    }

    public Veterinario guardar ( Veterinario veterinario) {
        return  veterinarioIDao.guardar(veterinario);
    }

    public List<Veterinario> listar () {
        return  veterinarioIDao.listaTodos();
    }

}
