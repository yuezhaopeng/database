package Service;

import Dao.Impl.LoginDetailDAOImpl;
import Dao.LoginDetailDAO;
import Entity.LoginDetail;
import Service.AssistantServiceHelp.ManagerService;
import Service.AssistantServiceHelp.StudentService;
import Service.AssistantServiceHelp.TeacherService;
import Utils.DAOFactory;

public class AssistantService {
    /*此为助教系统入口*/
    public static void service(String userid,int roleNum){
        if(roleNum==1){
            /*培养管理员*/
            new ManagerService(userid);
        }else if(roleNum==2 || roleNum==4){
            /*学科负责人*/
            System.out.println("您没有该模块的权限！");
        }else if(roleNum==3){
            /*授课教师*/
            new TeacherService(userid);
        }else if(roleNum==5){
            /*研究生*/
            new StudentService(userid);
        }
    }
}
