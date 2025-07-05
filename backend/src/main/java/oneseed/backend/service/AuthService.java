package oneseed.backend.service;

import oneseed.backend.dto.LoginDto;
import oneseed.backend.dto.RegisterDto;
import oneseed.backend.model.User;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    public User getCurrentUser();
}