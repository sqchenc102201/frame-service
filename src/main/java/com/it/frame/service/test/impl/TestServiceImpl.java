package com.it.frame.service.test.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.it.frame.common.util.RedisUtil;
import com.it.frame.mapper.test.TestMapper;
import com.it.frame.po.test.TestPO;
import com.it.frame.service.test.TestService;
import com.it.frame.vo.test.TestVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<TestVO> queryTestUserList(TestVO param) {
        List<TestPO> testList = (List<TestPO>) redisTemplate.opsForValue().get("testList");
        List<TestPO> list;
        if (null == testList) {
            list = testMapper.selectList(null);
            redisTemplate.opsForValue().set("testList", list);
        }
        List<TestPO> userInfos = new LambdaQueryChainWrapper<>(testMapper)
                .like(TestPO::getName, param.getName())
                .list();
        if (null == userInfos) {
            return new ArrayList<>();
        }
        return userInfos.stream().map(x ->{
            TestVO test = new TestVO();
            BeanUtils.copyProperties(x, test);
            return test;
        }).collect(Collectors.toList());
    }
}
