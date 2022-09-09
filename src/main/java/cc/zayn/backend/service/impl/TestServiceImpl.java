package cc.zayn.backend.service.impl;

import cc.zayn.backend.dao.TestDao;
import cc.zayn.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private final TestDao testDao;

    @Autowired
    public TestServiceImpl(TestDao testDao){
        this.testDao = testDao;
    }

    public boolean test(String id){
        return testDao.test()==10;
    }

}
