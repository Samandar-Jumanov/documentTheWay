package com.dtw.repo;


import com.dtw.entity.RepostedDocument;
import com.dtw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface RepostedDocumentRepo extends JpaRepository<RepostedDocument , Long> {
    List<RepostedDocument> findByUser(User user);
}
