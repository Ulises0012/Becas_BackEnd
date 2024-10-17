package sv.org.arrupe.API_BackEnd.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sv.org.arrupe.API_BackEnd.model.Usuario;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private String carnet;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Usuario usuario) {
        this.carnet = usuario.getCarnet();
        this.password = usuario.getPassword();
        // Convertir el enum Rol a String usando name()
        this.authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name())
        );
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
        return carnet;
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

    public String getCarnet() {
        return carnet;
    }
}