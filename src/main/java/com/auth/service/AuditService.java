package com.auth.service;


import com.auth.entity.AuditLog;
import com.auth.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;



@Service
@RequiredArgsConstructor
public class AuditService {



    private final AuditLogRepository repository;



    public void saveLog(
            String username,
            String action,
            String ip){



        AuditLog log =
                AuditLog.builder()

                .username(username)

                .action(action)

                .ipAddress(ip)

                .timestamp(
                        LocalDateTime.now()
                )

                .build();



        repository.save(log);

    }

}