package com.gameseeker.app.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class of persistence UserModel
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 */
@Entity
@Table(name = "tb_users")
public class UserModel {

  // System generated
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long idUser;

  private Long seekerCoins = 500l;

  // User generated
  @NotNull(message = "Attribute Name is Obligatory!")
  private String name;

  @NotNull(message = "Attribute Email is Obligatory!")
  @Email(message = "Inform a valid Email")
  private String email;

  @NotNull(message = "Attribute Password is Obligatory")
  private String password;

  private String picture;

  // System relations
  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties({ "user" })
  private List<PostModel> post = new ArrayList<>();

  public UserModel(Long idUser, Long seekerCoins, String name, String email, String password, String picture) {
    this.idUser = idUser;
    this.seekerCoins = seekerCoins;
    this.name = name;
    this.email = email;
    this.password = password;
    this.picture = picture;
  }

  public UserModel() {
  }

  // Getters and Setters
  public Long getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public Long getSeekerCoins() {
    return this.seekerCoins;
  }

  public void setSeekerCoins(Long seekerCoins) {
    this.seekerCoins = seekerCoins;
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

  public List<PostModel> getPost() {
    return this.post;
  }

  public void setPost(List<PostModel> post) {
    this.post = post;
  }

}
