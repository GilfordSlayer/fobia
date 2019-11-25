package com.fernando.fobia;


import com.fernando.fobia.resources.Mensajes;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class CtrlFobia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Mensajes mensajes;
    @Inject
    private ExternalContext externalContext;
    @Inject
    private DaoFobia dao;
    private Integer id;
    private Fobia modelo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fobia getModelo() {
        return modelo;
    }

    public void init() {
        try {
            leeLlavePrimaria();
            this.modelo = dao.busca(id);
        } catch (NumberFormatException e) {
            mensajes.error(null, "Id no encontrado.");
        } catch (Exception e) {
            mensajes.procesa(e);
        }
    }

    private void leeLlavePrimaria() throws NumberFormatException {
        final String parámetroId
                = externalContext.getRequestParameterMap().get("id");
        id = new Integer(parámetroId);
    }

    public String guarda() {
        try {
            dao.modifica(modelo);
            return "index?faces-redirect=true";
        } catch (Exception ex) {
            mensajes.procesa(ex);
            return null;
        }
    }

    public String elimina() {
        try {
            if (modelo != null) {
                dao.elimina(modelo);
                return "index?faces-redirect=true";
            }
        } catch (Exception ex) {
            mensajes.procesa(ex);
        }
        return null;
    }
}
