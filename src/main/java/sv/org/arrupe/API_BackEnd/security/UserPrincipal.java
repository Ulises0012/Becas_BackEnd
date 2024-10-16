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

    public UserPrincipal(String carnet, String password, Collection<? extends GrantedAuthority> authorities) {
        this.carnet = carnet;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario user) {
        return new UserPrincipal(
                user.getCarnet(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRol().toUpperCase()))
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

    public String getCarnet() {
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
}