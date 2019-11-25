package com.fernando.fobia;

import com.fernando.fobia.resources.Mensajes;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;


/** Controlador que se utiliza en varias vistas. La anotación
 * <code>@ViewScoped</code> indica que los objetos se mantienen almacenados en
 * el archivo de sesión mientras se muestre la vista que está usando este bean.
 * Al cambiar de vista, los datos se pierden. */
@Named
@ViewScoped
public class CtrlFobiaNueva implements Serializable {
  private static final long serialVersionUID = 1L;
  @Inject
  private Mensajes mensajes;
  @Inject
  private DaoFobia dao;
  private Fobia modelo;
  @PostConstruct
  void init() {
    modelo = new Fobia();
  }
  public Fobia getModelo() {
    return modelo;
  }
  
  public String guarda() {
    try {
      dao.agrega(modelo);
      return "index?faces-redirect=true";
    } catch (Exception ex) {
      mensajes.procesa(ex);
      return null;
    }
  }
}