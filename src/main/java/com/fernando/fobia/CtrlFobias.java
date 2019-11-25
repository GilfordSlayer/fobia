package com.fernando.fobia;

import com.fernando.fobia.resources.Mensajes;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CtrlFobias {

    @Inject
    private Mensajes mensajes;
    @Inject
    private DaoFobia dao;
    private List<Fobia> instancias;

    @PostConstruct
    void init() {
        try {
            instancias = dao.consulta();
        } catch (Exception ex) {
            mensajes.procesa(ex);
        }
    }
    
    public List<Fobia> getInstancias() {
        return instancias;
    }  
}
