package com.example.library.management.system.jwt;

import com.example.library.management.system.Config.CustomUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if("OPTIONS".equalsIgnoreCase(request.getMethod())       || uri.startsWith("/swagger-ui")
                || uri.startsWith("/v3/api-docs")
                || uri.startsWith("/swagger-resources")
                || uri.startsWith("/webjars/") ){
            filterChain.doFilter(request,response);
        }else{
            String authHeader= request.getHeader("Authorization");
            String token="";
            String username= "";
            if(authHeader != "" && authHeader.startsWith("Bearer ")){
                token=authHeader.substring(7);
                try{
                    username=jwtUtils.extractUsername(token);
                    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
                        if(jwtUtils.validateToken(token,userDetails)){
                            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }else {
                            sendErrorResponse(response,HttpServletResponse.SC_UNAUTHORIZED,"invalid or token expire");
                            return;
                        }
                    }
                } catch (ExpiredJwtException e) {
                    sendErrorResponse(response,HttpServletResponse.SC_UNAUTHORIZED,"invalid or token expire");
                    return;
                } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
                    sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                    return;
                }catch (Exception e){
                    sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                }
            }
        }
    }

    private void sendErrorResponse(HttpServletResponse response,int status,String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        String origin=response.getHeader("Origin");
        if("http://localhost:3000".equals(origin)){
            response.setHeader("Access-Control-Allow-Origin",origin);
        }else {
            response.setHeader("Access-Control-Allow-Origin",origin);
        }
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,PATCH,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers","Authorization,Content-type,Accept");
        boolean resStatus=false;
        String json="{\"status\":\"" + resStatus + "\",\"message\":\"" + message + "\"}";
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
