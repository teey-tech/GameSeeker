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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class of persistence ThemeModel
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 */
@Entity
@Table(name = "tb_themes")
public class ThemeModel {

  // System generated
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idTheme;

  // User generated
  @NotBlank(message = "Attribute Theme name is Obligatory!")
  private String themeName;

  // System relations
  @OneToMany(mappedBy = "theme", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties({ "theme" })
  private List<PostModel> post = new ArrayList<>();

  // Getters and Setters
  public Long getIdTheme() {
    return this.idTheme;
  }

  public void setIdTheme(Long idTheme) {
    this.idTheme = idTheme;
  }

  public String getThemeName() {
    return this.themeName;
  }

  public void setThemeName(String themeName) {
    this.themeName = themeName;
  }

  public List<PostModel> getPost() {
    return this.post;
  }

  public void setPost(List<PostModel> post) {
    this.post = post;
  }

}
