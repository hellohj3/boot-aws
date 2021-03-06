package org.example.bootaws.web;

import lombok.RequiredArgsConstructor;
import org.example.bootaws.config.oauth.LoginUser;
import org.example.bootaws.config.oauth.dto.SessionUser;
import org.example.bootaws.sevice.posts.PostsService;
import org.example.bootaws.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto findPostsDto = postsService.findById(id);
        model.addAttribute("posts", findPostsDto);

        return "posts-update";
    }
}
