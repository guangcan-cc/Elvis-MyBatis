package cc.blog.proxy;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyMapperProxy<T> implements InvocationHandler {

    private Class<T> mapperInterface;
    private SqlSession sqlSession;

    public MyMapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {

        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 针对不同的sql类型，需要调用sqlSession不同的方法

        // 返回值也有多种情况，这里不做处理，直接返回
        return sqlSession.<T>selectList(mapperInterface.getCanonicalName() + "." + method.getName());
    }
}
