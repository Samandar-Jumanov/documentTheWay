package com.dtw.repo;

import com.dtw.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface FeedbackRepo extends JpaRepository<Feedback , Long > {
    Stream<Feedback> findBySolutionIsNotNull();
}
