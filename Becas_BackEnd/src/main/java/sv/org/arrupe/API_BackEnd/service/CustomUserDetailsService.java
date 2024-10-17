package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.repository.UsuarioRepository;
import sv.org.arrupe.API_BackEnd.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String carnet) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCarnet(carnet)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con carnet: " + carnet));

        return new UserPrincipal(usuario);
    }
}