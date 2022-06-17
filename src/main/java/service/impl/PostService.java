package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PostRepository;
import service.IPostService;

import java.util.Optional;
  @Service
public class PostService implements IPostService {
   @Autowired
     PostRepository postRepository;
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }


      @Override
    public void save(Post post) {
          postRepository.save(post);

    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);

    }
}
