package com.gameseeker.app.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Class responsible for DTO Userlogin.
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see UserModel
 * @see UserCredentialsDTO
 */
public class UserLoginDTO {

  // User generated
  @NotNull(message = "Attribute Email is Obligatory!")
  @Email(message = "Inform a valid Email")
  private String email;

  @NotNull(message = "Attribute Password is Obligatory")
  private String password;

  // Getters and Setters
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
