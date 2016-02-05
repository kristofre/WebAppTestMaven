package com.dynatrace.samples.simplewebapp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dynatrace.samples.web.TestDataCreator;
import com.dynatrace.samples.web.dao.DestinationDao;
import com.dynatrace.samples.web.model.Destination;
import static org.junit.Assert.*;

public class ITDestinationDaoTest {
  private static EntityManagerFactory entityManagerFactory;

  @BeforeClass
  public static void setUp() throws Exception {
    entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
  }

  @Before
  public void createTestData() {
    // populate the db...
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    for (Destination d : TestDataCreator.createTestData()) {
      entityManager.persist(d);
    }
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  @After
  public void clearDatabase() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    List<Destination> destinations = entityManager.createQuery(
        "from Destination", Destination.class).getResultList();
    for (Destination destination : destinations) {
      entityManager.remove(destination);
    }
    entityManager.getTransaction().commit();
    entityManager.clear();
    entityManager.close();
  }

  @Test
  public void testGetAllDestinations() throws Exception {
    DestinationDao dd = new DestinationDao(entityManagerFactory);
    List<Destination> result = dd.getAllDestinations();
    assertEquals(2, result.size());
//    result = dd.getAllDestinations();
//    result = dd.getAllDestinations();
  }

  @Test
  public void testGetDestination() {
    DestinationDao dd = new DestinationDao(entityManagerFactory);
    Destination cuba = dd.getDestination("Cuba");
    System.out.println(cuba);
    assertNotNull(cuba);
    assertEquals("Cuba", cuba.getTitle());
    assertTrue(cuba.getDates().size() == 2);
  }

  @Test
  public void testNotExistingDestination() {
    DestinationDao dd = new DestinationDao(entityManagerFactory);
    Destination missing = null;
    try {
      missing = dd.getDestination("Akljlkjdflkjsdfglkjsdlfkgj");
    } catch (Exception e) {
      assertTrue(e instanceof RuntimeException);
    }
    assertNull(missing);
  }

  @Test
  public void testRemoveDestinations() throws Exception {
    DestinationDao dd = new DestinationDao(entityManagerFactory);
    List<Destination> result = dd.getAllDestinations();
    assertEquals(2, result.size());
    dd.removeDestinations();
    result = dd.getAllDestinations();
    assertEquals(0, result.size());
  }
  
  public static junit.framework.Test suite() {
    return new JUnit4TestAdapter(ITDestinationDaoTest.class);
  }

}
