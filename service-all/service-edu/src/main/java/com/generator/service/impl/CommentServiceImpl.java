package com.generator.service.impl;

import com.generator.domain.Comment;
import com.generator.mapper.CommentMapper;
import com.generator.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author asus
 * @since 2021-11-17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
