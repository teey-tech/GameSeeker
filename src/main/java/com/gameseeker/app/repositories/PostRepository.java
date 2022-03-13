package com.gameseeker.app.repositories;

import java.util.List;

import com.gameseeker.app.models.PostModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PostRepository responsible for CRUD operations on PostModel
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see PostModel
 */
@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {

  /**
   * Method responsible for find post by title
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param title
   * @return Optional<UserModel>
   */
  public List<PostModel> findAllByTitleContainingIgnoreCase(String title);

}
