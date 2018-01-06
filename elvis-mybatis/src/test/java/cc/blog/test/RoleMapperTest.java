package cc.blog.test;

import cc.blog.mapper.RoleMapper;
import cc.blog.mapper.UserMapper;
import cc.blog.model.SysRole;
import cc.blog.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import javax.management.relation.Role;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest{

    @Test
    public void testInsertForSelectKey() {

        try(SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role = roleMapper.selectByIdForResult(1L);

            System.out.println(role.getId());

            roleMapper.insertForSelectKey(role);

            System.out.println(role.getId());

            System.out.println(role);
        }
    }

    @Test
    public void testInsert() {

        try(SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role = roleMapper.selectByIdForResult(1L);

            System.out.println(role.getId());

            roleMapper.insert(role);

            System.out.println(role.getId());

            System.out.println(role);
        }
    }

    @Test
    public void testSelectByIdForResult() {

        try(SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role = roleMapper.selectByIdForResult(1L);

            System.out.println(role);
        }
    }

    @Test
    public void testSelectById() {

        try(SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role = roleMapper.selectById(1L);

            System.out.println(role);
        }
    }

    @Test
    public void selectRolesByUserIdAndRoleEnabled() {

        try(SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            List<SysRole> roles = roleMapper.selectRolesByUserIdAndRoleEnabled(1L,1);

            for(SysRole role : roles) {

                System.out.println(role);

            }

        }
    }

    @Test
    public void selectRolesByUserid() {

        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            List<SysRole> roles = roleMapper.selectRolesByUserid(1L);

            System.out.println(roles.get(0));

        }

    }

}
