package com.fernando.fobia;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@Stateless
@Dependent
public class DaoFobia {

    @Inject
    private EntityManager em;

    public List<Fobia> consulta() {
        /* Con el resultado de la consulta se llena una lista con objetos de la
     * clase "Pasatiempo". */
        return em.createQuery("SELECT c FROM Fobia c ORDER BY c.nombre",
                Fobia.class).getResultList();
    }

    public Fobia busca(Integer id) {
        return em.find(Fobia.class, id);
    }

    public void agrega(Fobia modelo) {
        em.persist(modelo); // Agrega el modelo a la base de datos.
    }

    public void modifica(Fobia modelo) {
        em.merge(modelo);// Guarda los cambios al modelo.
    }

    public void elimina(Fobia modelo) {
        // Busca el modelo en base a su id.
        final Fobia anterior = em.find(Fobia.class, modelo.getId());
        // Si el resultado es null, el chismoso ya no está registrado.
        if (anterior != null) {
            // Pero si la referencia es diferente de null, hay que eliminar el objeto.
            em.remove(anterior);
        }
    }
}
