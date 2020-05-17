package com.it.frame.mapper.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it.frame.po.test.TestPO;
import org.mapstruct.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<TestPO> {
}
