package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IPostService;
import service.impl.PostService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("post", postService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("post", postService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Post post) {
        post.setCreateAt(LocalDateTime.now());
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        Optional<Post> post = postService.findById(id);
        modelAndView.addObject("pos", post);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/delete");
        Post post = postService.findById(id).get();
        modelAndView.addObject("pos", post);
        return modelAndView;

    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.remove(id);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView showFindForm (@RequestParam String title) {
        ModelAndView modelAndView =new ModelAndView("/post/search");
        modelAndView.addObject("post", postService.findByTitle(title));
        return modelAndView;
    }

    @GetMapping("/topLikes")
    public ModelAndView showTopLike ( ) {
        ModelAndView modelAndView =new ModelAndView("/post/topLike");
        modelAndView.addObject("post", postService.findTrendLikes());
        return modelAndView;
    }

    @GetMapping("/topTime")
    public ModelAndView showTopTime( ) {
        ModelAndView modelAndView =new ModelAndView("/post/topTime");
        modelAndView.addObject("post", postService.findTopByTime());
        return modelAndView;
    }



}