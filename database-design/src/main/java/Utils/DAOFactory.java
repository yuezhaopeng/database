package Utils;

import Dao.*;
import Dao.Impl.*;

public class DAOFactory {                       //接口工厂
    private static DAOFactory daoFactory;
    static {
        daoFactory = new DAOFactory();
    }
    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
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

    public LoginCheckDAO getLoginCheckDAO() {
        return new LoginCheckDAOImpl();
    }

    public LoginDetailDAO getLoginDetailDAO() {
        return new LoginDetailDAOImpl();
    }

    public LeaderDAO getLeaderDAO() {
        return new LeaderDAOImpl();
    }

    public TeacherDao getTeacherDao() {
        return new TeacherDaoImpl();
    }

    public MentorDao getMentorDao() {
        return new MentorDaoImpl();
    }

    public StudentDao getStudentDao() {
        return new StudentDaoImpl();
    }
}
