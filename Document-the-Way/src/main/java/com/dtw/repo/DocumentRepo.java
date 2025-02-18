package com.dtw.repo;

import com.dtw.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document, Long > {
}
