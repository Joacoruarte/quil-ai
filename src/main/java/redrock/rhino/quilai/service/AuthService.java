package redrock.rhino.quilai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redrock.rhino.quilai.dto.LoginRequestDTO;
import redrock.rhino.quilai.dto.RegisterRequestDTO;
import redrock.rhino.quilai.exception.UsernameAlreadyExistsException;
import redrock.rhino.quilai.model.User;
import redrock.rhino.quilai.model.UserRole;
import redrock.rhino.quilai.security.JwtUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public void register (RegisterRequestDTO request) {
        if (userService.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(request.getUsername());
        }

        User toSave = new User();
        toSave.setUsername(request.getUsername());
        toSave.setPassword(passwordEncoder.encode(request.getPassword()));
        toSave.setEmail(request.getEmail());
        toSave.setRoles(Set.of(UserRole.USER));

        userService.save(toSave);
    }

    public String login (LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> claims = Map.of("roles", roles);

        return jwtUtil.generateAccessToken(userDetails.getUsername(), claims);
    }
}
