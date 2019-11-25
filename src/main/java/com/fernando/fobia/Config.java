package com.fernando.fobia;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.faces.annotation.FacesConfig;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
@FacesConfig
public class Config {
  /* @Produces indica que se puede usar en @Inject. @PersistenceContext indica
   * que usa transacciones y el archivo persistence.xml */
  @Produces
  @PersistenceContext
  EntityManager em;
}
