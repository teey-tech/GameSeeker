package com.gameseeker.app.dtos;

/**
 * Class responsible for DTO credentials.
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see UserModel
 * @see UserLoginDTO
 */
public class UserCredentialsDTO {

  // User generated
  private String basicToken;
  private Long id;
  private String email;

  // Super Constructor
  public UserCredentialsDTO(String basicToken, Long id, String email) {
    super();
    this.basicToken = basicToken;
    this.id = id;
    this.email = email;
  }

  public UserCredentialsDTO() {
  }

  // Getters and Setters
  public String getBasicToken() {
    return this.basicToken;
  }

  public void setBasicToken(String basicToken) {
    this.basicToken = basicToken;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
