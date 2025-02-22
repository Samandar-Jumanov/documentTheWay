package com.dtw.repo;

import com.dtw.entity.Document;
import com.dtw.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartsRepo extends JpaRepository<Part, Long > {

    List<Part> findByDocument(Document document);
}
