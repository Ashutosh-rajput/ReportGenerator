package com.Ashutosh.ReportGenerator.Repositry;

import com.Ashutosh.ReportGenerator.Entity.CBC;
import com.Ashutosh.ReportGenerator.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepo extends JpaRepository<Result,Long> {
    Optional<Result> findByUserInfo_IdAndName(Long userId, String name);
    Result findByUserInfo_Id(Long userId);
}
