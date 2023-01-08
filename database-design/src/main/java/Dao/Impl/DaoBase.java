package Dao.Impl;

import Dao.Dao;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DaoBase implements Dao {
    @Override
    public Connection getConnection() {
        Connection con = null;
        try{
        Properties properties = new Properties();
        InputStream inputStream = DaoBase.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(inputStream);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        con = dataSource.getConnection();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return con;
    }
}
