package com.gameseeker.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gameseeker.app.dtos.UserLoginDTO;
import com.gameseeker.app.models.UserModel;
import com.gameseeker.app.repositories.UserRepository;
import com.gameseeker.app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creating Controller Usuario.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @since 07/02/2022
 * @see UserService
 * @see UserModel
 * @see UserRepository
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserRepository repository;

  @Autowired
  private UserService services;

  /**
   * Get all user informations stored on data base.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @see UsuariosModel
   */
  @GetMapping("/all")
  public ResponseEntity<List<UserModel>> getAll() {
    return ResponseEntity.ok(repository.findAll());
  }

  /**
   * Get a user based on the id.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @see UserModel
   * @param idUser
   */
  @GetMapping("/{idUser}")
  public ResponseEntity<UserModel> findById(@PathVariable Long idUser) {
    return repository.findById(idUser).map(response -> ResponseEntity.ok(response))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  /**
   * Create a new user on database.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @see UserModel
   * @see UserService
   * @param newUser
   */
  @PostMapping("/register")
  public ResponseEntity<UserModel> save(@Valid @RequestBody UserModel newUser) {
    return services.registerNewUser(newUser);
  }

  /**
   * Verify user identification and login into the system.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @see UserService
   * @see UserModel
   * @param auth
   */
  @PostMapping("/auth")
  public ResponseEntity<UserLoginDTO> auth(@RequestBody Optional<UserLoginDTO> auth) {
    return services.logIn(auth)
        .map(resp -> ResponseEntity.ok(resp))
        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
  }

  /**
   * Update user on database.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @see UserModel
   * @see UserService
   * @param updateUser
   */
  @PutMapping("/update")
  public ResponseEntity<UserModel> update(@Valid @RequestBody UserModel updateUser) {
    return services.updateUser(updateUser)
        .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }
}
