package com.tang.zhen.film;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.zhen.film.dao.entity.NextUser;
import com.tang.zhen.film.dao.mapper.NextUserMapper;
import com.tang.zhen.film.example.dao.UserMapper;
import com.tang.zhen.film.example.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class NextFilmApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NextUserMapper nextUserMapper;

	@Test
	void contextLoads() {
	}

	@Test
    public  void hellowWorld(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);

    }

    @Test
    public  void generateTest(){
        List<NextUser> list = nextUserMapper.selectList(null);
        list.forEach(System.out::println);

    }

    @Test
    public  void addUser(){
        NextUser nextUser = new NextUser();
        nextUser.setUserName("tang1");
        nextUser.setUserPwd("love Learning");
        int insert = nextUserMapper.insert(nextUser);
        System.out.println(insert);

    }

    @Test
    public  void updateUser(){
        NextUser nextUser = new NextUser();
        nextUser.setUuid(6);
        nextUser.setUserName("tang1");
        nextUser.setUserPwd("love Learning 2 3");
       // int insert = nextUserMapper.updateById(nextUser);
        AbstractWrapper abstractWrapper = new UpdateWrapper();
        abstractWrapper.eq("user_name","tang1");
        int insert = nextUserMapper.update(nextUser,abstractWrapper);
        System.out.println(insert);

    }

    @Test
    public  void deleteUser(){
	    Integer id = 6;
        int insert = nextUserMapper.deleteById(id);
        System.out.println(insert==1);

    }

    @Test
    public  void queryById(){
        Integer id = 5;
        NextUser insert = nextUserMapper.selectById(id);
        System.out.println(insert);
    }

    @Test
    public  void queryByOurs(){
        List<NextUser> insert = nextUserMapper.getUser();
        insert.forEach(System.out::println);
    }

    @Test
    public  void pageTest(){
        IPage<NextUser> iPage = new Page<>();
        iPage.setCurrent(1);
        iPage.setSize(2);
        IPage<NextUser > nextUserIPage = nextUserMapper.selectPage(iPage,null);
        System.out.println(nextUserIPage);
    }
}
