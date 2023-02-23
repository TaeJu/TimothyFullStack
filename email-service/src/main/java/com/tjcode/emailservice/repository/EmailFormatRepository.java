package com.tjcode.emailservice.repository;

import com.tjcode.emailservice.entity.EmailFormat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailFormatRepository extends JpaRepository<EmailFormat, Integer> {
}
