package Utils;

import Dao.ActivityAttendDao;
import Dao.ActivityDetailDao;
import Dao.ActivityTableDao;
import Dao.Impl.ActivityAttendDaoImpl;
import Dao.Impl.ActivityDetailDaoImpl;
import Dao.Impl.ActivityTableDaoImpl;

public class DaoFactory {                       //接口工厂
    private static DaoFactory daoFactory;
    static {
        daoFactory = new DaoFactory();
    }
    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        return daoFactory;
    }
    public ActivityAttendDao getActivityAttendDao(){
        return new ActivityAttendDaoImpl();
    }
    public ActivityDetailDao getActivityDetailDao(){
        return new ActivityDetailDaoImpl();
    }
    public ActivityTableDao getActivityTableDao(){
        return new ActivityTableDaoImpl();
    }

}
