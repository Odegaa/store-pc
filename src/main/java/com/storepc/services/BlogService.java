package com.storepc.services;

import com.storepc.models.Blog;
import com.storepc.payloads.BlogDto;
import com.storepc.templates.Result;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    Blog getBlog(Long blogId);

    Result addBlog(BlogDto blogDto);

    Result updateBlog(Long blogId, BlogDto blogDto);

    Result deleteBlog(Long blogId);

}
