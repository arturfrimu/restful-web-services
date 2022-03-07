package com.rest.webservices.restfulwebservices.repository;

import com.rest.webservices.restfulwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
