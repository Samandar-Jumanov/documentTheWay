package com.dtw.repo;

import com.dtw.entity.Document;
import com.dtw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface DocumentRepo extends JpaRepository<Document, Long > {
    Stream<Document> findByUser(User currentUser);
}
