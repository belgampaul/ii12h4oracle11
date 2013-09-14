package be.belgampaul.entities;

import javax.persistence.*;

/**
 * User: ikka
 * Date: 14.09.13
 * Time: 2:10
 */
@Entity(name = "USERS")
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO, generator="users_seq_gen")
  @SequenceGenerator(name="users_seq_gen", sequenceName="users_seq")
  private Long id;
  private String login;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }
}
