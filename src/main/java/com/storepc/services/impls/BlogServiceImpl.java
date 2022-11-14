package com.storepc.services.impls;

import com.storepc.models.Account;
import com.storepc.models.Blog;
import com.storepc.payloads.BlogDto;
import com.storepc.repositories.AccountRepository;
import com.storepc.repositories.BlogRepository;
import com.storepc.services.BlogService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository,
                           AccountRepository accountRepository) {
        this.blogRepository = blogRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlog(Long blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }

    @Override
    public Result addBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setText(blogDto.getText());
        Optional<Account> optionalAccount = accountRepository.findById(blogDto.getAccountId());
        if (optionalAccount.isPresent()) {
            blog.setAccount(optionalAccount.get());
            blogRepository.save(blog);
            return new Result("Blog saved!", true, HttpStatus.CREATED);
        }
        return new Result("Account not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result updateBlog(Long blogId, BlogDto blogDto) {
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();
            blog.setTitle(blogDto.getTitle());
            blog.setText(blogDto.getText());
            Optional<Account> optionalAccount = accountRepository.findById(blogDto.getAccountId());
            if (optionalAccount.isPresent()) {
                blog.setAccount(optionalAccount.get());
                blogRepository.save(blog);
                return new Result("Blog updated!", true, HttpStatus.ACCEPTED);
            }
            return new Result("Account not found!", false, HttpStatus.NOT_FOUND);
        }
        return new Result("Blog not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteBlog(Long blogId) {
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        if (optionalBlog.isPresent()) {
            blogRepository.deleteById(blogId);
            return new Result("Blog deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Blog not found!", false, HttpStatus.NOT_FOUND);
    }
}