package com.rest.webservices.restfulwebservices.controller;

import com.rest.webservices.restfulwebservices.entity.Post;
import com.rest.webservices.restfulwebservices.exceptions.PostNotFoundException;
import com.rest.webservices.restfulwebservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PostJpaController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/posts")
    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping(path = "/jpa/posts/{id}")
    public EntityModel<Post> retrievePostById(@PathVariable("id") int id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) throw new PostNotFoundException("id-" + id);

        EntityModel<Post> model = EntityModel.of(post.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllPosts());
        model.add(linkToUsers.withRel("all-posts"));
        return model;
    }

    @DeleteMapping(path = "/jpa/posts/{id}")
    public void deletePostById(@PathVariable("id") int id) {
        postRepository.deleteById(id);
    }
}
