package com.Ashutosh.ReportGenerator.Repositry;

import com.Ashutosh.ReportGenerator.Entity.CBC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CBCRepo extends JpaRepository<CBC,Long> {
    CBC findByUserInfo_Id(Long userId);
}
