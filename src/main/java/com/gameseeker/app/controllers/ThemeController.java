package com.gameseeker.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gameseeker.app.models.ThemeModel;
import com.gameseeker.app.repositories.ThemeRepository;

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
 * Creating ThemeController.
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see ThemeModel
 * @see ThemeRepository
 */
@RestController
@RequestMapping("/themes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {

  @Autowired
  private ThemeRepository repository;

  /**
   * Select All information from the database Theme.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * 
   */
  @GetMapping("/all")
  public ResponseEntity<List<ThemeModel>> getAll() {
    return ResponseEntity.ok(repository.findAll());
  }

  /**
   * Select a single information from the database containing the ID.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param idTheme
   */
  @GetMapping("/{idTheme}")
  public ResponseEntity<ThemeModel> findById(@PathVariable long idTheme) {
    return repository.findById(idTheme).map(response -> ResponseEntity.ok(response))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

  /**
   * Get all information based on the name of the theme.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param themeName
   */
  @GetMapping("theme/{themeName}")
  public ResponseEntity<List<ThemeModel>> getByThemeName(@PathVariable String themeName) {
    return ResponseEntity.ok(repository.findAllByThemeNameContainingIgnoreCase(themeName));
  }

  /**
   * Save information on database.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param newTheme
   */
  @PostMapping("/register")
  public ResponseEntity<ThemeModel> saveTheme(@Valid @RequestBody ThemeModel newTheme) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(repository.save(newTheme));
  }

  /**
   * Update a information on database.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param updateTheme
   */
  @PutMapping("update")
  public ResponseEntity<ThemeModel> updateTheme(@Valid @RequestBody ThemeModel updateTheme) {
    return repository.findById(updateTheme.getIdTheme())
        .map(response -> ResponseEntity.status(HttpStatus.OK).body(repository.save(updateTheme)))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

  /**
   * Delete the information based on ID that is informed.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param id
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/delete/{idTheme}")
  public void deleteTheme(@PathVariable long idTheme) {
    Optional<ThemeModel> theme = repository.findById(idTheme);

    if (theme.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    repository.deleteById(idTheme);
  }

}
