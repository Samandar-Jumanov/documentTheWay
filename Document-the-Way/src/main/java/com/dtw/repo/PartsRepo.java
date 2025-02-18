package com.dtw.repo;

import com.dtw.entity.Parts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepo extends JpaRepository<Parts, Long > {
}
