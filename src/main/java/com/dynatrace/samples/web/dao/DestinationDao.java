package com.dynatrace.samples.web.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.dynatrace.samples.web.model.Destination;

public class DestinationDao {
  private EntityManagerFactory entityManagerFactory;

  public DestinationDao(EntityManagerFactory emf) {
    this.entityManagerFactory = emf;
  }

  public List<Destination> getAllDestinations() throws Exception {
    if (entityManagerFactory == null) {
      throw new RuntimeException("No EntityManagerFactory!");
    }
    
    EntityManager em = entityManagerFactory.createEntityManager();
    List<Destination> destinations = em.createQuery("from Destination",
        Destination.class).getResultList();
    em.close();
    return Collections.unmodifiableList(destinations);
  }

  public Destination getDestination(String string) {
    if (entityManagerFactory == null) {
      throw new RuntimeException("No EntityManagerFactory!");
    }
    EntityManager em = entityManagerFactory.createEntityManager();
    TypedQuery<Destination> q = em
        .createQuery(
            "select d from Destination as d inner join fetch d.dates where d.title = :title",
            Destination.class);
    q.setParameter("title", string);
    List<Destination> destinations = q.getResultList();
    em.close();

    if (destinations.size() == 0) {
      return null;
    } else {
      return destinations.get(0);
    }
  }

  public void storeDestination(Destination... dest) {
    if (entityManagerFactory == null) {
      throw new RuntimeException("No EntityManagerFactory!");
    }
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    for (int i = 0; i < dest.length; i++) {
      em.persist(dest[i]);
    }
    em.getTransaction().commit();
    em.close();
  }

  public void removeDestinations() {
    if (entityManagerFactory == null) {
      throw new RuntimeException("No EntityManagerFactory!");
    }
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.createQuery("delete from Journey j").executeUpdate();
    em.createQuery("delete from Destination d").executeUpdate();
    em.getTransaction().commit();
  }
}
