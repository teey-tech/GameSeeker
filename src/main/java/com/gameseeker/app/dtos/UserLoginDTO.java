package com.gameseeker.app.dtos;

/**
 * Class responsible for DTO Userlogin.
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see UserModel
 */
public class UserLoginDTO {

  // User generated
  private Long idUser;

  private String name;

  private String email;

  private String password;

  private String picture;

  private String token;

  // Getters and Setters
  public Long getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public String getPicture() {
    return this.picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
