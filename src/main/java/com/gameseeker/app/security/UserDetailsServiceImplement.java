package com.gameseeker.app.security;

import java.util.Optional;

import com.gameseeker.app.models.UserModel;
import com.gameseeker.app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class responsible for service loadUserByUsername.
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see UserDetailsService
 * @see UserRepository
 * @see UserModel
 * @see UserDetailsImplement
 */
@Service
public class UserDetailsServiceImplement implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserModel> optional = repository.findByEmail(username);

    if (optional.isPresent()) {
      return new UserDetailsImplements(optional.get());
    } else {
      throw new UsernameNotFoundException("User does not exist");
    }
  }

}
