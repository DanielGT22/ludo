package danielGrujic.ludo.security;
import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.exceptions.UnauthorizedException;
import danielGrujic.ludo.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAuthfilter extends OncePerRequestFilter {
    @Autowired
    private JWTTtools jwtTools;
    @Autowired
    private UserService userService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException , IOException {
        String checkRequestToken=request.getHeader("Authorization");
      if(checkRequestToken==null){
            throw  new UnauthorizedException("token non presente");
        }else{
            String accessToken= checkRequestToken.substring(7);
            jwtTools.verifyToken(accessToken);


            String id = jwtTools.extractIdFromToken(accessToken);
            User user= userService.findByUUID(UUID.fromString(id));


            Authentication authentication= new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request,response);

        }
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/login", request.getServletPath()) ||
        new AntPathMatcher().match("/auth/register", request.getServletPath());
    }


}
