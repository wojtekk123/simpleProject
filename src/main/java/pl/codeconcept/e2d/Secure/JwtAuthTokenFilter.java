package pl.codeconcept.e2d.Secure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.codeconcept.e2d.service.UserDetailServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {

            String jwt = getJwt(httpServletRequest);
            if (jwt!=null){
                 String username = jwtToken.getUsernameFromJwtToken(jwt);
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }catch (Exception e){
            logger.error("Can not set user authentication");
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }


    private String getJwt (HttpServletRequest request){

        String header = request.getHeader("Authorization");
        if (header!=null && header.startsWith("Bearer ")){
            return header.replace("Bearer","");
        }
        return null;
    }


    protected void doFilterInternal1(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {

            String jwt = getJwt(httpServletRequest);
            if (jwt!=null){
                Jws<Claims> claimsJws = jwtToken.getUsernameFromJwtToken1(jwt);
                String usersname = claimsJws.getBody().get("username").toString();
                String role = claimsJws.getBody().get("role").toString();
                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));


                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usersname,null,simpleGrantedAuthorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }catch (Exception e){
            logger.error("Can not set user authentication");
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

}
