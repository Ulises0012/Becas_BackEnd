package sv.org.arrupe.API_BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.org.arrupe.API_BackEnd.model.Usuario;
import sv.org.arrupe.API_BackEnd.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String carnet) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCarnet(carnet)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con carnet: " + carnet));
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        // Convertir el rol a minúsculas para la comparación
        String rolLowerCase = usuario.getRol().toLowerCase();
        
        if (rolLowerCase.equals("estudiante")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"));
        } else if (rolLowerCase.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            // Si el rol no es reconocido, puedes manejarlo como prefieras
            // Por ejemplo, asignar un rol por defecto o lanzar una excepción
            authorities.add(new SimpleGrantedAuthority("ROLE_UNKNOWN"));
        }
        
        return new org.springframework.security.core.userdetails.User(
                usuario.getCarnet(),
                usuario.getPassword(),
                authorities);
    }
}