package Service;

import Entity.*;
import Utils.DAOFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class AchieveService {
	public static boolean isValidDate(String dateStr){
        boolean judgeresult=true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            format.setLenient(false);
            Date date =format.parse(dateStr);
            System.out.println(date);
        }catch(Exception e){
            judgeresult=false;
        }
        String yearStr=dateStr.split("-")[0];
        if(yearStr.startsWith("0")||yearStr.length()!=4){
            judgeresult=false;
        }
        return judgeresult;
    }
	public static void adminMenu(String userid) {
		//筛选初审结果为通过，状态为1的记录，并进行更新为2通过或者4拒绝
		//查看已被认证的结果，状态为2的记录
		//查看已拒绝认证的记录，状态为4的记录
		System.out.println("A-对初审通过的成果认定进行终审");
		System.out.println("B-查看终审通过的成果记录");
		System.out.println("C-查看未通过终审的成果记录");
		System.out.println("D-退出");
		String c="";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("请输入菜单选项：");
			if(scanner.hasNext()){
			    c = scanner.next();//程序会等待用户输入完毕
			}
			while(!c.contentEquals("A")&&!c.contentEquals("B")&&!c.contentEquals("C")&&!c.contentEquals("D")) {
				System.out.println("您的输入不正确，请重新输入：");
				c = scanner.next();
			}
			if(c.contentEquals("D")) {
				break;
			}
			if(c.contentEquals("A")) {
				System.out.println("1-审核论文认证");
				System.out.println("2-审核奖励认证");
				System.out.println("3-审核标准认证");
				System.out.println("4-审核报告认证");
				System.out.println("5-审核专利认证");
				System.out.println("6-审核软硬件平台认证");
				System.out.println("7-审核教材认证");
				System.out.println("0-退出审核");
				String choice="";
				while(true) {
					System.out.println("请输入审核选项：");
					if(scanner.hasNext()){
					    choice = scanner.next();//程序会等待用户输入完毕
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("您的输入不正确，请重新输入：");
						choice = scanner.next();
					}
					if(choice.contentEquals("0")) {
						break;
					}
					if(choice.contentEquals("1")) {
						System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
						List<Thesis> lt= DAOFactory.getInstance().getThesisDAO().findThesis(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("1")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setState("2");
									DAOFactory .getInstance().getThesisDAO().updateThesis(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setState("4");
									DAOFactory .getInstance().getThesisDAO().updateThesis(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("2")) {
						System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
						List<Reward> lt=DAOFactory .getInstance().getRewardDAO().findReward(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("1")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setState("2");
									DAOFactory .getInstance().getRewardDAO().updateReward(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setState("4");
									DAOFactory .getInstance().getRewardDAO().updateReward(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("3")) {
						System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
						List<Standard> lt=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("1")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setState("2");
									DAOFactory .getInstance().getStandardDAO().updateStandard(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setState("4");
									DAOFactory .getInstance().getStandardDAO().updateStandard(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("4")) {
						List<Report> lt=DAOFactory .getInstance().getReportBase().findReport(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("初审结果：同意")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("终审结果：同意");
									DAOFactory .getInstance().getReportBase().updateReport(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("终审结果：拒绝");
									DAOFactory .getInstance().getReportBase().updateReport(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("5")) {
						List<Patent> lt=DAOFactory .getInstance().getPatentBase().findPatent(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getTstatus().contentEquals("初审结果：同意")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setTstatus("终审结果：同意");
									DAOFactory .getInstance().getPatentBase().updatePatent(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setTstatus("终审结果：拒绝");
									DAOFactory .getInstance().getPatentBase().updatePatent(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("6")) {
						List<Prove> lt=DAOFactory .getInstance().getProveBase().findProve(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("初审结果：同意")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("终审结果：同意");
									DAOFactory .getInstance().getProveBase().updateProve(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("终审结果：拒绝");
									DAOFactory .getInstance().getProveBase().updateProve(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("7")) {
						List<Textbook> lt=DAOFactory .getInstance().getTextbookBase().findTextbook(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("初审结果：同意")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("终审结果：同意");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("终审结果：拒绝");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}
							}
							k++;
						}
					}
				}
			}
			if(c.contentEquals("B")) {
				System.out.println("已通过终审的论文列表");
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 4);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("2")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("已通过终审的奖励列表");
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 4);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("2")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("已通过终审的标准列表");
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 4);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("2")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("已通过终审的报告列表");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 4);
				int k4=0;
				while(k4<lt4.size()) {
					if(lt4.get(k4).getStatus().contentEquals("终审结果：通过")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("已通过终审的专利列表");
				List<Patent> lt5=DAOFactory .getInstance().getPatentBase().findPatent(userid, 4);
				int k5=0;
				while(k5<lt5.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("终审结果：通过")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("已通过终审的软硬件平台认证列表");
				List<Prove> lt6=DAOFactory .getInstance().getProveBase().findProve(userid, 4);
				int k6=0;
				while(k6<lt6.size()) {
					if(lt6.get(k6).getStatus().contentEquals("终审结果：通过")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("已通过终审的教材列表");
				List<Textbook> lt7=DAOFactory .getInstance().getTextbookBase().findTextbook(userid, 4);
				int k7=0;
				while(k7<lt7.size()) {
					if(lt7.get(k7).getStatus().contentEquals("终审结果：通过")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
			if(c.contentEquals("C")) {
				System.out.println("未通过终审的论文列表");
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 4);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("4")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("未通过终审的奖励列表");
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 4);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("4")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("未通过终审的标准列表");
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 4);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("4")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("未通过终审的报告列表");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 4);
				int k4=0;
				while(k4<lt4.size()) {
					if(lt4.get(k4).getStatus().contentEquals("终审结果：拒绝")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("未通过终审的专利列表");
				List<Patent> lt5=DAOFactory .getInstance().getPatentBase().findPatent(userid, 4);
				int k5=0;
				while(k5<lt5.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("终审结果：拒绝")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("未通过终审的软硬件平台认证列表");
				List<Prove> lt6=DAOFactory .getInstance().getProveBase().findProve(userid, 4);
				int k6=0;
				while(k6<lt6.size()) {
					if(lt6.get(k6).getStatus().contentEquals("终审结果：拒绝")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("未通过终审的教材列表");
				List<Textbook> lt7=DAOFactory .getInstance().getTextbookBase().findTextbook(userid, 4);
				int k7=0;
				while(k7<lt7.size()) {
					if(lt7.get(k7).getStatus().contentEquals("终审结果：拒绝")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
		}
	}
	public static void leaderMenu(String userid) {
		//查看本学科下已被认定的成果，状态为2的记录
		System.out.println("查询研究生成果认定情况");
		System.out.println("1-查询论文认证情况");
		System.out.println("2-查询奖励认证情况");
		System.out.println("3-查询标准认证情况");
		System.out.println("4-查询报告认证情况");
		System.out.println("5-查询专利认证情况");
		System.out.println("6-查询软硬件平台认证情况");
		System.out.println("7-查询教材认证情况");
		System.out.println("0-退出查询");
		Scanner scanner = new Scanner(System.in);
		String c="";
		while(true) {
			System.out.println("请输入选项：");
			if(scanner.hasNext()){
			    c = scanner.next();//程序会等待用户输入完毕
			}
			while(!c.contentEquals("1")&&!c.contentEquals("2")&&!c.contentEquals("3")&&!c.contentEquals("4")&&!c.contentEquals("5")&&!c.contentEquals("6")&&!c.contentEquals("7")&&!c.contentEquals("0")) {
				System.out.println("您的输入不正确，请重新输入：");
				c = scanner.next();
			}
			if(c.contentEquals("0")) {
				break;
			}
			if(c.contentEquals("1")) {
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("2")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("2")) {
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt=DAOFactory .getInstance().getRewardDAO().findReward(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("2")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("3")) {
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("2")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("4")) {
				List<Report> lt=DAOFactory.getInstance().getReportBase().findReport(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getStatus().contentEquals("终审结果：同意")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("5")) {
				List<Patent> lt=DAOFactory.getInstance().getPatentBase().findPatent(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getTstatus().contentEquals("终审结果：同意")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("6")) {
				List<Prove> lt=DAOFactory.getInstance().getProveBase().findProve(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getStatus().contentEquals("终审结果：同意")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("7")) {
				List<Textbook> lt=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getStatus().contentEquals("终审结果：同意")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
		}
	}
	public static void mentorMenu(String userid) {
		//筛选未审核的记录，状态为0的记录，并进行更新为1通过或者3拒绝
		//查看导师审核阶段，通过的记录，状态为1
		//查看导师审核阶段，拒绝的记录，状态为3
		//查看已被认证的结果，状态为2的记录
		System.out.println("A-对本组研究生成果认定进行初审");
		System.out.println("B-查看本组研究生初审通过的成果记录");
		System.out.println("C-查看本组研究生未通过初审的成果记录");
		System.out.println("D-查看本组研究生通过终审的成果记录");
		System.out.println("E-退出");
		String c="";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("请输入菜单选项：");
			if(scanner.hasNext()){
			    c = scanner.next();//程序会等待用户输入完毕
			}
			while(!c.contentEquals("A")&&!c.contentEquals("B")&&!c.contentEquals("C")&&!c.contentEquals("D")&&!c.contentEquals("E")) {
				System.out.println("您的输入不正确，请重新输入：");
				c = scanner.next();
			}
			if(c.contentEquals("E")) {
				break;
			}
			if(c.contentEquals("A")) {
				System.out.println("1-审核论文认证");
				System.out.println("2-审核奖励认证");
				System.out.println("3-审核标准认证");
				System.out.println("4-审核报告认证");
				System.out.println("5-审核专利认证");
				System.out.println("6-审核软硬件平台认证");
				System.out.println("7-审核教材认证");
				System.out.println("0-退出审核");
				String choice="";
				while(true) {
					System.out.println("请输入审核选项：");
					if(scanner.hasNext()){
					    choice = scanner.next();//程序会等待用户输入完毕
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("您的输入不正确，请重新输入：");
						choice = scanner.next();
					}
					if(choice.contentEquals("0")) {
						break;
					}
					if(choice.contentEquals("1")) {
						System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
						List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("0")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setState("1");
									DAOFactory .getInstance().getThesisDAO().updateThesis(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setState("3");
									DAOFactory .getInstance().getThesisDAO().updateThesis(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("2")) {
						System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
						List<Reward> lt=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("0")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setState("1");
									DAOFactory .getInstance().getRewardDAO().updateReward(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setState("3");
									DAOFactory .getInstance().getRewardDAO().updateReward(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("3")) {
						System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
						List<Standard> lt=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("0")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setState("1");
									DAOFactory .getInstance().getStandardDAO().updateStandard(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setState("3");
									DAOFactory .getInstance().getStandardDAO().updateStandard(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("4")) {
						List<Report> lt=DAOFactory .getInstance().getReportBase().findReport(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("未审核")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("初审结果：同意");
									DAOFactory .getInstance().getReportBase().updateReport(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("初审结果：拒绝");
									DAOFactory .getInstance().getReportBase().updateReport(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("5")) {
						List<Patent> lt=DAOFactory .getInstance().getPatentBase().findPatent(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getTstatus().contentEquals("未审核")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setTstatus("初审结果：同意");
									DAOFactory .getInstance().getPatentBase().updatePatent(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setTstatus("初审结果：拒绝");
									DAOFactory .getInstance().getPatentBase().updatePatent(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("6")) {
						List<Prove> lt=DAOFactory .getInstance().getProveBase().findProve(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("未审核")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("初审结果：同意");
									DAOFactory .getInstance().getProveBase().updateProve(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("初审结果：拒绝");
									DAOFactory .getInstance().getProveBase().updateProve(lt.get(k));
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("7")) {
						List<Textbook> lt=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("未审核")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("初审结果：同意");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("初审结果：拒绝");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}
							}
							k++;
						}
					}
				}
			}
			if(c.contentEquals("B")) {
				System.out.println("已通过初审的论文列表");
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("1")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("已通过初审的奖励列表");
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("1")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("已通过初审的标准列表");
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt3=DAOFactory.getInstance().getStandardDAO().findStandard(userid, 3);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("1")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("已通过初审的报告列表");
				List<Report> lt4=DAOFactory.getInstance().getReportBase().findReport(userid, 3);
				int k4=0;
				while(k4<lt.size()) {
					if(lt4.get(k4).getStatus().contentEquals("初审结果：同意")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("已通过初审的专利列表");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 3);
				int k5=0;
				while(k5<lt.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("初审结果：同意")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("已通过初审的软硬件平台认证列表");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 3);
				int k6=0;
				while(k6<lt.size()) {
					if(lt6.get(k6).getStatus().contentEquals("初审结果：同意")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("已通过初审的教材列表");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 3);
				int k7=0;
				while(k7<lt.size()) {
					if(lt7.get(k7).getStatus().contentEquals("初审结果：同意")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
			if(c.contentEquals("C")) {
				System.out.println("未通过初审的论文列表");
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("3")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("未通过初审的奖励列表");
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("3")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("未通过初审的标准列表");
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 3);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("3")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("未通过初审的报告列表");
				List<Report> lt4=DAOFactory.getInstance().getReportBase().findReport(userid, 3);
				int k4=0;
				while(k4<lt.size()) {
					if(lt4.get(k4).getStatus().contentEquals("初审结果：拒绝")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("未通过初审的专利列表");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 3);
				int k5=0;
				while(k5<lt.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("初审结果：拒绝")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("未通过初审的软硬件平台认证列表");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 3);
				int k6=0;
				while(k6<lt.size()) {
					if(lt6.get(k6).getStatus().contentEquals("初审结果：拒绝")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("未通过初审的教材列表");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 3);
				int k7=0;
				while(k7<lt.size()) {
					if(lt7.get(k7).getStatus().contentEquals("初审结果：拒绝")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
			if(c.contentEquals("D")) {
				System.out.println("已通过终审的论文列表");
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("2")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("已通过终审的奖励列表");
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("2")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("已通过终审的标准列表");
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 3);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("2")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("已通过终审的报告列表");
				List<Report> lt4=DAOFactory.getInstance().getReportBase().findReport(userid, 3);
				int k4=0;
				while(k4<lt.size()) {
					if(lt4.get(k4).getStatus().contentEquals("终审结果：同意")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("已通过终审的专利列表");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 3);
				int k5=0;
				while(k5<lt.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("终审结果：同意")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("已通过终审的软硬件平台认证列表");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 3);
				int k6=0;
				while(k6<lt.size()) {
					if(lt6.get(k6).getStatus().contentEquals("终审结果：同意")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("已通过终审的教材列表");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 3);
				int k7=0;
				while(k7<lt.size()) {
					if(lt7.get(k7).getStatus().contentEquals("终审结果：同意")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
		}
	}
	public static void studentMenu(String userid) {
		//提交认证申请，此处插入时审核状态设为0
		//查看已提交的申请
		//修改被拒绝的申请，筛选状态为3和4的记录，进行更新
		//撤回申请，删除
		System.out.println("A-提交成果认定申请");
		System.out.println("B-查看已提交申请审核进程");
		System.out.println("C-修改被退回的申请");
		System.out.println("D-撤回成果认定申请");
		System.out.println("E-退出");
		String c="";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("请输入菜单选项：");
			if(scanner.hasNext()){
			    c = scanner.next();//程序会等待用户输入完毕
			}
			while(!c.contentEquals("A")&&!c.contentEquals("B")&&!c.contentEquals("C")&&!c.contentEquals("D")&&!c.contentEquals("E")) {
				System.out.println("您的输入不正确，请重新输入：");
				c = scanner.next();
			}
			if(c.contentEquals("E")) {
				break;
			}
			if(c.contentEquals("A")) {
				System.out.println("1-提交论文认证申请");
				System.out.println("2-提交奖励认证申请");
				System.out.println("3-提交标准认证申请");
				System.out.println("4-提交报告认证申请");
				System.out.println("5-提交专利认证申请");
				System.out.println("6-提交软硬件平台认证申请");
				System.out.println("7-提交教材认证申请");
				System.out.println("0-退出申请操作");
				String choice="";
				while(true) {
					System.out.println("请输入申请选项：");
					if(scanner.hasNext()){
					    choice = scanner.next();//程序会等待用户输入完毕
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("您的输入不正确，请重新输入：");
						choice = scanner.next();
					}
					if(choice.contentEquals("0")) {
						break;
					}
					if(choice.contentEquals("1")) {
						System.out.printf("请输入您想申请的论文认证个数：");
						while (!scanner.hasNextInt()){
						    System.out.println("请输入整数！");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Thesis t=new Thesis();
							System.out.printf("论文名称：");
							t.setName(scanner.nextLine());
							System.out.printf("论文发表刊物名称：");
							t.setPubname(scanner.nextLine());
							System.out.printf("论文发表时间：yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("请以yyyy-MM-dd格式输入时间！");
								tmp=scanner.nextLine();
							}
							t.setPubtime(tmp);
							System.out.printf("论文发表状态：(录用未发表/已发表)");
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("录用未发表")&&!tmp.contentEquals("已发表")) {
								System.out.println("请输入录用未发表/已发表！");
								tmp=scanner.nextLine();
							}
							t.setPubstate(tmp);
							System.out.printf("论文索引类型：");
							t.setIndextype(scanner.nextLine());
							System.out.printf("论文归属库情况：(学院高质量论文库/学院核心论文库)");
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("学院高质量论文库")&&!tmp.contentEquals("学院核心论文库")) {
								System.out.println("请输入学院高质量论文库/学院核心论文库！");
								tmp=scanner.nextLine();
							}
							t.setBase(scanner.nextLine());
							System.out.printf("论文扫描或PDF文件：");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setState("0");
							List<Thesis> ltl=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory .getInstance().getThesisDAO().addThesis(t);
							System.out.println("已提交申请！");
						}
					}
					if(choice.contentEquals("2")) {
						System.out.printf("请输入您想申请的奖励认证个数：");
						while (!scanner.hasNextInt()){
						    System.out.println("请输入整数！");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Reward t=new Reward();
							System.out.printf("奖励名称：");
							t.setName(scanner.nextLine());
							System.out.printf("奖励等级：(国家级/省部级/市级/其他)");
							String tmp="";
							while(!tmp.contentEquals("国家级")&&!tmp.contentEquals("省部级")&&!tmp.contentEquals("市级")&&!tmp.contentEquals("其他")) {
								System.out.println("请输入(国家级/省部级/市级/其他)！");
								tmp=scanner.nextLine();
							}
							t.setRelevel(tmp);
							System.out.printf("获奖等级：(特等奖/一等奖/二等奖/三等奖)");
							tmp="";
							while(!tmp.contentEquals("特等奖")&&!tmp.contentEquals("一等奖")&&!tmp.contentEquals("二等奖")&&!tmp.contentEquals("三等奖")) {
								System.out.println("请输入(特等奖/一等奖/二等奖/三等奖)！");
								tmp=scanner.nextLine();
							}
							t.setGetlevel(tmp);
							System.out.printf("排名：)");
							t.setGetlevel(scanner.nextLine());
							System.out.printf("获奖时间：yyyy-MM-dd");
							tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("请以yyyy-MM-dd格式输入时间！");
								tmp=scanner.nextLine();
							}
							t.setGettime(tmp);
							System.out.printf("佐证材料");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setState("0");
							List<Reward> ltl=DAOFactory .getInstance().getRewardDAO().findReward(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory .getInstance().getRewardDAO().addReward(t);
							System.out.println("已提交申请！");
						}
					}
					if(choice.contentEquals("3")) {
						System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
						System.out.printf("请输入您想申请的标准认证个数：");
						while (!scanner.hasNextInt()){
						    System.out.println("请输入整数！");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Standard t=new Standard();
							System.out.printf("标准名称：");
							t.setName(scanner.nextLine());
							System.out.printf("标准等级：(国家标准/省部级地方标准/行业标准/团队标准)");
							String tmp="";
							while(!tmp.contentEquals("国家标准")&&!tmp.contentEquals("省部级地方标准")&&!tmp.contentEquals("行业标准")&&!tmp.contentEquals("团队标准")) {
								System.out.println("请输入(国家标准/省部级地方标准/行业标准/团队标准)！");
								tmp=scanner.nextLine();
							}
							t.setGetlevel(tmp);
							System.out.printf("标准发布时间：yyyy-MM-dd");
							tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("请以yyyy-MM-dd格式输入时间！");
								tmp=scanner.nextLine();
							}
							t.setPubtime(tmp);
							System.out.printf("佐证材料");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setState("0");
							List<Standard> ltl=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory .getInstance().getStandardDAO().addStandard(t);
							System.out.println("已提交申请！");
						}
					}
					if(choice.contentEquals("4")) {
						System.out.printf("请输入您想申请的报告认证个数：");
						while (!scanner.hasNextInt()){
							System.out.println("请输入整数！");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Report t=new Report();
							System.out.printf("报告名称：");
							t.setName(scanner.nextLine());
							System.out.printf("报告服务单位：");
							t.setAddress(scanner.nextLine());
							System.out.printf("报告时间：yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("请以yyyy-MM-dd格式输入时间！");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("报告类型：(规划类/设计类/服务类/其他)");
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("规划类")&&!tmp.contentEquals("设计类")&&!tmp.contentEquals("服务类")&&!tmp.contentEquals("其他")) {
								System.out.println("请输入 规划类/设计类/服务类/其他");
								tmp=scanner.nextLine();
							}
							t.setType(tmp);
							System.out.printf("贡献度排名（整数）：");
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("佐证材料：");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setStatus("未审核");
							List<Report> ltl=DAOFactory.getInstance().getReportBase().findReport(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory.getInstance().getReportBase().addReport(t);
							System.out.println("已提交申请！");
						}
					}
					if(choice.contentEquals("5")) {
						System.out.printf("请输入您想申请的专利认证个数：");
						while (!scanner.hasNextInt()){			//输入鲁棒性
							System.out.println("请输入整数！");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Patent t=new Patent();
							System.out.printf("专利名称：");			//输入专利名称
							t.setName(scanner.nextLine());
							System.out.printf("专利号：");			//输入专利号
							t.setId(scanner.nextLine());
							System.out.printf("专利发布时间：yyyy-MM-dd");		//输入专利发布时间
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("请以yyyy-MM-dd格式输入时间！");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("专利类型(发明专利/实用新型专利)：");		//限制专利类型
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("发明专利")&&!tmp.contentEquals("实用新型专利")) {
								System.out.println("请输入 发明专利/实用新型专利");
								tmp=scanner.nextLine();
							}
							t.setType(tmp);
							System.out.printf("贡献度排名（整数）：");		//输入贡献度排名
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("专利状态：");		//输入专利状态
							t.setStatus(scanner.nextLine());
							System.out.printf("佐证材料：");		//输入佐证材料
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setTstatus("未审核");				//设置审核状态为未提交
							DAOFactory.getInstance().getPatentBase().addPatent(t);		//将其添加到表中
							System.out.println("已提交申请！");
						}
					}
					if(choice.contentEquals("6")) {
						System.out.printf("请输入您想申请的软硬件平台认证个数：");
						while (!scanner.hasNextInt()){
							System.out.println("请输入整数！");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Prove t=new Prove();
							System.out.printf("平台名称：");
							t.setName(scanner.nextLine());
							System.out.printf("平台服务单位：");
							t.setAddress(scanner.nextLine());
							System.out.printf("平台上线时间：yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("请以yyyy-MM-dd格式输入时间！");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("贡献度排名（整数）：");
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("佐证材料：");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setStatus("未审核");
							List<Prove> ltl=DAOFactory.getInstance().getProveBase().findProve(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory.getInstance().getProveBase().addProve(t);
							System.out.println("已提交申请！");
						}
					}
					if(choice.contentEquals("7")) {
						System.out.printf("请输入您想申请的教材认证个数：");
						while (!scanner.hasNextInt()){
							System.out.println("请输入整数！");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Textbook t=new Textbook();
							System.out.printf("教材名称：");
							t.setName(scanner.nextLine());
							System.out.printf("教材出版社：");
							t.setPress(scanner.nextLine());
							System.out.printf("教材出版时间：yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("请以yyyy-MM-dd格式输入时间！");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("贡献度排名（整数）：");
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("佐证材料：");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setStatus("未审核");
							List<Textbook> ltl=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory.getInstance().getTextbookBase().addTextbook(t);
							System.out.println("已提交申请！");
						}
					}
				}
			}
			if(c.contentEquals("B")) {
				System.out.println("论文认证进程");
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 1);
				int k=0;
				while(k<lt.size()) {
					System.out.println(lt.get(k));
					k++;
				}
				System.out.println("奖励认证进程");
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 1);
				int k2=0;
				while(k2<lt2.size()) {
					System.out.println(lt2.get(k2));
					k2++;
				}
				System.out.println("标准认证进程");
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 1);
				int k3=0;
				while(k3<lt3.size()) {
					System.out.println(lt3.get(k3));
					k3++;
				}
				System.out.println("报告认证进程");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 1);
				int k4=0;
				while(k4<lt4.size()) {
					System.out.println(lt4.get(k4));
					k4++;
				}
				System.out.println("终审认证进程");
				List<Patent> lt5=DAOFactory .getInstance().getPatentBase().findPatent(userid, 1);
				int k5=0;
				while(k5<lt5.size()) {
					System.out.println(lt5.get(k5));
					k5++;
				}
				System.out.println("软硬件平台认证进程");
				List<Prove> lt6=DAOFactory .getInstance().getProveBase().findProve(userid, 4);
				int k6=0;
				while(k6<lt6.size()) {
					System.out.println(lt6.get(k6));
					k6++;
				}
				System.out.println("教材认证进程");
				List<Textbook> lt7=DAOFactory .getInstance().getTextbookBase().findTextbook(userid, 4);
				int k7=0;
				while(k7<lt7.size()) {
					System.out.println(lt7.get(k7));
					k7++;
				}
			}
			if(c.contentEquals("C")) {
				System.out.println("1-修改论文认证申请");
				System.out.println("2-修改奖励认证申请");
				System.out.println("3-修改标准认证申请");
				System.out.println("4-修改报告认证申请");
				System.out.println("5-修改专利认证申请");
				System.out.println("6-修改软硬件平台认证申请");
				System.out.println("7-修改教材认证申请");
				System.out.println("0-退出申请操作");
				String choice="";
				while(true) {
					System.out.println("请输入修改选项：");
					if(scanner.hasNext()){
					    choice = scanner.next();//程序会等待用户输入完毕
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("您的输入不正确，请重新输入：");
						choice = scanner.next();
					}
					if(choice.contentEquals("1")) {
						List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 1);
						int k=0;
						System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("3")||lt.get(k).getState().contentEquals("4")) {
								Thesis t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：修改-1，跳过-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("请输入修改项序号（1-论文名称 2-论文发表刊物名称 3-发表时间 4-论文状态 5-索引类型 6-论文归属情况 7-论文扫描或PDF材料 0-退出对当前项的修改）");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
										    inc = scanner.next();//程序会等待用户输入完毕
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("7")&&!inc.contentEquals("0")) {
											System.out.println("您的输入不正确，请重新输入：");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("论文名称：");
											t.setName(scanner.nextLine());
											
										}
										if(choice.contentEquals("2")) {
											System.out.printf("论文发表刊物名称：");
											t.setPubname(scanner.nextLine());
										}
										if(choice.contentEquals("3")) {
											System.out.printf("论文发表时间：yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("请以yyyy-MM-dd格式输入时间！");
												tmp=scanner.nextLine();
											}
											t.setPubtime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("论文发表状态：(录用未发表/已发表)");
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("录用未发表")&&!tmp.contentEquals("已发表")) {
												System.out.println("请输入录用未发表/已发表！");
												tmp=scanner.nextLine();
											}
											t.setPubstate(tmp);
										}
										if(choice.contentEquals("5")) {
											System.out.printf("论文索引类型：");
											t.setIndextype(scanner.nextLine());
										}
										if(choice.contentEquals("6")) {
											System.out.printf("论文归属库情况：(学院高质量论文库/学院核心论文库)");
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("学院高质量论文库")&&!tmp.contentEquals("学院核心论文库")) {
												System.out.println("请输入学院高质量论文库/学院核心论文库！");
												tmp=scanner.nextLine();
											}
											t.setBase(scanner.nextLine());
										}
										if(choice.contentEquals("7")) {
											System.out.printf("论文扫描或PDF文件：");
											t.setMaterial(scanner.nextLine());
										}										
									}
									t.setState("0");
									DAOFactory .getInstance().getThesisDAO().updateThesis(t);
									System.out.println("修改成功！");
								}
								k++;
							}
						}
					}
					
					if(choice.contentEquals("2")) {
						System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
						List<Reward> lt=DAOFactory .getInstance().getRewardDAO().findReward(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("3")||lt.get(k).getState().contentEquals("4")) {
								Reward t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("请输入修改项序号（1-奖励名称 2奖励等级 3-获奖等级 4-排名 5-获奖时间 6-佐证材料 0-退出对当前项的修改）");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
										    inc = scanner.next();//程序会等待用户输入完毕
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("0")) {
											System.out.println("您的输入不正确，请重新输入：");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("奖励名称：");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("奖励等级：(国家级/省部级/市级/其他)");
											String tmp="";
											while(!tmp.contentEquals("国家级")&&!tmp.contentEquals("省部级")&&!tmp.contentEquals("市级")&&!tmp.contentEquals("其他")) {
												System.out.println("请输入(国家级/省部级/市级/其他)！");
												tmp=scanner.nextLine();
											}
											t.setRelevel(tmp);
										}
										if(choice.contentEquals("3")) {
											System.out.printf("排名：)");
											t.setGetlevel(scanner.nextLine());
										}
										if(choice.contentEquals("4")) {
											System.out.printf("获奖等级：(特等奖/一等奖/二等奖/三等奖)");
											String tmp="";
											while(!tmp.contentEquals("特等奖")&&!tmp.contentEquals("一等奖")&&!tmp.contentEquals("二等奖")&&!tmp.contentEquals("三等奖")) {
												System.out.println("请输入(特等奖/一等奖/二等奖/三等奖)！");
												tmp=scanner.nextLine();
											}
											t.setGetlevel(tmp);
										}
										if(choice.contentEquals("5")) {
											System.out.printf("获奖时间：yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("请以yyyy-MM-dd格式输入时间！");
												tmp=scanner.nextLine();
											}
											t.setGettime(tmp);
										}
										if(choice.contentEquals("6")) {
											System.out.printf("佐证材料");
											t.setMaterial(scanner.nextLine());
										}										
									}
									t.setState("0");
									DAOFactory .getInstance().getRewardDAO().updateReward(t);
									System.out.println("修改成功！");
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("3")) {
						System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
						List<Standard> lt=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("3")||lt.get(k).getState().contentEquals("4")) {
								Standard t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：同意-1，拒绝-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									System.out.println("请输入修改项序号（1-标准名称 2标准级别 3-标准发布时间 4-佐证材料 0-退出对当前项的修改）");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
										    inc = scanner.next();//程序会等待用户输入完毕
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("0")) {
											System.out.println("您的输入不正确，请重新输入：");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("标准名称：");
											t.setName(scanner.nextLine());
											
											
											
										}
										if(choice.contentEquals("2")) {
											System.out.printf("标准等级：(国家标准/省部级地方标准/行业标准/团队标准)");
											String tmp="";
											while(!tmp.contentEquals("国家标准")&&!tmp.contentEquals("省部级地方标准")&&!tmp.contentEquals("行业标准")&&!tmp.contentEquals("团队标准")) {
												System.out.println("请输入(国家标准/省部级地方标准/行业标准/团队标准)！");
												tmp=scanner.nextLine();
											}
											t.setGetlevel(tmp);
										}
										if(choice.contentEquals("3")) {
											System.out.printf("标准发布时间：yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("请以yyyy-MM-dd格式输入时间！");
												tmp=scanner.nextLine();
											}
											t.setPubtime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("佐证材料");
											t.setMaterial(scanner.nextLine());
										}
									t.setState("0");
									DAOFactory .getInstance().getStandardDAO().updateStandard(t);
									System.out.println("修改成功！");
								}
							}
							k++;
						}
						}
					}
					if(choice.contentEquals("4")) {
						List<Report> lt=DAOFactory.getInstance().getReportBase().findReport(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("初审结果：拒绝")||lt.get(k).getStatus().contentEquals("终审结果：拒绝")) {
								Report t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：修改-1，跳过-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("请输入修改项序号（1-报告名称 2-报告类型 3-报告时间 4-报告服务单位 5-贡献度排名 6-佐证材料 0-退出对当前项的修改）");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();//程序会等待用户输入完毕
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("0")) {
											System.out.println("您的输入不正确，请重新输入：");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("报告名称：");
											t.setName(scanner.nextLine());

										}
										if(choice.contentEquals("2")) {
											System.out.printf("报告类型：(规划类/设计类/服务类/其他)");
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("规划类")&&!tmp.contentEquals("设计类")&&!tmp.contentEquals("服务类")&&!tmp.contentEquals("其他")) {
												System.out.println("请输入 规划类/设计类/服务类/其他！");
												tmp=scanner.nextLine();
											}
											t.setType(tmp);
										}
										if(choice.contentEquals("3")) {
											System.out.printf("报告时间：yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("请以yyyy-MM-dd格式输入时间！");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("报告服务单位：");
											t.setAddress(scanner.nextLine());
										}
										if(choice.contentEquals("5")) {
											System.out.printf("贡献度排名（整数）：");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("6")) {
											System.out.printf("佐证材料：");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("未审核");
									DAOFactory.getInstance().getReportBase().updateReport(t);
									System.out.println("修改成功！");
								}
								k++;
							}
						}
					}
					if(choice.contentEquals("5")) {
						List<Patent> lt=DAOFactory.getInstance().getPatentBase().findPatent(userid, 1);	//获取到学生名下的所有专利
						int k=0;
						while(k<lt.size()) {			//遍历列表，
							if(lt.get(k).getTstatus().contentEquals("初审结果：拒绝")||lt.get(k).getTstatus().contentEquals("终审结果：拒绝")) {			//状态位为被打回
								Patent t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：修改-1，跳过-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){		//输入鲁棒性
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("请输入修改项序号（1-专利名称 2-专利类型 3-专利发布时间 4-专利状态 5-贡献度排名 6-佐证材料 0-退出对当前项的修改）");  //选择修改的字段
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();				//程序会等待用户输入完毕
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("0")) {
											System.out.println("您的输入不正确，请重新输入：");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {				//对专利名称进行修改
											System.out.printf("专利名称：");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("专利类型：(发明专利/实用新型专利)");		//对专利类型进行修改
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("发明专利")&&!tmp.contentEquals("实用新型专利")) {	//对专利类型进行控制
												System.out.println("请输入 发明专利/实用新型专利");
												tmp=scanner.nextLine();
											}
											t.setType(tmp);
										}
										if(choice.contentEquals("3")) {				//对专利时间进行修改
											System.out.printf("专利发布时间：yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {				//对输入格式进行判断
												System.out.println("请以yyyy-MM-dd格式输入时间！");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {				//对专利状态进行修改
											System.out.printf("专利状态：");
											t.setStatus(scanner.nextLine());
										}
										if(choice.contentEquals("5")) {				//对贡献度排名进行修改
											System.out.printf("贡献度排名（整数）：");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("6")) {				//对佐证材料进行修改
											System.out.printf("佐证材料：");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("未审核");									//将审核状态改为未审核，从初审开始
									DAOFactory.getInstance().getPatentBase().updatePatent(t);	//调用DAO层更新状态
									System.out.println("修改成功！");
								}
								k++;
							}
						}
					}
					if(choice.contentEquals("6")) {
						List<Prove> lt=DAOFactory.getInstance().getProveBase().findProve(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("初审结果：拒绝")||lt.get(k).getStatus().contentEquals("终审结果：拒绝")) {
								Prove t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：修改-1，跳过-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("请输入修改项序号（1-软硬件平台名称 2-平台服务单位 3-平台上线时间 4-贡献度排名 5-佐证材料 0-退出对当前项的修改）");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();//程序会等待用户输入完毕
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("0")) {
											System.out.println("您的输入不正确，请重新输入：");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("软硬件平台名称：");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("平台服务单位：");
											t.setAddress(scanner.nextLine());
										}
										if(choice.contentEquals("3")) {
											System.out.printf("平台上线时间：yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("请以yyyy-MM-dd格式输入时间！");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("贡献度排名（整数）：");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("5")) {
											System.out.printf("佐证材料：");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("未审核");
									DAOFactory.getInstance().getProveBase().updateProve(t);
									System.out.println("修改成功！");
								}
								k++;
							}
						}
					}
					if(choice.contentEquals("7")) {
						List<Textbook> lt=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("初审结果：拒绝")||lt.get(k).getStatus().contentEquals("终审结果：拒绝")) {
								Textbook t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("请输入：修改-1，跳过-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("您的输入错误，请输入1或0：");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("请输入修改项序号（1-教材名称 2-教材出版社 3-教材出版时间 4-贡献度排名 5-佐证材料 0-退出对当前项的修改）");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();//程序会等待用户输入完毕
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("0")) {
											System.out.println("您的输入不正确，请重新输入：");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("教材名称：");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("教材出版社：");
											t.setPress(scanner.nextLine());
										}
										if(choice.contentEquals("3")) {
											System.out.printf("教材出版时间：yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("请以yyyy-MM-dd格式输入时间！");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("贡献度排名（整数）：");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("5")) {
											System.out.printf("佐证材料：");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("未审核");
									DAOFactory.getInstance().getTextbookBase().updateTextbook(t);
									System.out.println("修改成功！");
								}
								k++;
							}
						}
					}
				}
			}
			if(c.contentEquals("D")) {
				System.out.println("已提交申请的论文认证列表");
				System.out.println("论文名称\t论文发表刊物名称\t发表时间\t论文状态\t索引类型\t论文归属库情况\t论文扫描或PDF材料\t所属人学号\t审核状态");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 1);
				int k=0;
				while(k<lt.size()) {
					System.out.println(k+1+"\t"+lt.get(k));
					k++;
				}
				System.out.printf("请输入您想撤回的论文认证个数：");
				while (!scanner.hasNextInt()){
				    System.out.println("请输入整数！");
				}
				int count=scanner.nextInt();
				while(count>lt.size()||count<0) {
					System.out.printf("请输入0-"+lt.size()+"的数字！");
					while (!scanner.hasNextInt()){
					    System.out.println("请输入整数！");
					}
					count=scanner.nextInt();
				}
				int j=0;
				for(j=0;j<count;j++) {
					System.out.printf("请输入您想撤回的论文序号：");
					while (!scanner.hasNextInt()){
					    System.out.println("请输入整数！");
					}
					int index=scanner.nextInt();
					while(index>lt.size()||index<1) {
						System.out.printf("请输入1-"+lt.size()+"的数字！");
						while (!scanner.hasNextInt()){
						    System.out.println("请输入整数！");
						}
						index=scanner.nextInt();
					}
					DAOFactory .getInstance().getThesisDAO().deleteThesis(lt.get(index-1));
					System.out.println("撤回成功！");
				}
				
				System.out.println("已提交申请的奖励认证列表");
				System.out.println("奖励名称\t奖励等级\t获奖等级\t排名\t获奖时间\t佐证材料\t所属人学号\t审核状态");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 1);
				int k2=0;
				while(k2<lt2.size()) {
					System.out.println(k2+1+"\t"+lt2.get(k2));
					k2++;
				}
				System.out.printf("请输入您想撤回的奖励认证个数：");
				while (!scanner.hasNextInt()){
				    System.out.println("请输入整数！");
				}
				count=scanner.nextInt();
				while(count>lt2.size()||count<0) {
					System.out.printf("请输入0-"+lt2.size()+"的数字！");
					while (!scanner.hasNextInt()){
					    System.out.println("请输入整数！");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("请输入您想撤回的奖励序号：");
					while (!scanner.hasNextInt()){
					    System.out.println("请输入整数！");
					}
					int index=scanner.nextInt();
					while(index>lt2.size()||index<1) {
						System.out.printf("请输入1-"+lt2.size()+"的数字！");
						while (!scanner.hasNextInt()){
						    System.out.println("请输入整数！");
						}
						index=scanner.nextInt();
					}
					DAOFactory .getInstance().getRewardDAO().deleteReward(lt2.get(index-1));
					System.out.println("撤回成功！");
				}
				
				System.out.println("已提交申请的标准认证列表");
				System.out.println("标准名称\t标准级别\t标准发布时间\t佐证材料\t所属人学号\t审核状态");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 1);
				int k3=0;
				while(k3<lt3.size()) {
					System.out.println(k3+1+"\t"+lt3.get(k3));
					k3++;
				}
				System.out.printf("请输入您想撤回的标准认证个数：");
				while (!scanner.hasNextInt()){
				    System.out.println("请输入整数！");
				}
				count=scanner.nextInt();
				while(count>lt3.size()||count<0) {
					System.out.printf("请输入0-"+lt3.size()+"的数字！");
					while (!scanner.hasNextInt()){
					    System.out.println("请输入整数！");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("请输入您想撤回的标准序号：");
					while (!scanner.hasNextInt()){
					    System.out.println("请输入整数！");
					}
					int index=scanner.nextInt();
					while(index>lt.size()||index<1) {
						System.out.printf("请输入1-"+lt3.size()+"的数字！");
						while (!scanner.hasNextInt()){
						    System.out.println("请输入整数！");
						}
						index=scanner.nextInt();
					}
					DAOFactory .getInstance().getStandardDAO().deleteStandard(lt3.get(index-1));
					System.out.println("撤回成功！");
				}

				System.out.println("已提交申请的报告认证列表");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 1);
				int k4=0;
				while(k4<lt4.size()) {
					System.out.println(k4+1+"\t"+lt4.get(k4));
					k4++;
				}
				System.out.printf("请输入您想撤回的报告认证个数：");
				while (!scanner.hasNextInt()){
					System.out.println("请输入整数！");
				}
				count=scanner.nextInt();
				while(count>lt4.size()||count<0) {
					System.out.printf("请输入0-"+lt4.size()+"的数字！");
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("请输入您想撤回的报告序号：");
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					int index=scanner.nextInt();
					while(index>lt4.size()||index<1) {
						System.out.printf("请输入1-"+lt4.size()+"的数字！");
						while (!scanner.hasNextInt()){
							System.out.println("请输入整数！");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getReportBase().deleteReport(lt4.get(index-1));
					System.out.println("撤回成功！");
				}
				System.out.println("已提交申请的专利认证列表");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 1);	//获取该研究生名下的专利列表
				int k5=0;
				while(k5<lt5.size()) {
					System.out.println(k5+1+"\t"+lt5.get(k5));		//输出专利列表
					k5++;
				}
				System.out.printf("请输入您想撤回的专利认证个数：");
				while (!scanner.hasNextInt()){					//对输入进行控制
					System.out.println("请输入整数！");
				}
				count=scanner.nextInt();
				while(count>lt5.size()||count<0) {
					System.out.printf("请输入0-"+lt5.size()+"的数字！");	 //对输入进行控制
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("请输入您想撤回的专利序号：");			//对输入进行控制
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					int index=scanner.nextInt();
					while(index>lt5.size()||index<1) {
						System.out.printf("请输入1-"+lt5.size()+"的数字！");	//对输入进行控制
						while (!scanner.hasNextInt()){
							System.out.println("请输入整数！");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getPatentBase().deletePatent(lt5.get(index-1));	//调用DAO层撤回申请
					System.out.println("撤回成功！");
				}

				System.out.println("已提交申请的软硬件平台认证列表");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 1);
				int k6=0;
				while(k6<lt6.size()) {
					System.out.println(k6+1+"\t"+lt6.get(k6));
					k6++;
				}
				System.out.printf("请输入您想撤回的软硬件平台认证个数：");
				while (!scanner.hasNextInt()){
					System.out.println("请输入整数！");
				}
				count=scanner.nextInt();
				while(count>lt6.size()||count<0) {
					System.out.printf("请输入0-"+lt6.size()+"的数字！");
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("请输入您想撤回的软硬件平台认证序号：");
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					int index=scanner.nextInt();
					while(index>lt6.size()||index<1) {
						System.out.printf("请输入1-"+lt6.size()+"的数字！");
						while (!scanner.hasNextInt()){
							System.out.println("请输入整数！");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getProveBase().deleteProve(lt6.get(index-1));
					System.out.println("撤回成功！");
				}


				System.out.println("已提交申请的教材认证列表");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 1);
				int k7=0;
				while(k7<lt7.size()) {
					System.out.println(k7+1+"\t"+lt7.get(k7));
					k7++;
				}
				System.out.printf("请输入您想撤回的教材认证个数：");
				while (!scanner.hasNextInt()){
					System.out.println("请输入整数！");
				}
				count=scanner.nextInt();
				while(count>lt7.size()||count<0) {
					System.out.printf("请输入0-"+lt7.size()+"的数字！");
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("请输入您想撤回的教材序号：");
					while (!scanner.hasNextInt()){
						System.out.println("请输入整数！");
					}
					int index=scanner.nextInt();
					while(index>lt7.size()||index<1) {
						System.out.printf("请输入1-"+lt7.size()+"的数字！");
						while (!scanner.hasNextInt()){
							System.out.println("请输入整数！");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getTextbookBase().deleteTextbook(lt7.get(index-1));
					System.out.println("撤回成功！");
				}
			}
		}
	}
	public static void achieveMenu(String userid,int role) {
		switch(role) {
		case 1:
			//研究生管理员通过或拒绝认定成果
			adminMenu(userid);
			break;
		case 2:
			//学科负责人
			leaderMenu(userid);
			break;
		case 3:
			//授课教师
			System.out.println("授课教师无权限操作成果认定工作！");
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
}
