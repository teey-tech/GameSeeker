package com.gameseeker.app.security;

import java.util.Collection;
import java.util.List;

import com.gameseeker.app.models.UserModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Class responsible for charge user details in security spring
 * 
 * @author Thiago Batista
 * @since 07/02/2022
 * @version 1.0
 * @see UserDetails
 * @see UserModel
 * @see UserDetailsImplements
 * @see UserDetailsServiceImplement
 */
public class UserDetailsImplements implements UserDetails {

  private static final long serialVersionUID = 1l;
  private String email;
  private String password;
  private List<GrantedAuthority> authorities;

  public UserDetailsImplements() {
    super();
  }

  public UserDetailsImplements(UserModel user) {
    this.email = user.getEmail();
    this.password = user.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
