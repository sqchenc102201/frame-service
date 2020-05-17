package com.it.frame.service.test;

import com.it.frame.vo.test.TestVO;

import java.util.List;

public interface TestService {
    List<TestVO> queryTestUserList(TestVO param);
}
