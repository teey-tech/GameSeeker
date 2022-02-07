package com.gameseeker.app.services;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import com.gameseeker.app.dtos.UserCredentialsDTO;
import com.gameseeker.app.dtos.UserLoginDTO;
import com.gameseeker.app.models.UserModel;
import com.gameseeker.app.repositories.UserRepository;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Class responsible for the user services..
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see UsuarioModel
 */
@Service
public class UserService {

  private UserCredentialsDTO credentialsDTO;

  @Autowired
  private UserRepository repository;

  /**
   * Private static method, used to encrypt with BCryptPasswordEncoder format a
   * string passed as a parameter.
   * 
   * @param password, String format.
   * @return String
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @see BCryptPasswordEncoder
   * 
   */
  private static String encryptPassword(String password) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String passwordEncoder = encoder.encode(password);
    return passwordEncoder;
  }

  /**
   * Private static method, used to generate basic token.
   * 
   * @param email,    String format.
   * @param password, String format.
   * @return String
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @see Base64
   * 
   */
  private static String generatorBasicToken(String email, String password) {
    String structure = email + ":" + password;
    byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
    return "Basic " + new String(structureBase64);
  }

  /**
   * Public method used to register a user in the system's database. This method
   * returns a BAD_REQUEST if the intention to register already has an email
   * registered in the system, to avoid duplication. If you don't hear an existing
   * email in the system, it returns CREATED status with user object no response.
   * 
   * @param newUser, UserRegistrationDTO object.
   * @return ResponseEntity<UserModel>
   * @author Thiago Batista
   * @since 07/02/2022
   * 
   */

  public ResponseEntity<UserModel> registerNewUser(UserModel newUser) {
    Optional<UserModel> optional = repository.findByEmail(newUser.getEmail());

    if (optional.isEmpty()) {
      newUser.setPassword(encryptPassword(newUser.getPassword()));
      return ResponseEntity.status(201).body(repository.save(newUser));
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email alreay in use!");
    }
  }

  /**
   * Public method used to update a user in the system's database. This method
   * returns a BAD_REQUEST if the user tries to update a diferent user
   * registered in the system, to avoid errors.
   * 
   * @author Thiago Batista
   * @since 07/02/2022
   * @version 1.0
   * @param updateUser
   * @return ResponseEntity<UsuarioModel>
   * 
   */
  public Optional<UserModel> updateUser(UserModel updateUser) {
    if (repository.findById(updateUser.getIdUser()).isPresent()) {
      Optional<UserModel> findUser = repository.findByEmail(updateUser.getEmail());
      if (findUser.isPresent()) {
        if (findUser.get().getIdUser() != updateUser.getIdUser()) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!", null);
        }
      }
      updateUser.setPassword(encryptPassword(updateUser.getPassword()));
      return Optional.of(repository.save(updateUser));
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!", null);

  }

  /**
   * Public method to get user credentials for authentications.
   * 
   * @param dto
   * @return ResponseEntity<UserCredentialsDTO>
   * @since 07/02/2022
   * @version 1.0
   * 
   */
  public ResponseEntity<UserCredentialsDTO> credentials(@Valid UserLoginDTO dto) {
    return repository.findByEmail(dto.getEmail()).map(resp -> {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

      if (encoder.matches(dto.getPassword(), resp.getPassword())) {
        credentialsDTO = new UserCredentialsDTO(
            generatorBasicToken(dto.getEmail(), dto.getPassword()),
            resp.getIdUser(),
            resp.getEmail());
        return ResponseEntity.status(200).body(credentialsDTO);
      } else {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password is typed wrong");
      }
    }).orElseThrow(() -> {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inform a correct Email or Password");
    });
  }
}
