package controller;

import domain.Comment;
import domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CommentService;
import service.UserService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;



    //Logger: Used for debugging, monitoring, auditing, and
    // error diagnosis by logging key actions and events within the application.
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CommentController.class);


//ResponseEntity: Represents the entire HTTP response, allowing for precise control over status codes,
// headers, and body content. It enhances the flexibility of the response sent back to the client.

    //This method logs the creation of a user and then calls the createUser method of UserService.
    // The response is wrapped in a ResponseEntity with a status of 200 OK and the created user as the body.
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        logger.info("Creating comment");
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }
   // This method logs the fetching of a user by ID and then calls the getUserById method of UserService.
   // The response is wrapped in a ResponseEntity with a status of 200 OK and the fetched user as the body.
   @GetMapping("/{id}")
   public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
       logger.info("Fetching comment by id: " + id);
       Comment comment = commentService.getCommentById(id);
       return ResponseEntity.ok(comment);
   }

    //This method logs the fetching of all users and then calls the getAllUsers method of UserService.
    // The response is wrapped in a ResponseEntity with a status of 200 OK and the list of users as the body.
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        logger.info("Fetching all comments");
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
// This method logs the updating of a user by ID and then calls the updateUser method of UserService.
// The response is wrapped in a ResponseEntity with a status of 200 OK and the updated user as the body.
@GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
    logger.info("Fetching comments by post id: " + postId);
    List<Comment> comments = commentService.getCommentsByPostId(postId);
    return ResponseEntity.ok(comments);
}
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        logger.info("Updating comment with id: " + id);
        Comment updatedComment = commentService.updateComment(id, commentDetails);
        return ResponseEntity.ok(updatedComment);
    }


    //   This method logs the deletion of a user by ID and then calls the deleteUser method of UserService.
//   The response is wrapped in a ResponseEntity with a status of 204 No Content and no body.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        logger.info("Deleting comment with id: " + id);
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}

