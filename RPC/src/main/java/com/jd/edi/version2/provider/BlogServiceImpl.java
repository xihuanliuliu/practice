package com.jd.edi.version2.provider;

import com.jd.edi.version2.common.Blog;
import com.jd.edi.version2.service.BlogService;

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
