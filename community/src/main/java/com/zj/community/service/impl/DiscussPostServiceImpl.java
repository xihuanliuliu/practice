package com.zj.community.service.impl;

import com.zj.community.entity.DiscussPost;
import com.zj.community.mapper.DiscussPostMapper;
import com.zj.community.service.DiscussPostService;
import com.zj.community.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    private static final Logger logger = LoggerFactory.getLogger(DiscussPostServiceImpl.class);

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Override
    public List<DiscussPost> queryDiscussPosts(Long userId, Page page) {
        return discussPostMapper.queryDiscussPosts(userId, page.getOffset(), page.getLimit());
    }

    @Override
    public int selectDiscussPostRows(Long userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
