package com.example.library.management.system.repo;

import com.example.library.management.system.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedRecordRepo extends JpaRepository<IssueRecord,Integer> {
}
