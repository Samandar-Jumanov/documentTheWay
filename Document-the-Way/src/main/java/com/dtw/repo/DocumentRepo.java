package com.dtw.repo;

import com.dtw.entity.Document;
import com.dtw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepo extends JpaRepository<Document, Long > {
    List<Document> findByUser(User currentUser);
}
