package com.example.sociallo.security.user;

import com.example.sociallo.model.User;
import com.example.sociallo.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.example.sociallo.constants.Messages.USER_BY_USERNAME_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username).orElseThrow(
      () -> new ResponseStatusException(NOT_FOUND, String.format(USER_BY_USERNAME_NOT_FOUND, username)));
    return new UserDetailsImpl(user);
  }
}
