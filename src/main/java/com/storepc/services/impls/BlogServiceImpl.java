package com.storepc.services.impls;

import com.storepc.models.Blog;
import com.storepc.payloads.BlogDto;
import com.storepc.repositories.AccountRepository;
import com.storepc.repositories.BlogRepository;
import com.storepc.services.BlogService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public Blog getBlog(Long blogId) {
        return null;
    }

    @Override
    public Result addBlog(BlogDto blogDto) {
        return null;
    }

    @Override
    public Result updateBlog(Long blogId, BlogDto blogDto) {
        return null;
    }

    @Override
    public Result deleteBlog(Long blogId) {
        return null;
    }
}