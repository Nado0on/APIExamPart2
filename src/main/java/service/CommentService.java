package service;

import domain.Comment;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;


    // create comment
    public Comment createComment(Comment comment) {

        return commentRepository.save(comment);
    }

    //Retrieve comment by user id
    public Comment getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        return comment;
    }


    //Get all comments
    public List<Comment> getAllComments() {
        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()) {
            commentList.add(comment);
        }
        return commentList;
    }


    //Get comment by specific post id
    public List<Comment> getCommentsByPostId(Long postId) {
        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()) {
            if (comment.getPostId() != null && comment.getPostId().equals(postId)) {
                commentList.add(comment);
            }
        }
        return commentList;
    }


    //Update comment
    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = getCommentById(id);
        comment.setContent(commentDetails.getContent());
        return commentRepository.save(comment);
    }


    //Delete a comment
    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }
}