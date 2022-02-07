package com.gameseeker.app.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class of persistence PostModel
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 */
@Entity
@Table(name = "tb_posts")
public class PostModel {

  // System generated
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPost;

  // User generated
  @NotBlank(message = "Attribute Title is Obligatory!")
  @Size(min = 5, max = 100, message = "Attribute Tittle needs to contain at least 05 and max 100 characters")
  private String title;

  @NotBlank(message = "Attribute Description is Obligatory!")
  @Size(min = 10, max = 1000, message = "Attribute Description needs to contain at least 10 and max 10000 characters")
  private String description;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
  private LocalDate releaceDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
  private LocalDate postDate;

  @NotNull(message = "Attribute Price is Obligatory!")
  private Float price;

  @NotNull(message = "Attribute Stock is Obligatory!")
  private Integer stock;

  @NotNull(message = "Attribute picture is Obligatory!")
  private Integer picture;

  // System relations
  @ManyToOne
  @JoinColumn(name = "fk_user")
  @JsonIgnoreProperties({ "post" })
  private UserModel user;

  @ManyToOne
  @JoinColumn(name = "fk_theme")
  @JsonIgnoreProperties({ "theme" })
  private ThemeModel theme;

  // Getters and Setters
  public Long getIdPost() {
    return this.idPost;
  }

  public void setIdPost(Long idPost) {
    this.idPost = idPost;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getReleaceDate() {
    return this.releaceDate;
  }

  public void setReleaceDate(LocalDate releaceDate) {
    this.releaceDate = releaceDate;
  }

  public LocalDate getPostDate() {
    return this.postDate;
  }

  public void setPostDate(LocalDate postDate) {
    this.postDate = postDate;
  }

  public Float getPrice() {
    return this.price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getStock() {
    return this.stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Integer getPicture() {
    return this.picture;
  }

  public void setPicture(Integer picture) {
    this.picture = picture;
  }

  public UserModel getUser() {
    return this.user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public ThemeModel getTheme() {
    return this.theme;
  }

  public void setTheme(ThemeModel theme) {
    this.theme = theme;
  }

}
