import be.belgampaul.entities.User;

import javax.persistence.*;
import java.util.List;

/**
 * User: ikka
 * Date: 14.09.13
 * Time: 2:12
 */
public class Main {
  private static final String PERSISTENCE_UNIT_NAME = "examplePU";
  private static EntityManagerFactory factory;
  public static void main(String[] args) {
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager entityManager = factory.createEntityManager();
    //insertDefaultUser(entityManager);
    showUsers(entityManager);
    User user = new User();
    user.setId(-2L);
    user.setLogin("test");
    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();

    //createUsersTable(entityManager);
  }

  private static void showUsers(EntityManager entityManager) {
    Query nativeQuery = entityManager.createNativeQuery("select id, login from users");
    List resultList = nativeQuery.getResultList();
    for (Object o : resultList) {
       o = null;
    }

  }

  private static void createUsersTable(EntityManager entityManager) {
    Query nativeQuery = entityManager.createNativeQuery("create table users (id numeric(15) primary key, login varchar(20) unique)");
    nativeQuery.executeUpdate();
  }

  private static void insertDefaultUser(EntityManager entityManager) {
    Query nativeQuery = entityManager.createNativeQuery("insert into users (id , login) values (-1, 'ikka')");
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    nativeQuery.executeUpdate();
    transaction.commit();
  }
}

