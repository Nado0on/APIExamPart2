package service;

import domain.Comment;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {

        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        return comment;
    }



















}
