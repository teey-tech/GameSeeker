package com.gameseeker.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gameseeker.app.models.PostModel;
import com.gameseeker.app.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Creating PostController.
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see PostModel
 * @see PostRepository
 */
@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

  @Autowired
  private PostRepository repository;

  /**
   * Select All information from Post database.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * 
   */
  @GetMapping("/all")
  public ResponseEntity<List<PostModel>> getAll() {
    return ResponseEntity.ok(repository.findAll());
  }

  /**
   * Select a single post containing the id that was send.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param idPost
   */
  @GetMapping("/{idPost}")
  public ResponseEntity<PostModel> getById(@PathVariable long idPost) {
    return repository.findById(idPost).map(response -> ResponseEntity.ok(response))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

  /**
   * Get all information based on the name of the title.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param title
   */
  @GetMapping("/{title}")
  public ResponseEntity<List<PostModel>> getByTitle(@PathVariable String title) {
    return ResponseEntity.ok(repository.findAllByTitleContainingIgnoreCase(title));
  }

  /**
   * Save information on database.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param newPost
   */
  @PostMapping
  public ResponseEntity<PostModel> savePost(@Valid @RequestBody PostModel newPost) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(repository.save(newPost));
  }

  /**
   * Update a information on database.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param updatePost
   */
  @PutMapping
  public ResponseEntity<PostModel> updatePost(@Valid @RequestBody PostModel updatePost) {
    return repository.findById(updatePost.getIdPost())
        .map(response -> ResponseEntity.status(HttpStatus.OK).body(repository.save(updatePost)))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

  /**
   * Delete the information based on ID that is informed.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param idPost
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{idPost}")
  public void deletePost(@PathVariable long idPost) {
    Optional<PostModel> post = repository.findById(idPost);

    if (post.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    repository.deleteById(idPost);

  }
}
