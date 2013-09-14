package be.belgampaul.examples;

import be.belgampaul.Customer;
import be.belgampaul.CustomerRepository;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

/**
 * User: ikka
 * Date: 14.09.13
 * Time: 5:11
 */
@Configuration
@EnableJpaRepositories
public class Main {
  @Bean
  public DataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    ds.setUrl("jdbc:oracle:thin:@localhost:1521:ikka");
    ds.setUsername("root");
    ds.setPassword("toor");
    return ds;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
    lef.setDataSource(dataSource);
    lef.setJpaVendorAdapter(jpaVendorAdapter);
    lef.setPackagesToScan("be");
    return lef;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setShowSql(true);
    hibernateJpaVendorAdapter.setGenerateDdl(false);
    hibernateJpaVendorAdapter.setDatabase(Database.ORACLE);
    return hibernateJpaVendorAdapter;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }


  public static void main(String[] args) {
    AbstractApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
    CustomerRepository repository = context.getBean(CustomerRepository.class);

    // save a couple of customers

    repository.save(new Customer("Jack", "Bauer"));
    repository.save(new Customer("Chloe", "O'Brian"));
    repository.save(new Customer("Kim", "Bauer"));
    repository.save(new Customer("David", "Palmer"));
    repository.save(new Customer("Michelle", "Dessler"));

    // fetch all customers
    List<Customer> customers = repository.findAll();
    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");
    for (Customer customer : customers) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer by ID
    Customer customer = repository.findOne(1L);
    System.out.println("Customer found with findOne(1L):");
    System.out.println("--------------------------------");
    System.out.println(customer);
    System.out.println();

    // fetch customers by last name
    List<Customer> bauers = repository.findByLastName("Bauer");
    System.out.println("Customer found with findByLastName('Bauer'):");
    System.out.println("--------------------------------------------");
    for (Customer bauer : bauers) {
      System.out.println(bauer);
    }

    context.close();
  }

}
