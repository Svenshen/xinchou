package tech.wetech.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.wetech.admin.modules.system.mapper.TestDao;
import tech.wetech.admin.modules.system.po.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WetechAdminApplicationTests {

        @Autowired
        TestDao td;
	@Test
	public void contextLoads() {
            System.out.println(td.findAll());
		//EntityHelper.getOrderByClause(User.class);
	}

}
