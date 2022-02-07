package com.gameseeker.app.repositories;

import java.util.List;

import com.gameseeker.app.models.ThemeModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ThemeRepository responsible for CRUD operations on ThemeModel
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see ThemeModel
 */
@Repository
public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {

  /**
   * Method responsible for find theme by theme name
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param themeName
   * @return Optional<UserModel>
   */
  public List<ThemeModel> findAllByThemeNameContainingIgnoreCase(String themeName);
}
