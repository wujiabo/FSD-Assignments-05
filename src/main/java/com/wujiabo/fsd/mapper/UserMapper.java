package com.wujiabo.fsd.mapper;

import com.wujiabo.fsd.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from sysuser")
    public List<SysUser> findAll();

    @Select("SELECT * FROM sysuser WHERE username = #{username}")
    public SysUser findByUsername(String username);

    @Delete("DELETE FROM sysuser WHERE username = #{username}")
    public int deleteByUsername(String username);

    @Insert("INSERT INTO sysuser(username, password,name,email) VALUES (#{username}, #{password}, #{name}, #{email})")
    public int insert(SysUser sysUser);

    @Update("Update sysuser set name=#{name}, email=#{email}, password=#{password} where username=#{username}")
    public int update(SysUser sysUser);
}
