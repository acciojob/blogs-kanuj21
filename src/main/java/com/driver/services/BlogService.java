package com.driver.services;
import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        // Create a blog at the current time
        User user = userRepository1.findById(userId).orElse(null);
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPubDate(new Date());
        blog.setUser(user);
        blogRepository1.save(blog);
        user.getBlogList().add(blog);
        userRepository1.save(user);
        return blog;
    }

    public void deleteBlog(int blogId) {
        // Delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}