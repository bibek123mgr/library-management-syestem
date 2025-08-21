package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.Config.AppLogger;
import com.example.library.management.system.constants.APIConstant;
import com.example.library.management.system.dtos.LoginUserDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.UserDtos;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.repo.UserRepo;
import com.example.library.management.system.service.AuthService;
import com.example.library.management.system.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppLogger logger;

    @Override
    public ResponseEntity<ResponseDtos<Void>> signIn(LoginUserDtos loginData) {
       try{
           String accessToken="";
           String refreshToken="";
           return ResponseUtils.getResponse(true,APIConstant.LOGIN_SUCCESSFULLY,accessToken,refreshToken, HttpStatus.OK);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }



    @Override
    public ResponseEntity<ResponseDtos<Void>> registerUser(UserDtos registerData) {
        try{
            User user=mapUserRegisterData(registerData);
            userRepo.save(user);
            return ResponseUtils.getResponse(true,APIConstant.CREATED,HttpStatus.CREATED);
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
            String accessToken="";
            String newRefreshToken="";
            return  ResponseUtils.getResponse(true,APIConstant.LOGIN_SUCCESSFULLY,accessToken,newRefreshToken,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
