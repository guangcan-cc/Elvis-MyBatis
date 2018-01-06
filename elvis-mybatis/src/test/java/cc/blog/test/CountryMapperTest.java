package cc.blog.test;

import cc.blog.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CountryMapperTest extends BaseMapperTest{

    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            List<Country> countryList = sqlSession.selectList("cc.blog.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        }
    }

    private void printCountryList(List<Country> countryList) {
        for(Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());

        }
    }

}
