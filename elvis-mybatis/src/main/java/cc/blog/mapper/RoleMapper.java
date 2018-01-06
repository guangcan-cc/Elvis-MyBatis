package cc.blog.mapper;

import cc.blog.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {

    List<SysRole> selectRolesByUserid(Long userId);

    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    @Select({"select id,role_name,enabled,create_by,create_time from sys_role where id = #{id}"})
    SysRole selectById(Long id);

    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time"),
    })
    @Select({"select id,role_name,enabled,create_by,create_time from sys_role where id = #{id}"})
    SysRole selectByIdForResult(Long id);

    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time) " +
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime})"})
    @Options(useGeneratedKeys = true)
    int insert(SysRole role);

    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time) " +
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertForSelectKey(SysRole role);

}















