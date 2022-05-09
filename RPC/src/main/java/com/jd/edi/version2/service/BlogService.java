package com.jd.edi.version2.service;


import com.jd.edi.version2.common.Blog;

public interface BlogService {
    Blog getBlog(String name);

    void addBlog(Blog blog);

}
