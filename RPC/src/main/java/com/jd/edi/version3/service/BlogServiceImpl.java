package com.jd.edi.version3.service;

import com.jd.edi.version3.common.Blog;
import com.jd.edi.version3.service.BlogService;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlog(String name) {
        return new Blog(name, "博客");
    }

    @Override
    public void addBlog(Blog blog) {
        System.out.println("add blog: " + blog.toString());
    }
}
