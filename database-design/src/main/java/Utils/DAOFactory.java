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

    public MentorDAO getMentorDao() {
        return new MentorDAOImpl();
    }

    public StudentDao getStudentDao() {
        return new StudentDaoImpl();
    }

    public PatentDAO getPatentBase(){
        return new PatentDAOBase();
    }

    public ProveDAO getProveBase(){
        return new ProveDAOBase();
    }

    public ReportDAO getReportBase(){ return new ReportDAOBase(); }

    public TextbookDAO getTextbookBase(){
        return new TextbookDAOBase();
    }

    public ThesisDao getThesisDAO(){
        return new ThesisDaoImpl();
    }

    public RewardDao getRewardDAO(){
        return new RewardDaoImpl();
    }

    public StandardDao getStandardDAO(){
        return new StandardDaoImpl();
    }

    public ProjectMentorDAO getProjectMentorDAO() {
        return new ProjectMentorDAOImpl();
    }

    public ProjectDistributeDAO getProjectDistributeDAO() {
        return new ProjectDistributeDAOImpl();
    }

}
