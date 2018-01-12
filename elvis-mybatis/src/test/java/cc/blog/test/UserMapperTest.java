package cc.blog.test;

import cc.blog.mapper.UserMapper;
import cc.blog.model.Country;
import cc.blog.model.SysUser;
import cc.blog.proxy.MyMapperProxy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserMapperTest extends BaseMapperTest{

    @Test
    public void testSelectAllUserAndRolesSelect() {

        SysUser user1 = null;
        SysUser user2 = null;
        SysUser user3 = null;
        SysUser user4 = null;

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user1 = userMapper.selectAllUserAndRolesSelect(1L);

//            System.out.println(user.getRoles().get(0).getRoleName());
//            System.out.println(user.getRoles().get(0).getPrivilegeList().get(0).getPrivilegeName());
            user2 = userMapper.selectAllUserAndRolesSelect(1L);
            System.out.println("同一个SqlSession：" + (user1 == user2));
        }

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            user3 = userMapper.selectAllUserAndRolesSelect(1L);

            user4 = userMapper.selectAllUserAndRolesSelect(1L);

            System.out.println("不同一个SqlSession：" + (user1 == user3));
            System.out.println(user3 == user4);
        }
    }

    @Test
    public void testSelectAllUserAndRoles() {

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


            List<SysUser> users = userMapper.selectAllUserAndRoles();

            printUserList(users);
        }
    }

    @Test
    public void selectselectUserAndRoleByIdForResultMap() {

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


            List<SysUser> users = userMapper.selectUserAndRoleByIdForResultMap(1L, "xxxx");

            printUserList(users);
        }
    }

    @Test
    public void selectUserAndRoleById() {

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


            List<SysUser> users = userMapper.selectUserAndRoleById(1L);

            printUserList(users);
        }
    }

    @Test
    public void testUpdateByMap() {

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            userMapper.updateByMap(new HashMap<String, Object>(){{
                put("id",1L);
                put("user_email","test@qq.com");
                put("user_password","123");
            }});

            SysUser user = userMapper.selectById(1L);

            System.out.println(user);

        }
    }

    @Test
    public void testinsertList() {

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysUser> userList = new ArrayList<>();

            for(int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("8023");
                user.setUserEmail("guangcan_@@163.com");
                userList.add(user);
            }

            userMapper.insertList(userList);

            printUserList(userList);

        }
    }

    @Test
    public void testSelectByIds() {

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//            List<SysUser> users = userMapper.selectByIdList(new ArrayList<Long>(){{
//                add(1L);
//                add(1001L);
//                add(1008L);
//            }});

            List<SysUser> users = userMapper.selectByIdArray(new Long[]{1L, 1001L, 1008L});

            printUserList(users);

        }
    }

    @Test
    public void testSelectByUser() {

        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser query = new SysUser();

            query.setUserName("el");

//            query.setUserEmail("guangcan_cc@163.com");
            List<SysUser> users = userMapper.selectByUser(query);

            printUserList(users);

        }
    }

    @Test
    public void testMapperProxy() {
        try(SqlSession sqlSession = getSqlSession()) {

            MyMapperProxy<UserMapper> userMyMapperProxy = new MyMapperProxy<>(UserMapper.class, sqlSession);

            UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class[]{UserMapper.class},
                    userMyMapperProxy);

            List<SysUser> users = userMapper.selectAll();


            for (SysUser user : users) {
                System.out.println(user);
            }

        }
    }

    @Test
    public void testDeleteById() {
        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


            userMapper.deleteById(1007L);

            sqlSession.commit();

        }
    }

    @Test
    public void testUpdateById() {
        try(SqlSession sqlSession = getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//            SysUser user = userMapper.selectById(1007L);

            SysUser user = new SysUser();
            user.setId(1007L);

            user.setUserName("Carlos");

            userMapper.updateById(user);

            sqlSession.commit();

        }
    }

    @Test
    public void testInsertBySelectKey() {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();

            user.setUserName("elvis");
            user.setUserPassword("8023");
            user.setUserEmail("guangcan_cc@163.com ");
            user.setUserInfo("啦啦啦啦啦啦啦");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            int result = userMapper.insertBySelectKey(user);

            System.out.println(result);
            System.out.println(user.getId());


        }
    }

    @Test
    public void testInsertForKey() {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();

            user.setUserName("elvis");
            user.setUserPassword("8023");
            user.setUserEmail("guangcan_cc@163.com ");
            user.setUserInfo("啦啦啦啦啦啦啦");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            int result = userMapper.insertForKey(user);

            System.out.println(result);
            System.out.println(user.getId());


        }
    }


    @Test
    public void testInsert() {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();

            user.setUserName("elvis");
            user.setUserPassword("8023");
            user.setUserEmail("guangcan_cc@163.com ");
            user.setUserInfo("啦啦啦啦啦啦啦");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            int result = userMapper.insert(user);

            System.out.println(result);
            System.out.println(user.getId());


        }
    }

    @Test
    public void testSelectAll() {

        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysUser> userList = userMapper.selectAll();
            printUserList(userList);
        }

    }

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1L);
            System.out.println(user);
        }
    }

    private void printUserList(List<SysUser> userList) {
        for(SysUser user : userList) {
            System.out.println(user);

        }
    }

}
