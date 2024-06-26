package controller;
import domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

@Autowired
    private PostService postService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        logger.info("Now creating post");
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        logger.info("Retrieving post by id: " + id);
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        logger.info("Fetching all posts");
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Post>> getPostsByTitle(@PathVariable String title) {
        logger.info("Fetching posts by title: " + title);
        List<Post> posts = postService.getPostsByTitle(title);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        logger.info("Fetching posts by user id: " + userId);
        List<Post> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        logger.info("Updating post with id: " + id);
        Post updatedPost = postService.updatePost(id, postDetails);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        logger.info("Deleting post with id: " + id);
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
