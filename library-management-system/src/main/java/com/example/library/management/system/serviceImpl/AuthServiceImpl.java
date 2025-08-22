package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.Config.AppLogger;
import com.example.library.management.system.constants.APIConstant;
import com.example.library.management.system.dtos.LoginUserDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.UserDtos;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.entity.UserPrincipal;
import com.example.library.management.system.jwt.JwtUtils;
import com.example.library.management.system.repo.UserRepo;
import com.example.library.management.system.service.AuthService;
import com.example.library.management.system.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;



    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<ResponseDtos<Void>> signIn(LoginUserDtos loginData) {
       try{
           Authentication authentication= authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(loginData.getEmail(),loginData.getPassword())
           );
           if(authentication.isAuthenticated()){
               UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal();
               String username= userPrincipal.getUsername();
               String roles=userPrincipal.getAuthorities().iterator().next().getAuthority();
               String accessToken= jwtUtils.generateTokenAccessToken(username,roles);
               String refreshToken= jwtUtils.generateRefreshToken(username,roles);
               return ResponseUtils.getResponse(true,APIConstant.LOGIN_SUCCESSFULLY,accessToken,refreshToken, HttpStatus.OK);
           }else {
               return ResponseUtils.getResponse(true,"Invalid Credentials", HttpStatus.BAD_REQUEST);
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> registerUser(UserDtos registerData) {
        try{
            User existingUser=userRepo.findByEmail(registerData.getEmail());
            if(existingUser == null) {
                User user = mapUserRegisterData(registerData);
                userRepo.save(user);
                return ResponseUtils.getResponse(true, APIConstant.CREATED, HttpStatus.CREATED);
            }else{
                return ResponseUtils.getResponse(false, APIConstant.USER_ALREADY_EXIST, HttpStatus.CREATED);

            }
        } catch (Exception e) {
            AppLogger.error(APIConstant.INTERNAL_SERVER_ERROR,e);
            throw new RuntimeException(e);
        }
    }

    private User mapUserRegisterData(UserDtos registerData){
        User newUser=new User();
        newUser.setName(registerData.getName());
        newUser.setEmail(registerData.getEmail());
        newUser.setPassword(registerData.getPassword());
        newUser.setRoles(registerData.getRoles());
        return newUser;
    }


    @Override
    public ResponseEntity<ResponseDtos<Void>> refreshToken(String refreshToken) {
        try{
            if(!jwtUtils.isTokenExpired(refreshToken)){
                String username= jwtUtils.extractUsername(refreshToken);
                String roles= jwtUtils.extractRole(refreshToken);
                String accessToken= jwtUtils.generateTokenAccessToken(username,roles);
                return ResponseUtils.getResponse(true,APIConstant.LOGIN_SUCCESSFULLY,accessToken,refreshToken,HttpStatus.OK);
            }else{
                return ResponseUtils.getResponse(false,"Invalid Refresh Token ",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
