package Service;

import Entity.ActivityAttend;
import Entity.ActivityDetail;
import Entity.ActivityTable;
import Utils.DaoFactory;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ActivityService {

    public static void acticityMenu(String userid, int role){
        Scanner sc = new Scanner(System.in);
        switch (role) {
            case 1:
                //研究生管理员
                adminMenu(userid);
                break;
            case 2:
                //学科负责人
                leaderMenu(userid);
                break;
            case 3:
                //授课教师
                System.out.println("授课教师无权限操作该部分！");
                break;
            case 4:
                //导师
                mentorMenu(userid);
                break;
            case 5:
                //研究生
                studentMenu(userid);
                break;
        }
    }
    private static void adminMenu(String userid){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请选择功能");
            System.out.println("1.查看所有的学术交流参与信息");
            System.out.println("2.查看已通过审核的统计表");
            System.out.println("3.导出已通过审核的统计表");
            System.out.println("0.退出本系统");
            String choose = sc.nextLine().trim();
            switch (choose) {
                case "1": {
                    attendDetailShowForAdmin();
                    break;
                }
                case "2": {
                    activityTableShowForAdmin();
                    break;
                }
                case"3":{
                    out();
                    break;
                }
                case "0": {
                    return;
                }
                default:{
                    System.out.println("----------");
                    System.out.println("请正确输入");
                    System.out.println("----------");
                }
            }
        }
    }
    private static void leaderMenu(String userid) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("学科负责人-请选择功能");
            System.out.println("1.查看本学科所有学生的统计表");
            System.out.println("2.审批统计表");
            System.out.println("0.退出本系统");
            String choose = sc.nextLine().trim();
            switch (choose) {
                case "1": {
                    activityTableShowForL(userid);
                    break;
                }
                case "2": {
                    activityTableLaudit();
                    break;
                }
                case "0": {
                    return;
                }
                default:{
                    System.out.println("----------");
                    System.out.println("请正确输入");
                    System.out.println("----------");
                }
            }
        }
    }

    private static void mentorMenu(String userid) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("导师-请选择功能");
            System.out.println("1.插入学术交流基础信息");
            System.out.println("2.查看许可的学术交流基础信息");
            System.out.println("3.查看需要审批的交流参与信息");
            System.out.println("4.查看审批通过的交流参与信息");
            System.out.println("5.审批学生的学术交流参与信息（请先查看需要审批的参与信息）");
            System.out.println("6.查看名下学生所有统计表");
            System.out.println("7.审批统计表（请先查看统计表信息）");

            System.out.println("0.退出本系统");
            String choose = sc.nextLine().trim();
            switch (choose) {
                case "1": {
                    attendDetailInsert(userid);
                    break;
                }
                case "2": {
                    attendDetailShowForMen(userid);
                    break;
                }
                case "3": {
                    activityAttendShowForMenAudit(userid);
                    break;
                }
                case "4": {
                    activityAttendShowForMenYes(userid);
                    break;
                }case "5": {
                    activityAttendAudit();
                    break;
                }case "6": {
                    activityTableShowForM(userid);
                    break;
                } case "7": {
                    activityTableMaudit();
                    break;
                }
                case "0": {
                    return;
                }
                default:{
                    System.out.println("----------");
                    System.out.println("请正确输入");
                    System.out.println("----------");
                }
            }
        }
    }

    private static void studentMenu(String userid) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("学生-请选择功能");
            System.out.println("1.查看可以报名的学术交流基础信息");
            System.out.println("2.填写学术交流参与证明(请先查看获取序号)");
            System.out.println("3.删除对应的学术交流参与证明(请先查看获取序号)");
            System.out.println("4.查看自己未通过的学术交流参与证明");
            System.out.println("5.查看自己已通过的学术交流参与证明");
            System.out.println("6.撤销自己的学术交流参与证明申请(请先查看获取序号)");
            System.out.println("7.修改学术交流参与信息并重新提交(请先查看获取序号)");
            System.out.println("8.选择两次学术交流审批，导出统计表(请先查看获取序号)");
            System.out.println("9.删除本人申请表(删除后可再次申请)");
            System.out.println("0.退出本系统");
            String choose = sc.nextLine().trim();
            switch (choose) {
                case "1": {
                    attendDetailShowForStu(userid);
                    break;
                }
                case "2": {
                    activityAttendInsert(userid);
                    break;
                }
                case "3": {
                    activityAttendDelete(userid);
                    break;
                }
                case "4": {
                    activityAttendShowForStuNo(userid);
                    break;
                }
                case "5": {
                    activityAttendShowForStuYes(userid);
                    break;
                }
                case "6": {
                    activityAttendWithdrow(userid);
                    break;
                }
                case "7": {
                    activityAttendResubmit(userid);
                    break;
                }
                case "8": {
                    activityTableInsert(userid);
                    break;
                }
                case "9": {
                    activityTableDelete(userid);
                    break;
                }
                case "0": {
                    return;
                }
                default:{
                    System.out.println("----------");
                    System.out.println("请正确输入");
                    System.out.println("----------");
                }
            }
        }
    }
    public static void attendDetailShowForStu(String Sno) {
        List<ActivityDetail> ActivityDetails = DaoFactory.getInstance().getActivityDetailDao().showForStu(Sno);
        System.out.println("共 " + ActivityDetails.size() + " 条记录");
        for (ActivityDetail activityDetail : ActivityDetails) {
            System.out.print("活动序号：" + activityDetail.getActNo() + "\t");
            System.out.print("活动名称：" + activityDetail.getActName() + "\t");
            System.out.print("活动地址：" + activityDetail.getActAdd() + "\t");
            System.out.print("活动日期：" + activityDetail.getActDate());
            System.out.println();
            System.out.println("-----------------------------------");
        }
    }

    public static void activityAttendInsert(String Sno) {
        Scanner sc = new Scanner(System.in);

        System.out.println("填写申请学术交流参与信息");
        System.out.println("请输入参与活动序号");
        String actNo = sc.nextLine();
        System.out.println("请输入报告名称");
        String actReportName = sc.nextLine();
        System.out.println("请输入参会图片地址");
        String actPic = sc.nextLine();
        System.out.println("请输入申请备注");
        String sps = sc.nextLine();
        ActivityAttend activityAttend = new ActivityAttend();
        activityAttend.setSno(Sno);
        activityAttend.setActNo(actNo);
        activityAttend.setActReportName(actReportName);
        activityAttend.setActPic(actPic);
        activityAttend.setMaudit("0");
        activityAttend.setSps(sps);
        activityAttend.setMps("无");
        boolean check = DaoFactory.getInstance().getActivityAttendDao().insert(activityAttend);
        if (check) {
            System.out.println("申请成功");
        } else {
            System.out.println("申请失败，请检查信息是否合法，是否重复申请");
        }
    }

    public static void activityAttendDelete(String Sno) {
        Scanner sc = new Scanner(System.in);

        System.out.println("删除学术交流参与信息");
        System.out.println("请输入参与活动序号");
        String actNo = sc.nextLine();

        boolean check = DaoFactory.getInstance().getActivityAttendDao().delete(Sno, actNo);
        if (check) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败，请检查信息是否合法，是否重复申请");
        }
    }

    public static void activityAttendWithdrow(String Sno) {
        Scanner sc = new Scanner(System.in);
        System.out.println("撤回对应的申请学术交流参与信息");
        System.out.println("请输入参与活动序号");
        String actNo = sc.nextLine();
        boolean check = DaoFactory.getInstance().getActivityAttendDao().withdraw(Sno, actNo);
        if (check) {
            System.out.println("撤销成功");
        } else {
            System.out.println("撤销失败，请检查信息是否合法，序号是否重复");
        }
    }

    public static void activityAttendResubmit(String Sno) {
        Scanner sc = new Scanner(System.in);
        System.out.println("修改被退回的学术交流参与信息审批并重新提交");
        System.out.println("请输入参与活动序号");
        String actNo = sc.nextLine();
        System.out.println("请输入报告名称");
        String actReportName = sc.nextLine();
        System.out.println("请输入参会图片地址");
        String actPic = sc.nextLine();
        System.out.println("请输入申请备注");
        String sps = sc.nextLine();
        ActivityAttend activityAttend = new ActivityAttend();
        activityAttend.setSno(Sno);
        activityAttend.setActNo(actNo);
        activityAttend.setActReportName(actReportName);
        activityAttend.setActPic(actPic);
        activityAttend.setMaudit("0");
        activityAttend.setSps(sps);
        activityAttend.setMps("无");

        boolean check = DaoFactory.getInstance().getActivityAttendDao().reSubmit(activityAttend);
        if (check) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败，请查看是否有对应活动");
        }
    }

    public static void activityAttendAudit() {
        Scanner sc = new Scanner(System.in);

        System.out.println("导师进行审核");
        System.out.println("请输入审核学生学号");
        String Sno = sc.nextLine().trim();
        System.out.println("请输入审核学生参与活动序号");
        String actNo = sc.nextLine().trim();
        System.out.println("请输入审核结果，1为同意，0和其他为不同意");
        String res = sc.nextLine().trim();
        boolean check;
        if (res.equals("1")) {
            check = DaoFactory.getInstance().getActivityAttendDao().audit(Sno, actNo, 1);
        } else {
            check = DaoFactory.getInstance().getActivityAttendDao().audit(Sno, actNo, -1);
        }
        if (check) {
            System.out.println("审核成功");
        } else {
            System.out.println("审核失败，请查看是否有对应学生和活动");
        }
    }

    public static void activityAttendShowForStuYes(String Sno) {
        System.out.println("学生查看申请成功记录");
        List<ActivityAttend> activityAttends = DaoFactory.getInstance().getActivityAttendDao().showForStuYes(Sno);
        for (ActivityAttend activityAttend : activityAttends) {
            System.out.println("共 " + activityAttends.size() + " 条记录");
            System.out.print("学生学号：" + activityAttend.getSno() + "\t");
            System.out.print("交流活动序号：" + activityAttend.getActNo() + "\t");
            System.out.print("交流报告名称：" + activityAttend.getActReportName() + "\t");
            System.out.print("参会图片地址：" + activityAttend.getActPic() + "\t");
            System.out.print("学生备注：" + activityAttend.getSps() + "\t");
            System.out.println();
            System.out.println("-------------------------------------");
        }
        System.out.println("展示完成");
    }

    public static void activityAttendShowForStuNo(String Sno) {
        System.out.println("学生查看申请失败记录");
        List<ActivityAttend> activityAttends = DaoFactory.getInstance().getActivityAttendDao().showForStuNo(Sno);
        System.out.println("共 " + activityAttends.size() + " 条记录");
        for (ActivityAttend activityAttend : activityAttends) {
            System.out.print("学生学号：" + activityAttend.getSno() + "\t");
            System.out.print("交流活动序号：" + activityAttend.getActNo() + "\t");
            System.out.print("交流报告名称：" + activityAttend.getActReportName() + "\t");
            System.out.print("参会图片地址：" + activityAttend.getActPic() + "\t");
            System.out.print("学生备注：" + activityAttend.getSps() + "\t");
            System.out.print("审核状态：" + "审核失败");
            System.out.println();
            System.out.println("-------------------------------------");
        }
        System.out.println("展示完成");
    }

    public static void activityAttendShowForMenAudit(String Mno) {
        System.out.println("导师查看需要审批的记录");
        List<ActivityAttend> activityAttends = DaoFactory.getInstance().getActivityAttendDao().showForMenAudit(Mno);
        System.out.println("共 " + activityAttends.size() + " 条记录");
        for (ActivityAttend activityAttend : activityAttends) {
            System.out.print("学生学号：" + activityAttend.getSno() + "\t");
            System.out.print("交流活动序号：" + activityAttend.getActNo() + "\t");
            System.out.print("交流报告名称：" + activityAttend.getActReportName() + "\t");
            System.out.print("参会图片地址：" + activityAttend.getActPic() + "\t");
            System.out.print("学生备注：" + activityAttend.getSps() + "\t");
            System.out.print("审核状态：" + "待审核");
            System.out.println();
            System.out.println("-------------------------------------");
        }
        System.out.println("展示完成");
    }

    public static void activityAttendShowForMenYes(String Mno) {
        System.out.println("导师查看通过记录表");
        List<ActivityAttend> activityAttends = DaoFactory.getInstance().getActivityAttendDao().showForMenYes(Mno);
        System.out.println("共 " + activityAttends.size() + " 条记录");
        for (ActivityAttend activityAttend : activityAttends) {
            System.out.print("学生学号：" + activityAttend.getSno() + "\t");
            System.out.print("交流活动序号：" + activityAttend.getActNo() + "\t");
            System.out.print("交流报告名称：" + activityAttend.getActReportName() + "\t");
            System.out.print("参会图片地址：" + activityAttend.getActPic() + "\t");
            System.out.print("学生备注：" + activityAttend.getSps() + "\t");
            System.out.print("审核状态：" + "审核成功");
            System.out.println();
            System.out.println("-------------------------------------");
        }
        System.out.println("展示完成");
    }

    public static void attendDetailInsert(String Mno) {
        Scanner sc = new Scanner(System.in);

        ActivityDetail activityDetail = new ActivityDetail();
        System.out.println("增加学术交流详细信息");
        System.out.println("请输入学术交流序号");
        String actNo = sc.nextLine();
        System.out.println("请输入学术交流名称");
        String actName = sc.nextLine();
        System.out.println("请输入学术交流地址");
        String actAdd = sc.nextLine();
        System.out.println("请输入学术交流日期，例如2020-11-11");
        String actDate = sc.nextLine();
        activityDetail.setMno(Mno);
        activityDetail.setActNo(actNo);
        activityDetail.setActName(actName);
        activityDetail.setActAdd(actAdd);
        activityDetail.setActDate(actDate);

        boolean check = DaoFactory.getInstance().getActivityDetailDao().insert(activityDetail);
        if (check) {
            System.out.println("增加成功");
        } else {
            System.out.println("增加失败，请检查信息是否合法，序号是否重复");
        }
    }

    public static void attendDetailShowForMen(String Mno) {
        List<ActivityDetail> activityDetails = DaoFactory.getInstance().getActivityDetailDao().showForMen(Mno);
        System.out.println("共 " + activityDetails.size() + " 条记录");
        for (ActivityDetail activityDetail : activityDetails) {
            System.out.print("活动序号：" + activityDetail.getActNo() + "\t");
            System.out.print("活动名称：" + activityDetail.getActName() + "\t");
            System.out.print("活动地址：" + activityDetail.getActAdd() + "\t");
            System.out.print("活动日期：" + activityDetail.getActDate());
            System.out.println();
            System.out.println("-----------------------------------");
        }
        System.out.println("展示完毕");
    }

    public static void attendDetailShowForAdmin() {
        List<ActivityDetail> activityDetails = DaoFactory.getInstance().getActivityDetailDao().showForAdmin();
        System.out.println("共 " + activityDetails.size() + " 条记录");
        for (ActivityDetail activityDetail : activityDetails) {
            System.out.print("活动序号：" + activityDetail.getActNo() + "\t");
            System.out.print("活动名称：" + activityDetail.getActName() + "\t");
            System.out.print("活动地址：" + activityDetail.getActAdd() + "\t");
            System.out.print("活动日期：" + activityDetail.getActDate() + "\t");
            System.out.print("导师序号：" + activityDetail.getMno() + "\t");
            System.out.println();
            System.out.println("-----------------------------------");
        }
        System.out.println("展示完毕");
    }

    public static void activityTableInsert(String Sno) {
        Scanner sc = new Scanner(System.in);

        System.out.println("填写学术交流统计表");
        System.out.println("请输入第一次学术交流序号");
        String actNo1 = sc.nextLine().trim();
        System.out.println("请输入第二次学术交流序号");
        String actNo2 = sc.nextLine().trim();
        //加robost
        if (!activityAttendCheck(Sno, actNo1, actNo2)) {
            System.out.println("没有对应的两个审批通过的交流记录");
            return;
        }
        ActivityTable activityTable = new ActivityTable();
        activityTable.setSno(Sno);
        activityTable.setActNoOne(actNo1);
        activityTable.setActNoTwo(actNo2);
        activityTable.setMaudit("0");
        activityTable.setLaudit("0");

        boolean check = DaoFactory.getInstance().getActivityTableDao().insert(activityTable);
        if (check) {
            System.out.println("增加成功");
        } else {
            System.out.println("增加失败，请检查信息是否合法，是否重复重复");
        }
    }

    public static void activityTableDelete(String Sno) {
        System.out.println("删除本人申请表");
        boolean check = DaoFactory.getInstance().getActivityTableDao().delete(Sno);
        if (check) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败，不存在本人申请记录");
        }
    }

    public static void activityTableMaudit() {
        Scanner sc = new Scanner(System.in);

        System.out.println("审批学术交流统计表");
        System.out.println("请输入审批学生学号");
        String Sno = sc.nextLine();
        System.out.println("请输入审核结果，1为同意，0和其他为不同意");
        String res = sc.nextLine();
        boolean check;
        if (Objects.equals(res, "1")) {
            check = DaoFactory.getInstance().getActivityTableDao().Maudit(Sno, 1);
        } else {
            check = DaoFactory.getInstance().getActivityTableDao().Maudit(Sno, -1);
        }
        if (check) {
            System.out.println("审核成功");
        } else {
            System.out.println("审核失败，请查看是否有对应学生");
        }
    }

    public static void activityTableLaudit() {
        Scanner sc = new Scanner(System.in);

        System.out.println("审批学术交流统计表");
        System.out.println("请输入审批学生学号");
        String Sno = sc.nextLine();
        System.out.println("请输入审核结果，1为同意，0和其他为不同意");
        String res = sc.nextLine();
        boolean check;
        if (Objects.equals(res, "1")) {
            check = DaoFactory.getInstance().getActivityTableDao().Laudit(Sno, 1);
        } else {
            check = DaoFactory.getInstance().getActivityTableDao().Laudit(Sno, -1);
        }
        if (check) {
            System.out.println("审核成功");
        } else {
            System.out.println("审核失败，请查看是否有对应学生");
        }
    }

    public static void activityTableShowForM(String Mno) {
        List<HashMap<String, Object>> activityTables = DaoFactory.getInstance().getActivityTableDao().showForM(Mno);
        for (HashMap<String, Object> activityTable : activityTables) {
            ActivityTable a = (ActivityTable) activityTable.get("a");
            String name = (String) activityTable.get("name");
            String major = (String) activityTable.get("major");
            System.out.print("姓名:" + name + "\t");
            System.out.print("学号:" + a.getSno() + "\t");
            System.out.print("学科:" + major + "\n");
            System.out.print("导师审核状态:");
            if (a.getMaudit().equals("0")) {
                System.out.print("待审核\t");
            } else if (a.getMaudit().equals("1")) {
                System.out.print("审核通过\t");
            } else {
                System.out.print("审核失败\t");
            }
            System.out.print("学科负责人审核状态:");
            if (a.getLaudit().equals("0")) {
                System.out.print("待审核\t");
            } else if (a.getLaudit().equals("1")) {
                System.out.print("审核通过\t");
            } else {
                System.out.print("审核失败\t");
            }
            System.out.print("\n");
            System.out.print("活动1序号:" + a.getActNoOne() + "\t");
            activityDetailShowOne(a.getActNoOne());
            activityAttendShowOne(a.getSno(), a.getActNoOne());
            System.out.println();
            System.out.print("活动2序号:" + a.getActNoTwo() + "\t");
            activityDetailShowOne(a.getActNoTwo());
            activityAttendShowOne(a.getSno(), a.getActNoTwo());
            System.out.println();
        }
        System.out.println("展示完成");
    }

    public static void activityTableShowForL(String Lno) {
        List<HashMap<String, Object>> activityTables = DaoFactory.getInstance().getActivityTableDao().showForL(Lno);
        for (HashMap<String, Object> activityTable : activityTables) {
            ActivityTable a = (ActivityTable) activityTable.get("a");
            String name = (String) activityTable.get("name");
            String major = (String) activityTable.get("major");
            System.out.print("姓名:" + name + "\t");
            System.out.print("学号:" + a.getSno() + "\t");
            System.out.print("学科:" + major + "\n");
            System.out.print("导师审核状态:");
            if (a.getMaudit().equals("0")) {
                System.out.print("待审核\t");
            } else if (a.getMaudit().equals("1")) {
                System.out.print("审核通过\t");
            } else {
                System.out.print("审核失败\t");
            }
            System.out.print("学科负责人审核状态:");
            if (a.getLaudit().equals("0")) {
                System.out.print("待审核\t");
            } else if (a.getLaudit().equals("1")) {
                System.out.print("审核通过\t");
            } else {
                System.out.print("审核失败\t");
            }
            System.out.print("\n");
            System.out.print("活动1序号:" + a.getActNoOne() + "\t");
            activityDetailShowOne(a.getActNoOne());
            activityAttendShowOne(a.getSno(), a.getActNoOne());
            System.out.println();
            System.out.print("活动2序号:" + a.getActNoTwo() + "\t");
            activityDetailShowOne(a.getActNoTwo());
            activityAttendShowOne(a.getSno(), a.getActNoTwo());
            System.out.println();
        }
        System.out.println("展示完成");
    }

    public static void activityTableShowForAdmin() {
        List<HashMap<String, Object>> activityTables = DaoFactory.getInstance().getActivityTableDao().showForAdmin();
        for (HashMap<String, Object> activityTable : activityTables) {
            ActivityTable a = (ActivityTable) activityTable.get("a");
            String name = (String) activityTable.get("name");
            String major = (String) activityTable.get("major");
            System.out.print("姓名:" + name + "\t");
            System.out.print("学号:" + a.getSno() + "\t");
            System.out.print("学科:" + major + "\n");
            System.out.print("导师审核状态:");
            if (a.getMaudit().equals("0")) {
                System.out.print("待审核\t");
            } else if (a.getMaudit().equals("1")) {
                System.out.print("审核通过\t");
            } else {
                System.out.print("审核失败\t");
            }
            System.out.print("学科负责人审核状态:");
            if (a.getLaudit().equals("0")) {
                System.out.print("待审核\t");
            } else if (a.getLaudit().equals("1")) {
                System.out.print("审核通过\t");
            } else {
                System.out.print("审核失败\t");
            }
            System.out.print("活动1序号:" + a.getActNoOne() + "\t");
            activityDetailShowOne(a.getActNoOne());
            activityAttendShowOne(a.getSno(), a.getActNoOne());
            System.out.println();
            System.out.print("活动2序号:" + a.getActNoTwo() + "\t");
            activityDetailShowOne(a.getActNoTwo());
            activityAttendShowOne(a.getSno(), a.getActNoTwo());
            System.out.println();
        }
        System.out.println("展示完成");
    }

    public static void activityAttendShowOne(String Sno, String actNo) {
        ActivityAttend a = DaoFactory.getInstance().getActivityAttendDao().showOne(Sno, actNo);
        System.out.print("报告名称:" + a.getActReportName() + "\t");
        System.out.print("参会图片:" + a.getActPic() + "\t");
        System.out.print("学生备注:" + a.getSps() + "\t");
    }

    public static void activityDetailShowOne(String actNo) {
        ActivityDetail a = DaoFactory.getInstance().getActivityDetailDao().showOne(actNo);
        System.out.print("名称:" + a.getActName() + "\t");
        System.out.print("名称:" + a.getActAdd() + "\t");
        System.out.print("名称:" + a.getActDate() + "\t");
    }

    public static boolean activityAttendCheck(String Sno, String a1, String a2) {
        return DaoFactory.getInstance().getActivityAttendDao().exist(Sno, a1, a2);
    }

    public static void out() {
        String path = "D:\\test.txt";
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, false)));
            List<HashMap<String, Object>> activityTables = DaoFactory.getInstance().getActivityTableDao().showForAdmin();
            for (HashMap<String, Object> activityTable : activityTables) {
                ActivityTable a = (ActivityTable) activityTable.get("a");
                String name = (String) activityTable.get("name");
                String major = (String) activityTable.get("major");
                out.write("姓名:" + name + "\t");
                out.write("学号:" + a.getSno() + "\t");
                out.write("学科:" + major + "\n");
                out.write("导师审核状态:");
                if (a.getMaudit().equals("0")) {
                    out.write("待审核\t");
                } else if (a.getMaudit().equals("1")) {
                    out.write("审核通过\t");
                } else {
                    out.write("审核失败\t");
                }
                out.write("学科负责人审核状态:");
                if (a.getLaudit().equals("0")) {
                    out.write("待审核\t\n");
                } else if (a.getLaudit().equals("1")) {
                    out.write("审核通过\t\n");
                } else {
                    out.write("审核失败\t\n");
                }
                out.write("活动1序号:" + a.getActNoOne() + "\t");
                activityDetailShowOne(out, a.getActNoOne());
                activityAttendShowOne(out, a.getSno(), a.getActNoOne());
                out.write("\n");
                out.write("活动2序号:" + a.getActNoTwo() + "\t");
                activityDetailShowOne(out, a.getActNoTwo());
                activityAttendShowOne(out, a.getSno(), a.getActNoTwo());
                out.write("\n");
            }
            System.out.println("导出完成");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void activityAttendShowOne(BufferedWriter out, String Sno, String actNo) throws IOException {
        ActivityAttend a = DaoFactory.getInstance().getActivityAttendDao().showOne(Sno, actNo);
        out.write("报告名称:" + a.getActReportName() + "\t");
        out.write("参会图片:" + a.getActPic() + "\t");
        out.write("学生备注:" + a.getSps() + "\t");
    }

    public static void activityDetailShowOne(BufferedWriter out, String actNo) throws IOException {
        ActivityDetail a = DaoFactory.getInstance().getActivityDetailDao().showOne(actNo);
        out.write("名称:" + a.getActName() + "\t");
        out.write("名称:" + a.getActAdd() + "\t");
        out.write("名称:" + a.getActDate() + "\t");
    }
}
