package be.belgampaul;

import javax.persistence.*;

@Entity
public class Customer {

  private String firstName;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
  @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq")
  private Long id;
  private String lastName;

  private Customer() {
  }

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

}