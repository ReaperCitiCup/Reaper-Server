package reaper.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerRepositoryTest {
    @Autowired
    ManagerRepository managerRepository;

    @Test
    public void findByManagerId() throws Exception {
        System.out.println(managerRepository.findByManagerId("1").getName());
    }

}