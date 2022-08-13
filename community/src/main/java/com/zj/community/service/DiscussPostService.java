package com.zj.community.service;

import com.zj.community.entity.DiscussPost;
import com.zj.community.utils.Page;

import java.util.List;

public interface DiscussPostService {

    List<DiscussPost> queryDiscussPosts(Long userId, Page page);

    int selectDiscussPostRows(Long userId);
}
