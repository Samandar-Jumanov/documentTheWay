package com.dtw.repo;

import com.dtw.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepo extends JpaRepository<Contents , Long > {
}
