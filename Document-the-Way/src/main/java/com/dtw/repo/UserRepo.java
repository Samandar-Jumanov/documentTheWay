package com.dtw.repo;

import com.dtw.entity.RepostedDocument;
import com.dtw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepo extends JpaRepository<User,Long> {
  Optional < User > findByUsername (String username);
  Stream<User> findByReposts(RepostedDocument repost);
}
