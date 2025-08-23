package com.example.library.management.system.entity;

import com.example.library.management.system.enums.UsageStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;



@Entity
@Data
@Table(name = "otp_request_tbl")
public class OtpRequest {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String resetToken;

    private Integer otp;

    private LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    private UsageStatus usageStatus;
}

