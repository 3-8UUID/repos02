package com.cssl.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static String applicationContext = "com/cssl/config/applicationContext.xml";
    private static ClassPathXmlApplicationContext context = null;
    private static SqlSessionFactoryBean sqlSessionFactoryBean;
    private static SqlSessionFactory factory = null;
    private static ThreadLocal<SqlSession> threadLocal =
            new ThreadLocal<SqlSession>();

    static {
        String resource = "com/cssl/config/mybatis-config.xml";
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        context = new ClassPathXmlApplicationContext(applicationContext);
        sqlSessionFactoryBean = context.getBean(SqlSessionFactoryBean.class);
    }

    public static SqlSessionFactoryBean getSqlSessionFactoryBean() {
        return sqlSessionFactoryBean;
    }

    public static SqlSessionFactory getFactory() {
        return factory;
    }

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = factory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    public static void closeSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.set(null);
        }
    }
}
