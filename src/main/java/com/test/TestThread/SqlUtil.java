package com.test.TestThread;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 8/16/2018.
 */
public class SqlUtil {
    private static Logger logger = LoggerFactory.getLogger(SqlUtil.class);
    private static final SessionFactory sessionFactory;
    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable e) {
            logger.info("初始化sessionFactory失败！");
            throw new ExceptionInInitializerError(e);
        }
    }
    public static final ThreadLocal session = new ThreadLocal();
    public static Session currentSession() throws HibernateException {
        Session s = (Session)session.get();
        if(s==null){
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }
    public static void closeSession() throws HibernateException{
        Session s = (Session)session.get();
        session.set(null);
        if(s!=null){
            s.close();
        }
    }
}



