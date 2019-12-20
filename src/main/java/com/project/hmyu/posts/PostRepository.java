package com.project.hmyu.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findByPostId(int postId);
    Page<Post> findAll(Pageable pageable);
    Page<Post> findByStatus(Enum status, Pageable pageable);
}
