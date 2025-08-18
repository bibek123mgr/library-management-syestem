package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.Config.AppLogger;
import com.example.library.management.system.constants.APIConstant;
import com.example.library.management.system.dtos.LoginUserDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.UserDtos;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.repo.UserRepo;
import com.example.library.management.system.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppLogger logger;

    @Override
    public ResponseDtos<Void> signIn(LoginUserDtos loginData) {
       try{
           String accessToken="";
           String refreshToken="";
           return new ResponseDtos<>(true,APIConstant.LOGIN_SUCCESSFULLY,accessToken,refreshToken);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }



    @Override
    public ResponseDtos<Void> registerUser(UserDtos registerData) {
        try{
            User user=mapUserRegisterData(registerData);
            userRepo.save(user);
            return new ResponseDtos<>(true,APIConstant.CREATED);
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
    public ResponseDtos<Void> refreshToken(String refreshToken) {
        try{
            String accessToken="";
            String newRefreshToken="";
            return new ResponseDtos<>(true,APIConstant.LOGIN_SUCCESSFULLY,accessToken,newRefreshToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
