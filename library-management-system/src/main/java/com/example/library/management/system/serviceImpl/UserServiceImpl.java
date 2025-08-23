package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.Config.AppLogger;
import com.example.library.management.system.constants.APIConstant;
import com.example.library.management.system.dtos.ResetPasswordRequestDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.ValidateTokenRequestDtos;
import com.example.library.management.system.entity.OtpRequest;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.enums.UsageStatus;
import com.example.library.management.system.repo.OtpRequestRepo;
import com.example.library.management.system.repo.UserRepo;
import com.example.library.management.system.service.UserService;
import com.example.library.management.system.utils.EmailUtils;
import com.example.library.management.system.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private OtpRequestRepo otpRequestRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Value("${otp.expiry.minutes:5}")
    private int expiryMinutes;

    @Override
    public ResponseEntity<ResponseDtos<Void>> resetPassword(ResetPasswordRequestDtos resetPasswordRequestDtos) {
       try{
           String resetToken =resetPasswordRequestDtos.getResetToken();
           String password=resetPasswordRequestDtos.getNewPassword();
           Optional<OtpRequest> otpRequestOpt= otpRequestRepo.findByResetTokenAndUsageStatus(resetToken,UsageStatus.UNUSED);
           if(otpRequestOpt.isEmpty()){
               return ResponseUtils.getResponse(false, APIConstant.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
           }else {
               OtpRequest otpRequest=otpRequestOpt.get();
               LocalDateTime currentDateTimeWithExpiryMinutesPlus=LocalDateTime.now().plusMinutes(expiryMinutes);
               if(!otpRequest.getExpiredAt().isAfter(currentDateTimeWithExpiryMinutesPlus)){
                   return ResponseUtils.getResponse(true, "OTP has been Expired", HttpStatus.OK);
               }else {
                   User user=otpRequest.getUser();
                   user.setPassword(encoder.encode(password));
                   otpRequest.setUsageStatus(UsageStatus.USED);
                   userRepo.save(user);
                   otpRequestRepo.save(otpRequest);
                   return ResponseUtils.getResponse(true, "Password Successfully Changed",resetToken, HttpStatus.OK);
               }
           }
       } catch (Exception e) {
           AppLogger.error(APIConstant.INTERNAL_SERVER_ERROR,e);
           return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> verifyOTP(ValidateTokenRequestDtos validateTokenRequestData) {
        try{
            String username = validateTokenRequestData.getEmail();
            Integer otp= validateTokenRequestData.getOtp();
            User user=userRepo.findByEmail(username);
            if(user != null) {
                Optional<OtpRequest> otpRequestOpt= otpRequestRepo.findTopByOtpAndUserOrderByIdDesc(otp,user);
                if(otpRequestOpt.isEmpty()){
                    return ResponseUtils.getResponse(false, APIConstant.INVALID_OTP, HttpStatus.BAD_REQUEST);
                }else {
                    OtpRequest otpRequest=otpRequestOpt.get();
                    LocalDateTime currentDateTime=LocalDateTime.now();
                    if(!otpRequest.getExpiredAt().isAfter(currentDateTime)){
                        return ResponseUtils.getResponse(true, "OTP has been Expired", HttpStatus.OK);
                    }else {
                        String resetToken=otpRequest.getResetToken();
                        return ResponseUtils.getResponse(true, "Successfully Validate OTP",resetToken, HttpStatus.OK);
                    }
                }
            }else{
                return ResponseUtils.getResponse(false, APIConstant.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            AppLogger.error(APIConstant.INTERNAL_SERVER_ERROR,e);
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> forgetPasswordRequest(String email) {
        try{
            User user=userRepo.findByEmail(email);
            if(user != null){
                String resetToken= UUID.randomUUID().toString();
                Random random=new Random();
                int otp= random.nextInt(900000) + 100000;
                String resetLink = "http://localhost:3000/reset-password?token=" + resetToken;

                String subject = "Password Reset Request";
                String message = "Hi " + user.getName() + ",\n\n"
                        + "We received a request to reset your password.\n\n"
                        + "👉 You can reset it in either of these ways:\n"
                        + "1. **Click the link below (valid for "+ expiryMinutes +" minutes):**\n"
                        + resetLink + "\n\n"
                        + "2. **Or use this OTP (valid for "+ expiryMinutes +" minutes):** " + otp + "\n\n"
                        + "If you didn’t request this, please ignore this email.";

                OtpRequest otpRequest=mapDataToOtpRequest(user,resetToken,otp);
                otpRequestRepo.save(otpRequest);
                emailUtils.sendMail(message,subject,email);
                return ResponseUtils.getResponse(true,"Successfully Send OTP", HttpStatus.OK);
            }else{
                return ResponseUtils.getResponse(false, APIConstant.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            AppLogger.error(APIConstant.INTERNAL_SERVER_ERROR,e);
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private OtpRequest mapDataToOtpRequest(User user,String resetToken,Integer otp){
        OtpRequest otpRequest=new OtpRequest();
        otpRequest.setUser(user);
        otpRequest.setResetToken(resetToken);
        otpRequest.setOtp(otp);
        otpRequest.setUsageStatus(UsageStatus.UNUSED);
        otpRequest.setExpiredAt(LocalDateTime.now().plusMinutes(expiryMinutes));
        return otpRequest;
    }

}
