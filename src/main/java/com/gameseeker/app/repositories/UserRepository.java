package com.gameseeker.app.repositories;

import java.util.List;
import java.util.Optional;

import com.gameseeker.app.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository responsible for CRUD operations on UserModel
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see UserModel
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

  /**
   * Method responsible for find user by email
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param email
   * @return Optional<UserModel>
   */
  public Optional<UserModel> findByEmail(String email);

  /**
   * Method responsible for find post by title
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param title
   * @return Optional<UserModel>
   */
  public List<UserModel> findAllByFavoritThemeContainingIgnoreCase(String favoritTheme);

}
