package com.example.library.management.system.repo;

import com.example.library.management.system.entity.OtpRequest;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.enums.UsageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRequestRepo extends JpaRepository<OtpRequest,Integer> {

    Optional<OtpRequest> findTopByOtpAndUserOrderByIdDesc(Integer otp, User user);

    Optional<OtpRequest> findByResetTokenAndUsageStatus(String resetToken,UsageStatus usageStatus);
}
