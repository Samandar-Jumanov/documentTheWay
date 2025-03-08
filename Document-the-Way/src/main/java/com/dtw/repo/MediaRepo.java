package com.dtw.repo;

import com.dtw.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepo  extends JpaRepository<Media , Long > {
}
