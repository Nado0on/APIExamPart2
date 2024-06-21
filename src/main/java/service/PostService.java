package service;

import domain.Post;
import domain.User;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ResourceNotFoundException("Post " + id + " not found");
        }
        return post;
    }

    public List<Post> getAllPosts() {
        List<Post> postList = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            postList.add(post);
        }
        return postList;
    }

    public List<Post> getPostsByTitle(String title) {
        List<Post> postList = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            if (post.getTitle() != null && post.getTitle().equalsIgnoreCase(title)) {
                postList.add(post);
            }
        }
        return postList;
    }

    public List<Post> getPostsByUserId(Long userId) {
        List<Post> postList = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            if (post.getUserID() != null && post.getUserID().equals(userId)) {
                postList.add(post);
            }
        }
        return postList;
    }


    public Post updatePost(Long id, Post postDetails) {
        Post post = getPostById(id);
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        return postRepository.save(post);
    }


    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }


}
