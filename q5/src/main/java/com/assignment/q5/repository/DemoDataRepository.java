package com.assignment.q5.repository;

import com.assignment.q5.model.DemoData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoDataRepository extends JpaRepository<DemoData, Long> {
}
