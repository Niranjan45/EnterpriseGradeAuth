package com.auth.controller;


import com.auth.entity.AuditLog;

import com.auth.repository.AuditLogRepository;


import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/admin/audit")
@RequiredArgsConstructor
public class AuditController {



private final AuditLogRepository repository;



@GetMapping
public List<AuditLog> logs(){


return repository.findAll();


}


}