package sv.org.arrupe.API_BackEnd.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import sv.org.arrupe.API_BackEnd.service.CustomUserDetailsService;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private Environment env;
    
    private final Set<String> invalidatedTokens = ConcurrentHashMap.newKeySet();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Si estamos en perfil de prueba, saltamos la autenticación
            if (env.getActiveProfiles().length > 0 && "test".equals(env.getActiveProfiles()[0])) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = getJwtFromRequest(request);
            
            if (StringUtils.hasText(jwt) && invalidatedTokens.contains(jwt)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\": \"Token has been invalidated\"}");
                return;
            }
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String carnet = tokenProvider.getCarnetFromJWT(jwt);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(carnet);
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("No se pudo establecer la autenticación de usuario en el contexto de seguridad", ex);
            SecurityContextHolder.clearContext();
        }
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void invalidateToken(String token) {
        if (token != null) {
            invalidatedTokens.add(token);
        }
    }

    public boolean isTokenInvalidated(String token) {
        return invalidatedTokens.contains(token);
    }
}