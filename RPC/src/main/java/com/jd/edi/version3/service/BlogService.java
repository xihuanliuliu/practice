package com.jd.edi.version3.service;


import com.jd.edi.version3.common.Blog;

public interface BlogService {
    Blog getBlog(String name);

    void addBlog(Blog blog);

}
