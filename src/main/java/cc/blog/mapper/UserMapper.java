package cc.blog.mapper;

import cc.blog.model.SysUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysUser> selectByRowBounds(RowBounds rowBounds);

    int insert(SysUser user);

    int insertForKey(SysUser user);

    int insertBySelectKey(SysUser user);

    int updateById(SysUser user);

    int deleteById(Long id);

    List<SysUser> selectByUser(SysUser user);

    int updateByIdSelective(SysUser user);

    List<SysUser> selectByIdList(List<Long> idList);

    List<SysUser> selectByIdArray(Long[] idArray);

    int insertList(List<SysUser> userList);

    int updateByMap(Map<String, Object> map);

    List<SysUser> selectUserAndRoleById(Long id);

    List<SysUser> selectUserAndRoleByIdForResultMap(Long id);

    List<SysUser> selectUserAndRoleByIdForResultMap(@Param("id") Long id, String userName);

    List<SysUser> selectAllUserAndRoles();

    SysUser selectAllUserAndRolesSelect(@Param("id") Long id);
}
