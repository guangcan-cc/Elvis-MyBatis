package cc.blog.test;


import cc.blog.mapper.FileMapper;
import cc.blog.model.UpdateSub;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class FileMapperTest extends BaseMapperTest{

    @Test
    public void testGetFile(){

        try(SqlSession sqlSession = getSqlSession()) {

            FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);

            UpdateSub updateSub = fileMapper.getFile("F:\\\\1\\\\A");

            System.out.println(updateSub);

        }
    }

}
