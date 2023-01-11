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
		//ɸѡ������Ϊͨ����״̬Ϊ1�ļ�¼�������и���Ϊ2ͨ������4�ܾ�
		//�鿴�ѱ���֤�Ľ����״̬Ϊ2�ļ�¼
		//�鿴�Ѿܾ���֤�ļ�¼��״̬Ϊ4�ļ�¼
		System.out.println("A-�Գ���ͨ���ĳɹ��϶���������");
		System.out.println("B-�鿴����ͨ���ĳɹ���¼");
		System.out.println("C-�鿴δͨ������ĳɹ���¼");
		System.out.println("D-�˳�");
		String c="";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("������˵�ѡ�");
			if(scanner.hasNext()){
			    c = scanner.next();//�����ȴ��û��������
			}
			while(!c.contentEquals("A")&&!c.contentEquals("B")&&!c.contentEquals("C")&&!c.contentEquals("D")) {
				System.out.println("�������벻��ȷ�����������룺");
				c = scanner.next();
			}
			if(c.contentEquals("D")) {
				break;
			}
			if(c.contentEquals("A")) {
				System.out.println("1-���������֤");
				System.out.println("2-��˽�����֤");
				System.out.println("3-��˱�׼��֤");
				System.out.println("4-��˱�����֤");
				System.out.println("5-���ר����֤");
				System.out.println("6-�����Ӳ��ƽ̨��֤");
				System.out.println("7-��˽̲���֤");
				System.out.println("0-�˳����");
				String choice="";
				while(true) {
					System.out.println("���������ѡ�");
					if(scanner.hasNext()){
					    choice = scanner.next();//�����ȴ��û��������
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("�������벻��ȷ�����������룺");
						choice = scanner.next();
					}
					if(choice.contentEquals("0")) {
						break;
					}
					if(choice.contentEquals("1")) {
						System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
						List<Thesis> lt= DAOFactory.getInstance().getThesisDAO().findThesis(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("1")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
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
						System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
						List<Reward> lt=DAOFactory .getInstance().getRewardDAO().findReward(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("1")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
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
						System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
						List<Standard> lt=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 4);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("1")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
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
							if(lt.get(k).getStatus().contentEquals("��������ͬ��")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("��������ͬ��");
									DAOFactory .getInstance().getReportBase().updateReport(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("���������ܾ�");
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
							if(lt.get(k).getTstatus().contentEquals("��������ͬ��")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setTstatus("��������ͬ��");
									DAOFactory .getInstance().getPatentBase().updatePatent(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setTstatus("���������ܾ�");
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
							if(lt.get(k).getStatus().contentEquals("��������ͬ��")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("��������ͬ��");
									DAOFactory .getInstance().getProveBase().updateProve(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("���������ܾ�");
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
							if(lt.get(k).getStatus().contentEquals("��������ͬ��")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("��������ͬ��");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("���������ܾ�");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}
							}
							k++;
						}
					}
				}
			}
			if(c.contentEquals("B")) {
				System.out.println("��ͨ������������б�");
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 4);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("2")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("��ͨ������Ľ����б�");
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 4);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("2")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("��ͨ������ı�׼�б�");
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 4);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("2")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("��ͨ������ı����б�");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 4);
				int k4=0;
				while(k4<lt4.size()) {
					if(lt4.get(k4).getStatus().contentEquals("��������ͨ��")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("��ͨ�������ר���б�");
				List<Patent> lt5=DAOFactory .getInstance().getPatentBase().findPatent(userid, 4);
				int k5=0;
				while(k5<lt5.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("��������ͨ��")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("��ͨ���������Ӳ��ƽ̨��֤�б�");
				List<Prove> lt6=DAOFactory .getInstance().getProveBase().findProve(userid, 4);
				int k6=0;
				while(k6<lt6.size()) {
					if(lt6.get(k6).getStatus().contentEquals("��������ͨ��")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("��ͨ������Ľ̲��б�");
				List<Textbook> lt7=DAOFactory .getInstance().getTextbookBase().findTextbook(userid, 4);
				int k7=0;
				while(k7<lt7.size()) {
					if(lt7.get(k7).getStatus().contentEquals("��������ͨ��")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
			if(c.contentEquals("C")) {
				System.out.println("δͨ������������б�");
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 4);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("4")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("δͨ������Ľ����б�");
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 4);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("4")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("δͨ������ı�׼�б�");
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 4);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("4")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("δͨ������ı����б�");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 4);
				int k4=0;
				while(k4<lt4.size()) {
					if(lt4.get(k4).getStatus().contentEquals("���������ܾ�")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("δͨ�������ר���б�");
				List<Patent> lt5=DAOFactory .getInstance().getPatentBase().findPatent(userid, 4);
				int k5=0;
				while(k5<lt5.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("���������ܾ�")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("δͨ���������Ӳ��ƽ̨��֤�б�");
				List<Prove> lt6=DAOFactory .getInstance().getProveBase().findProve(userid, 4);
				int k6=0;
				while(k6<lt6.size()) {
					if(lt6.get(k6).getStatus().contentEquals("���������ܾ�")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("δͨ������Ľ̲��б�");
				List<Textbook> lt7=DAOFactory .getInstance().getTextbookBase().findTextbook(userid, 4);
				int k7=0;
				while(k7<lt7.size()) {
					if(lt7.get(k7).getStatus().contentEquals("���������ܾ�")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
		}
	}
	public static void leaderMenu(String userid) {
		//�鿴��ѧ�����ѱ��϶��ĳɹ���״̬Ϊ2�ļ�¼
		System.out.println("��ѯ�о����ɹ��϶����");
		System.out.println("1-��ѯ������֤���");
		System.out.println("2-��ѯ������֤���");
		System.out.println("3-��ѯ��׼��֤���");
		System.out.println("4-��ѯ������֤���");
		System.out.println("5-��ѯר����֤���");
		System.out.println("6-��ѯ��Ӳ��ƽ̨��֤���");
		System.out.println("7-��ѯ�̲���֤���");
		System.out.println("0-�˳���ѯ");
		Scanner scanner = new Scanner(System.in);
		String c="";
		while(true) {
			System.out.println("������ѡ�");
			if(scanner.hasNext()){
			    c = scanner.next();//�����ȴ��û��������
			}
			while(!c.contentEquals("1")&&!c.contentEquals("2")&&!c.contentEquals("3")&&!c.contentEquals("4")&&!c.contentEquals("5")&&!c.contentEquals("6")&&!c.contentEquals("7")&&!c.contentEquals("0")) {
				System.out.println("�������벻��ȷ�����������룺");
				c = scanner.next();
			}
			if(c.contentEquals("0")) {
				break;
			}
			if(c.contentEquals("1")) {
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
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
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
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
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
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
					if(lt.get(k).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("5")) {
				List<Patent> lt=DAOFactory.getInstance().getPatentBase().findPatent(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getTstatus().contentEquals("��������ͬ��")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("6")) {
				List<Prove> lt=DAOFactory.getInstance().getProveBase().findProve(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
			if(c.contentEquals("7")) {
				List<Textbook> lt=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 2);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
			}
		}
	}
	public static void mentorMenu(String userid) {
		//ɸѡδ��˵ļ�¼��״̬Ϊ0�ļ�¼�������и���Ϊ1ͨ������3�ܾ�
		//�鿴��ʦ��˽׶Σ�ͨ���ļ�¼��״̬Ϊ1
		//�鿴��ʦ��˽׶Σ��ܾ��ļ�¼��״̬Ϊ3
		//�鿴�ѱ���֤�Ľ����״̬Ϊ2�ļ�¼
		System.out.println("A-�Ա����о����ɹ��϶����г���");
		System.out.println("B-�鿴�����о�������ͨ���ĳɹ���¼");
		System.out.println("C-�鿴�����о���δͨ������ĳɹ���¼");
		System.out.println("D-�鿴�����о���ͨ������ĳɹ���¼");
		System.out.println("E-�˳�");
		String c="";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("������˵�ѡ�");
			if(scanner.hasNext()){
			    c = scanner.next();//�����ȴ��û��������
			}
			while(!c.contentEquals("A")&&!c.contentEquals("B")&&!c.contentEquals("C")&&!c.contentEquals("D")&&!c.contentEquals("E")) {
				System.out.println("�������벻��ȷ�����������룺");
				c = scanner.next();
			}
			if(c.contentEquals("E")) {
				break;
			}
			if(c.contentEquals("A")) {
				System.out.println("1-���������֤");
				System.out.println("2-��˽�����֤");
				System.out.println("3-��˱�׼��֤");
				System.out.println("4-��˱�����֤");
				System.out.println("5-���ר����֤");
				System.out.println("6-�����Ӳ��ƽ̨��֤");
				System.out.println("7-��˽̲���֤");
				System.out.println("0-�˳����");
				String choice="";
				while(true) {
					System.out.println("���������ѡ�");
					if(scanner.hasNext()){
					    choice = scanner.next();//�����ȴ��û��������
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("�������벻��ȷ�����������룺");
						choice = scanner.next();
					}
					if(choice.contentEquals("0")) {
						break;
					}
					if(choice.contentEquals("1")) {
						System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
						List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("0")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
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
						System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
						List<Reward> lt=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("0")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
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
						System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
						List<Standard> lt=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 3);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("0")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
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
							if(lt.get(k).getStatus().contentEquals("δ���")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("��������ͬ��");
									DAOFactory .getInstance().getReportBase().updateReport(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("���������ܾ�");
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
							if(lt.get(k).getTstatus().contentEquals("δ���")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setTstatus("��������ͬ��");
									DAOFactory .getInstance().getPatentBase().updatePatent(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setTstatus("���������ܾ�");
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
							if(lt.get(k).getStatus().contentEquals("δ���")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("��������ͬ��");
									DAOFactory .getInstance().getProveBase().updateProve(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("���������ܾ�");
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
							if(lt.get(k).getStatus().contentEquals("δ���")) {
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									lt.get(k).setStatus("��������ͬ��");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}else if(inchoice.contentEquals("0")) {
									lt.get(k).setStatus("���������ܾ�");
									DAOFactory .getInstance().getTextbookBase().updateTextbook(lt.get(k));
								}
							}
							k++;
						}
					}
				}
			}
			if(c.contentEquals("B")) {
				System.out.println("��ͨ������������б�");
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("1")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("��ͨ������Ľ����б�");
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("1")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("��ͨ������ı�׼�б�");
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Standard> lt3=DAOFactory.getInstance().getStandardDAO().findStandard(userid, 3);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("1")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("��ͨ������ı����б�");
				List<Report> lt4=DAOFactory.getInstance().getReportBase().findReport(userid, 3);
				int k4=0;
				while(k4<lt.size()) {
					if(lt4.get(k4).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("��ͨ�������ר���б�");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 3);
				int k5=0;
				while(k5<lt.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("��������ͬ��")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("��ͨ���������Ӳ��ƽ̨��֤�б�");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 3);
				int k6=0;
				while(k6<lt.size()) {
					if(lt6.get(k6).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("��ͨ������Ľ̲��б�");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 3);
				int k7=0;
				while(k7<lt.size()) {
					if(lt7.get(k7).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
			if(c.contentEquals("C")) {
				System.out.println("δͨ������������б�");
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("3")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("δͨ������Ľ����б�");
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("3")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("δͨ������ı�׼�б�");
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 3);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("3")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("δͨ������ı����б�");
				List<Report> lt4=DAOFactory.getInstance().getReportBase().findReport(userid, 3);
				int k4=0;
				while(k4<lt.size()) {
					if(lt4.get(k4).getStatus().contentEquals("���������ܾ�")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("δͨ�������ר���б�");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 3);
				int k5=0;
				while(k5<lt.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("���������ܾ�")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("δͨ���������Ӳ��ƽ̨��֤�б�");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 3);
				int k6=0;
				while(k6<lt.size()) {
					if(lt6.get(k6).getStatus().contentEquals("���������ܾ�")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("δͨ������Ľ̲��б�");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 3);
				int k7=0;
				while(k7<lt.size()) {
					if(lt7.get(k7).getStatus().contentEquals("���������ܾ�")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
			if(c.contentEquals("D")) {
				System.out.println("��ͨ������������б�");
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 3);
				int k=0;
				while(k<lt.size()) {
					if(lt.get(k).getState().contentEquals("2")) {
						System.out.println(lt.get(k));
					}
					k++;
				}
				System.out.println("��ͨ������Ľ����б�");
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 3);
				int k2=0;
				while(k2<lt2.size()) {
					if(lt2.get(k2).getState().contentEquals("2")) {
						System.out.println(lt2.get(k2));
					}
					k2++;
				}
				System.out.println("��ͨ������ı�׼�б�");
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 3);
				int k3=0;
				while(k3<lt3.size()) {
					if(lt3.get(k3).getState().contentEquals("2")) {
						System.out.println(lt3.get(k3));
					}
					k3++;
				}
				System.out.println("��ͨ������ı����б�");
				List<Report> lt4=DAOFactory.getInstance().getReportBase().findReport(userid, 3);
				int k4=0;
				while(k4<lt.size()) {
					if(lt4.get(k4).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt4.get(k4));
					}
					k4++;
				}
				System.out.println("��ͨ�������ר���б�");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 3);
				int k5=0;
				while(k5<lt.size()) {
					if(lt5.get(k5).getTstatus().contentEquals("��������ͬ��")) {
						System.out.println(lt5.get(k5));
					}
					k5++;
				}
				System.out.println("��ͨ���������Ӳ��ƽ̨��֤�б�");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 3);
				int k6=0;
				while(k6<lt.size()) {
					if(lt6.get(k6).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt6.get(k6));
					}
					k6++;
				}
				System.out.println("��ͨ������Ľ̲��б�");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 3);
				int k7=0;
				while(k7<lt.size()) {
					if(lt7.get(k7).getStatus().contentEquals("��������ͬ��")) {
						System.out.println(lt7.get(k7));
					}
					k7++;
				}
			}
		}
	}
	public static void studentMenu(String userid) {
		//�ύ��֤���룬�˴�����ʱ���״̬��Ϊ0
		//�鿴���ύ������
		//�޸ı��ܾ������룬ɸѡ״̬Ϊ3��4�ļ�¼�����и���
		//�������룬ɾ��
		System.out.println("A-�ύ�ɹ��϶�����");
		System.out.println("B-�鿴���ύ������˽���");
		System.out.println("C-�޸ı��˻ص�����");
		System.out.println("D-���سɹ��϶�����");
		System.out.println("E-�˳�");
		String c="";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("������˵�ѡ�");
			if(scanner.hasNext()){
			    c = scanner.next();//�����ȴ��û��������
			}
			while(!c.contentEquals("A")&&!c.contentEquals("B")&&!c.contentEquals("C")&&!c.contentEquals("D")&&!c.contentEquals("E")) {
				System.out.println("�������벻��ȷ�����������룺");
				c = scanner.next();
			}
			if(c.contentEquals("E")) {
				break;
			}
			if(c.contentEquals("A")) {
				System.out.println("1-�ύ������֤����");
				System.out.println("2-�ύ������֤����");
				System.out.println("3-�ύ��׼��֤����");
				System.out.println("4-�ύ������֤����");
				System.out.println("5-�ύר����֤����");
				System.out.println("6-�ύ��Ӳ��ƽ̨��֤����");
				System.out.println("7-�ύ�̲���֤����");
				System.out.println("0-�˳��������");
				String choice="";
				while(true) {
					System.out.println("����������ѡ�");
					if(scanner.hasNext()){
					    choice = scanner.next();//�����ȴ��û��������
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("�������벻��ȷ�����������룺");
						choice = scanner.next();
					}
					if(choice.contentEquals("0")) {
						break;
					}
					if(choice.contentEquals("1")) {
						System.out.printf("���������������������֤������");
						while (!scanner.hasNextInt()){
						    System.out.println("������������");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Thesis t=new Thesis();
							System.out.printf("�������ƣ�");
							t.setName(scanner.nextLine());
							System.out.printf("���ķ��������ƣ�");
							t.setPubname(scanner.nextLine());
							System.out.printf("���ķ���ʱ�䣺yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
								tmp=scanner.nextLine();
							}
							t.setPubtime(tmp);
							System.out.printf("���ķ���״̬��(¼��δ����/�ѷ���)");
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("¼��δ����")&&!tmp.contentEquals("�ѷ���")) {
								System.out.println("������¼��δ����/�ѷ���");
								tmp=scanner.nextLine();
							}
							t.setPubstate(tmp);
							System.out.printf("�����������ͣ�");
							t.setIndextype(scanner.nextLine());
							System.out.printf("���Ĺ����������(ѧԺ���������Ŀ�/ѧԺ�������Ŀ�)");
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("ѧԺ���������Ŀ�")&&!tmp.contentEquals("ѧԺ�������Ŀ�")) {
								System.out.println("������ѧԺ���������Ŀ�/ѧԺ�������Ŀ⣡");
								tmp=scanner.nextLine();
							}
							t.setBase(scanner.nextLine());
							System.out.printf("����ɨ���PDF�ļ���");
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
							System.out.println("���ύ���룡");
						}
					}
					if(choice.contentEquals("2")) {
						System.out.printf("��������������Ľ�����֤������");
						while (!scanner.hasNextInt()){
						    System.out.println("������������");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Reward t=new Reward();
							System.out.printf("�������ƣ�");
							t.setName(scanner.nextLine());
							System.out.printf("�����ȼ���(���Ҽ�/ʡ����/�м�/����)");
							String tmp="";
							while(!tmp.contentEquals("���Ҽ�")&&!tmp.contentEquals("ʡ����")&&!tmp.contentEquals("�м�")&&!tmp.contentEquals("����")) {
								System.out.println("������(���Ҽ�/ʡ����/�м�/����)��");
								tmp=scanner.nextLine();
							}
							t.setRelevel(tmp);
							System.out.printf("�񽱵ȼ���(�صȽ�/һ�Ƚ�/���Ƚ�/���Ƚ�)");
							tmp="";
							while(!tmp.contentEquals("�صȽ�")&&!tmp.contentEquals("һ�Ƚ�")&&!tmp.contentEquals("���Ƚ�")&&!tmp.contentEquals("���Ƚ�")) {
								System.out.println("������(�صȽ�/һ�Ƚ�/���Ƚ�/���Ƚ�)��");
								tmp=scanner.nextLine();
							}
							t.setGetlevel(tmp);
							System.out.printf("������)");
							t.setGetlevel(scanner.nextLine());
							System.out.printf("��ʱ�䣺yyyy-MM-dd");
							tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
								tmp=scanner.nextLine();
							}
							t.setGettime(tmp);
							System.out.printf("��֤����");
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
							System.out.println("���ύ���룡");
						}
					}
					if(choice.contentEquals("3")) {
						System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
						System.out.printf("��������������ı�׼��֤������");
						while (!scanner.hasNextInt()){
						    System.out.println("������������");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Standard t=new Standard();
							System.out.printf("��׼���ƣ�");
							t.setName(scanner.nextLine());
							System.out.printf("��׼�ȼ���(���ұ�׼/ʡ�����ط���׼/��ҵ��׼/�Ŷӱ�׼)");
							String tmp="";
							while(!tmp.contentEquals("���ұ�׼")&&!tmp.contentEquals("ʡ�����ط���׼")&&!tmp.contentEquals("��ҵ��׼")&&!tmp.contentEquals("�Ŷӱ�׼")) {
								System.out.println("������(���ұ�׼/ʡ�����ط���׼/��ҵ��׼/�Ŷӱ�׼)��");
								tmp=scanner.nextLine();
							}
							t.setGetlevel(tmp);
							System.out.printf("��׼����ʱ�䣺yyyy-MM-dd");
							tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
								tmp=scanner.nextLine();
							}
							t.setPubtime(tmp);
							System.out.printf("��֤����");
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
							System.out.println("���ύ���룡");
						}
					}
					if(choice.contentEquals("4")) {
						System.out.printf("��������������ı�����֤������");
						while (!scanner.hasNextInt()){
							System.out.println("������������");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Report t=new Report();
							System.out.printf("�������ƣ�");
							t.setName(scanner.nextLine());
							System.out.printf("�������λ��");
							t.setAddress(scanner.nextLine());
							System.out.printf("����ʱ�䣺yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("�������ͣ�(�滮��/�����/������/����)");
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("�滮��")&&!tmp.contentEquals("�����")&&!tmp.contentEquals("������")&&!tmp.contentEquals("����")) {
								System.out.println("������ �滮��/�����/������/����");
								tmp=scanner.nextLine();
							}
							t.setType(tmp);
							System.out.printf("���׶���������������");
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("��֤���ϣ�");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setStatus("δ���");
							List<Report> ltl=DAOFactory.getInstance().getReportBase().findReport(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory.getInstance().getReportBase().addReport(t);
							System.out.println("���ύ���룡");
						}
					}
					if(choice.contentEquals("5")) {
						System.out.printf("���������������ר����֤������");
						while (!scanner.hasNextInt()){			//����³����
							System.out.println("������������");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Patent t=new Patent();
							System.out.printf("ר�����ƣ�");			//����ר������
							t.setName(scanner.nextLine());
							System.out.printf("ר���ţ�");			//����ר����
							t.setId(scanner.nextLine());
							System.out.printf("ר������ʱ�䣺yyyy-MM-dd");		//����ר������ʱ��
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("ר������(����ר��/ʵ������ר��)��");		//����ר������
							tmp=scanner.nextLine();
							while(!tmp.contentEquals("����ר��")&&!tmp.contentEquals("ʵ������ר��")) {
								System.out.println("������ ����ר��/ʵ������ר��");
								tmp=scanner.nextLine();
							}
							t.setType(tmp);
							System.out.printf("���׶���������������");		//���빱�׶�����
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("ר��״̬��");		//����ר��״̬
							t.setStatus(scanner.nextLine());
							System.out.printf("��֤���ϣ�");		//������֤����
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setTstatus("δ���");				//�������״̬Ϊδ�ύ
							DAOFactory.getInstance().getPatentBase().addPatent(t);		//������ӵ�����
							System.out.println("���ύ���룡");
						}
					}
					if(choice.contentEquals("6")) {
						System.out.printf("�����������������Ӳ��ƽ̨��֤������");
						while (!scanner.hasNextInt()){
							System.out.println("������������");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Prove t=new Prove();
							System.out.printf("ƽ̨���ƣ�");
							t.setName(scanner.nextLine());
							System.out.printf("ƽ̨����λ��");
							t.setAddress(scanner.nextLine());
							System.out.printf("ƽ̨����ʱ�䣺yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("���׶���������������");
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("��֤���ϣ�");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setStatus("δ���");
							List<Prove> ltl=DAOFactory.getInstance().getProveBase().findProve(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory.getInstance().getProveBase().addProve(t);
							System.out.println("���ύ���룡");
						}
					}
					if(choice.contentEquals("7")) {
						System.out.printf("��������������Ľ̲���֤������");
						while (!scanner.hasNextInt()){
							System.out.println("������������");
						}
						int count=scanner.nextInt();
						int j=0;
						for(j=0;j<count;j++) {
							Textbook t=new Textbook();
							System.out.printf("�̲����ƣ�");
							t.setName(scanner.nextLine());
							System.out.printf("�̲ĳ����磺");
							t.setPress(scanner.nextLine());
							System.out.printf("�̲ĳ���ʱ�䣺yyyy-MM-dd");
							String tmp=scanner.nextLine();
							while(!isValidDate(tmp)) {
								System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
								tmp=scanner.nextLine();
							}
							t.setTime(tmp);
							System.out.printf("���׶���������������");
							t.setContribution(Integer.parseInt(scanner.nextLine()));
							System.out.printf("��֤���ϣ�");
							t.setMaterial(scanner.nextLine());
							t.setSid(userid);
							t.setStatus("δ���");
							List<Textbook> ltl=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 4);
							String id="";
							if(ltl==null||ltl.size()==0) {
								id="1";
							}else {
								id=String.valueOf(Integer.valueOf(ltl.get(ltl.size()-1).getId())+1);
							}
							t.setId(id);
							DAOFactory.getInstance().getTextbookBase().addTextbook(t);
							System.out.println("���ύ���룡");
						}
					}
				}
			}
			if(c.contentEquals("B")) {
				System.out.println("������֤����");
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 1);
				int k=0;
				while(k<lt.size()) {
					System.out.println(lt.get(k));
					k++;
				}
				System.out.println("������֤����");
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 1);
				int k2=0;
				while(k2<lt2.size()) {
					System.out.println(lt2.get(k2));
					k2++;
				}
				System.out.println("��׼��֤����");
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 1);
				int k3=0;
				while(k3<lt3.size()) {
					System.out.println(lt3.get(k3));
					k3++;
				}
				System.out.println("������֤����");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 1);
				int k4=0;
				while(k4<lt4.size()) {
					System.out.println(lt4.get(k4));
					k4++;
				}
				System.out.println("������֤����");
				List<Patent> lt5=DAOFactory .getInstance().getPatentBase().findPatent(userid, 1);
				int k5=0;
				while(k5<lt5.size()) {
					System.out.println(lt5.get(k5));
					k5++;
				}
				System.out.println("��Ӳ��ƽ̨��֤����");
				List<Prove> lt6=DAOFactory .getInstance().getProveBase().findProve(userid, 4);
				int k6=0;
				while(k6<lt6.size()) {
					System.out.println(lt6.get(k6));
					k6++;
				}
				System.out.println("�̲���֤����");
				List<Textbook> lt7=DAOFactory .getInstance().getTextbookBase().findTextbook(userid, 4);
				int k7=0;
				while(k7<lt7.size()) {
					System.out.println(lt7.get(k7));
					k7++;
				}
			}
			if(c.contentEquals("C")) {
				System.out.println("1-�޸�������֤����");
				System.out.println("2-�޸Ľ�����֤����");
				System.out.println("3-�޸ı�׼��֤����");
				System.out.println("4-�޸ı�����֤����");
				System.out.println("5-�޸�ר����֤����");
				System.out.println("6-�޸���Ӳ��ƽ̨��֤����");
				System.out.println("7-�޸Ľ̲���֤����");
				System.out.println("0-�˳��������");
				String choice="";
				while(true) {
					System.out.println("�������޸�ѡ�");
					if(scanner.hasNext()){
					    choice = scanner.next();//�����ȴ��û��������
					}
					while(!choice.contentEquals("1")&&!choice.contentEquals("2")&&!choice.contentEquals("3")&&!choice.contentEquals("4")&&!choice.contentEquals("5")&&!choice.contentEquals("6")&&!choice.contentEquals("7")&&!choice.contentEquals("0")) {
						System.out.println("�������벻��ȷ�����������룺");
						choice = scanner.next();
					}
					if(choice.contentEquals("1")) {
						List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 1);
						int k=0;
						System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("3")||lt.get(k).getState().contentEquals("4")) {
								Thesis t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺�޸�-1������-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("�������޸�����ţ�1-�������� 2-���ķ��������� 3-����ʱ�� 4-����״̬ 5-�������� 6-���Ĺ������ 7-����ɨ���PDF���� 0-�˳��Ե�ǰ����޸ģ�");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
										    inc = scanner.next();//�����ȴ��û��������
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("7")&&!inc.contentEquals("0")) {
											System.out.println("�������벻��ȷ�����������룺");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("�������ƣ�");
											t.setName(scanner.nextLine());
											
										}
										if(choice.contentEquals("2")) {
											System.out.printf("���ķ��������ƣ�");
											t.setPubname(scanner.nextLine());
										}
										if(choice.contentEquals("3")) {
											System.out.printf("���ķ���ʱ�䣺yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
												tmp=scanner.nextLine();
											}
											t.setPubtime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("���ķ���״̬��(¼��δ����/�ѷ���)");
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("¼��δ����")&&!tmp.contentEquals("�ѷ���")) {
												System.out.println("������¼��δ����/�ѷ���");
												tmp=scanner.nextLine();
											}
											t.setPubstate(tmp);
										}
										if(choice.contentEquals("5")) {
											System.out.printf("�����������ͣ�");
											t.setIndextype(scanner.nextLine());
										}
										if(choice.contentEquals("6")) {
											System.out.printf("���Ĺ����������(ѧԺ���������Ŀ�/ѧԺ�������Ŀ�)");
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("ѧԺ���������Ŀ�")&&!tmp.contentEquals("ѧԺ�������Ŀ�")) {
												System.out.println("������ѧԺ���������Ŀ�/ѧԺ�������Ŀ⣡");
												tmp=scanner.nextLine();
											}
											t.setBase(scanner.nextLine());
										}
										if(choice.contentEquals("7")) {
											System.out.printf("����ɨ���PDF�ļ���");
											t.setMaterial(scanner.nextLine());
										}										
									}
									t.setState("0");
									DAOFactory .getInstance().getThesisDAO().updateThesis(t);
									System.out.println("�޸ĳɹ���");
								}
								k++;
							}
						}
					}
					
					if(choice.contentEquals("2")) {
						System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
						List<Reward> lt=DAOFactory .getInstance().getRewardDAO().findReward(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("3")||lt.get(k).getState().contentEquals("4")) {
								Reward t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("�������޸�����ţ�1-�������� 2�����ȼ� 3-�񽱵ȼ� 4-���� 5-��ʱ�� 6-��֤���� 0-�˳��Ե�ǰ����޸ģ�");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
										    inc = scanner.next();//�����ȴ��û��������
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("0")) {
											System.out.println("�������벻��ȷ�����������룺");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("�������ƣ�");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("�����ȼ���(���Ҽ�/ʡ����/�м�/����)");
											String tmp="";
											while(!tmp.contentEquals("���Ҽ�")&&!tmp.contentEquals("ʡ����")&&!tmp.contentEquals("�м�")&&!tmp.contentEquals("����")) {
												System.out.println("������(���Ҽ�/ʡ����/�м�/����)��");
												tmp=scanner.nextLine();
											}
											t.setRelevel(tmp);
										}
										if(choice.contentEquals("3")) {
											System.out.printf("������)");
											t.setGetlevel(scanner.nextLine());
										}
										if(choice.contentEquals("4")) {
											System.out.printf("�񽱵ȼ���(�صȽ�/һ�Ƚ�/���Ƚ�/���Ƚ�)");
											String tmp="";
											while(!tmp.contentEquals("�صȽ�")&&!tmp.contentEquals("һ�Ƚ�")&&!tmp.contentEquals("���Ƚ�")&&!tmp.contentEquals("���Ƚ�")) {
												System.out.println("������(�صȽ�/һ�Ƚ�/���Ƚ�/���Ƚ�)��");
												tmp=scanner.nextLine();
											}
											t.setGetlevel(tmp);
										}
										if(choice.contentEquals("5")) {
											System.out.printf("��ʱ�䣺yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
												tmp=scanner.nextLine();
											}
											t.setGettime(tmp);
										}
										if(choice.contentEquals("6")) {
											System.out.printf("��֤����");
											t.setMaterial(scanner.nextLine());
										}										
									}
									t.setState("0");
									DAOFactory .getInstance().getRewardDAO().updateReward(t);
									System.out.println("�޸ĳɹ���");
								}
							}
							k++;
						}
					}
					if(choice.contentEquals("3")) {
						System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
						List<Standard> lt=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getState().contentEquals("3")||lt.get(k).getState().contentEquals("4")) {
								Standard t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺ͬ��-1���ܾ�-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									System.out.println("�������޸�����ţ�1-��׼���� 2��׼���� 3-��׼����ʱ�� 4-��֤���� 0-�˳��Ե�ǰ����޸ģ�");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
										    inc = scanner.next();//�����ȴ��û��������
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("0")) {
											System.out.println("�������벻��ȷ�����������룺");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("��׼���ƣ�");
											t.setName(scanner.nextLine());
											
											
											
										}
										if(choice.contentEquals("2")) {
											System.out.printf("��׼�ȼ���(���ұ�׼/ʡ�����ط���׼/��ҵ��׼/�Ŷӱ�׼)");
											String tmp="";
											while(!tmp.contentEquals("���ұ�׼")&&!tmp.contentEquals("ʡ�����ط���׼")&&!tmp.contentEquals("��ҵ��׼")&&!tmp.contentEquals("�Ŷӱ�׼")) {
												System.out.println("������(���ұ�׼/ʡ�����ط���׼/��ҵ��׼/�Ŷӱ�׼)��");
												tmp=scanner.nextLine();
											}
											t.setGetlevel(tmp);
										}
										if(choice.contentEquals("3")) {
											System.out.printf("��׼����ʱ�䣺yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
												tmp=scanner.nextLine();
											}
											t.setPubtime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("��֤����");
											t.setMaterial(scanner.nextLine());
										}
									t.setState("0");
									DAOFactory .getInstance().getStandardDAO().updateStandard(t);
									System.out.println("�޸ĳɹ���");
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
							if(lt.get(k).getStatus().contentEquals("���������ܾ�")||lt.get(k).getStatus().contentEquals("���������ܾ�")) {
								Report t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺�޸�-1������-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("�������޸�����ţ�1-�������� 2-�������� 3-����ʱ�� 4-�������λ 5-���׶����� 6-��֤���� 0-�˳��Ե�ǰ����޸ģ�");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();//�����ȴ��û��������
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("0")) {
											System.out.println("�������벻��ȷ�����������룺");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("�������ƣ�");
											t.setName(scanner.nextLine());

										}
										if(choice.contentEquals("2")) {
											System.out.printf("�������ͣ�(�滮��/�����/������/����)");
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("�滮��")&&!tmp.contentEquals("�����")&&!tmp.contentEquals("������")&&!tmp.contentEquals("����")) {
												System.out.println("������ �滮��/�����/������/������");
												tmp=scanner.nextLine();
											}
											t.setType(tmp);
										}
										if(choice.contentEquals("3")) {
											System.out.printf("����ʱ�䣺yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("�������λ��");
											t.setAddress(scanner.nextLine());
										}
										if(choice.contentEquals("5")) {
											System.out.printf("���׶���������������");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("6")) {
											System.out.printf("��֤���ϣ�");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("δ���");
									DAOFactory.getInstance().getReportBase().updateReport(t);
									System.out.println("�޸ĳɹ���");
								}
								k++;
							}
						}
					}
					if(choice.contentEquals("5")) {
						List<Patent> lt=DAOFactory.getInstance().getPatentBase().findPatent(userid, 1);	//��ȡ��ѧ�����µ�����ר��
						int k=0;
						while(k<lt.size()) {			//�����б�
							if(lt.get(k).getTstatus().contentEquals("���������ܾ�")||lt.get(k).getTstatus().contentEquals("���������ܾ�")) {			//״̬λΪ�����
								Patent t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺�޸�-1������-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){		//����³����
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("�������޸�����ţ�1-ר������ 2-ר������ 3-ר������ʱ�� 4-ר��״̬ 5-���׶����� 6-��֤���� 0-�˳��Ե�ǰ����޸ģ�");  //ѡ���޸ĵ��ֶ�
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();				//�����ȴ��û��������
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("6")&&!inc.contentEquals("0")) {
											System.out.println("�������벻��ȷ�����������룺");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {				//��ר�����ƽ����޸�
											System.out.printf("ר�����ƣ�");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("ר�����ͣ�(����ר��/ʵ������ר��)");		//��ר�����ͽ����޸�
											String tmp=scanner.nextLine();
											while(!tmp.contentEquals("����ר��")&&!tmp.contentEquals("ʵ������ר��")) {	//��ר�����ͽ��п���
												System.out.println("������ ����ר��/ʵ������ר��");
												tmp=scanner.nextLine();
											}
											t.setType(tmp);
										}
										if(choice.contentEquals("3")) {				//��ר��ʱ������޸�
											System.out.printf("ר������ʱ�䣺yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {				//�������ʽ�����ж�
												System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {				//��ר��״̬�����޸�
											System.out.printf("ר��״̬��");
											t.setStatus(scanner.nextLine());
										}
										if(choice.contentEquals("5")) {				//�Թ��׶����������޸�
											System.out.printf("���׶���������������");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("6")) {				//����֤���Ͻ����޸�
											System.out.printf("��֤���ϣ�");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("δ���");									//�����״̬��Ϊδ��ˣ��ӳ���ʼ
									DAOFactory.getInstance().getPatentBase().updatePatent(t);	//����DAO�����״̬
									System.out.println("�޸ĳɹ���");
								}
								k++;
							}
						}
					}
					if(choice.contentEquals("6")) {
						List<Prove> lt=DAOFactory.getInstance().getProveBase().findProve(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("���������ܾ�")||lt.get(k).getStatus().contentEquals("���������ܾ�")) {
								Prove t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺�޸�-1������-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("�������޸�����ţ�1-��Ӳ��ƽ̨���� 2-ƽ̨����λ 3-ƽ̨����ʱ�� 4-���׶����� 5-��֤���� 0-�˳��Ե�ǰ����޸ģ�");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();//�����ȴ��û��������
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("0")) {
											System.out.println("�������벻��ȷ�����������룺");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("��Ӳ��ƽ̨���ƣ�");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("ƽ̨����λ��");
											t.setAddress(scanner.nextLine());
										}
										if(choice.contentEquals("3")) {
											System.out.printf("ƽ̨����ʱ�䣺yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("���׶���������������");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("5")) {
											System.out.printf("��֤���ϣ�");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("δ���");
									DAOFactory.getInstance().getProveBase().updateProve(t);
									System.out.println("�޸ĳɹ���");
								}
								k++;
							}
						}
					}
					if(choice.contentEquals("7")) {
						List<Textbook> lt=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 1);
						int k=0;
						while(k<lt.size()) {
							if(lt.get(k).getStatus().contentEquals("���������ܾ�")||lt.get(k).getStatus().contentEquals("���������ܾ�")) {
								Textbook t=lt.get(k);
								System.out.println(lt.get(k));
								String inchoice="";
								System.out.println("�����룺�޸�-1������-0");
								inchoice = scanner.next();
								while(!inchoice.contentEquals("1")&&!inchoice.contentEquals("0")){
									System.out.println("�����������������1��0��");
									inchoice = scanner.next();
								}
								if(inchoice.contentEquals("1")) {
									int count=0;
									System.out.println("�������޸�����ţ�1-�̲����� 2-�̲ĳ����� 3-�̲ĳ���ʱ�� 4-���׶����� 5-��֤���� 0-�˳��Ե�ǰ����޸ģ�");
									while(true) {
										String inc="";
										if(scanner.hasNext()){
											inc = scanner.next();//�����ȴ��û��������
										}
										while(!inc.contentEquals("1")&&!inc.contentEquals("2")&&!inc.contentEquals("3")&&!inc.contentEquals("4")&&!inc.contentEquals("5")&&!inc.contentEquals("0")) {
											System.out.println("�������벻��ȷ�����������룺");
											inc = scanner.next();
										}
										if(choice.contentEquals("0")) {
											break;
										}
										if(choice.contentEquals("1")) {
											System.out.printf("�̲����ƣ�");
											t.setName(scanner.nextLine());
										}
										if(choice.contentEquals("2")) {
											System.out.printf("�̲ĳ����磺");
											t.setPress(scanner.nextLine());
										}
										if(choice.contentEquals("3")) {
											System.out.printf("�̲ĳ���ʱ�䣺yyyy-MM-dd");
											String tmp=scanner.nextLine();
											while(!isValidDate(tmp)) {
												System.out.println("����yyyy-MM-dd��ʽ����ʱ�䣡");
												tmp=scanner.nextLine();
											}
											t.setTime(tmp);
										}
										if(choice.contentEquals("4")) {
											System.out.printf("���׶���������������");
											t.setContribution(Integer.parseInt(scanner.nextLine()));
										}
										if(choice.contentEquals("5")) {
											System.out.printf("��֤���ϣ�");
											t.setMaterial(scanner.nextLine());
										}
									}
									t.setStatus("δ���");
									DAOFactory.getInstance().getTextbookBase().updateTextbook(t);
									System.out.println("�޸ĳɹ���");
								}
								k++;
							}
						}
					}
				}
			}
			if(c.contentEquals("D")) {
				System.out.println("���ύ�����������֤�б�");
				System.out.println("��������\t���ķ���������\t����ʱ��\t����״̬\t��������\t���Ĺ��������\t����ɨ���PDF����\t������ѧ��\t���״̬");
				List<Thesis> lt=DAOFactory .getInstance().getThesisDAO().findThesis(userid, 1);
				int k=0;
				while(k<lt.size()) {
					System.out.println(k+1+"\t"+lt.get(k));
					k++;
				}
				System.out.printf("���������볷�ص�������֤������");
				while (!scanner.hasNextInt()){
				    System.out.println("������������");
				}
				int count=scanner.nextInt();
				while(count>lt.size()||count<0) {
					System.out.printf("������0-"+lt.size()+"�����֣�");
					while (!scanner.hasNextInt()){
					    System.out.println("������������");
					}
					count=scanner.nextInt();
				}
				int j=0;
				for(j=0;j<count;j++) {
					System.out.printf("���������볷�ص�������ţ�");
					while (!scanner.hasNextInt()){
					    System.out.println("������������");
					}
					int index=scanner.nextInt();
					while(index>lt.size()||index<1) {
						System.out.printf("������1-"+lt.size()+"�����֣�");
						while (!scanner.hasNextInt()){
						    System.out.println("������������");
						}
						index=scanner.nextInt();
					}
					DAOFactory .getInstance().getThesisDAO().deleteThesis(lt.get(index-1));
					System.out.println("���سɹ���");
				}
				
				System.out.println("���ύ����Ľ�����֤�б�");
				System.out.println("��������\t�����ȼ�\t�񽱵ȼ�\t����\t��ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Reward> lt2=DAOFactory .getInstance().getRewardDAO().findReward(userid, 1);
				int k2=0;
				while(k2<lt2.size()) {
					System.out.println(k2+1+"\t"+lt2.get(k2));
					k2++;
				}
				System.out.printf("���������볷�صĽ�����֤������");
				while (!scanner.hasNextInt()){
				    System.out.println("������������");
				}
				count=scanner.nextInt();
				while(count>lt2.size()||count<0) {
					System.out.printf("������0-"+lt2.size()+"�����֣�");
					while (!scanner.hasNextInt()){
					    System.out.println("������������");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("���������볷�صĽ�����ţ�");
					while (!scanner.hasNextInt()){
					    System.out.println("������������");
					}
					int index=scanner.nextInt();
					while(index>lt2.size()||index<1) {
						System.out.printf("������1-"+lt2.size()+"�����֣�");
						while (!scanner.hasNextInt()){
						    System.out.println("������������");
						}
						index=scanner.nextInt();
					}
					DAOFactory .getInstance().getRewardDAO().deleteReward(lt2.get(index-1));
					System.out.println("���سɹ���");
				}
				
				System.out.println("���ύ����ı�׼��֤�б�");
				System.out.println("��׼����\t��׼����\t��׼����ʱ��\t��֤����\t������ѧ��\t���״̬");
				List<Standard> lt3=DAOFactory .getInstance().getStandardDAO().findStandard(userid, 1);
				int k3=0;
				while(k3<lt3.size()) {
					System.out.println(k3+1+"\t"+lt3.get(k3));
					k3++;
				}
				System.out.printf("���������볷�صı�׼��֤������");
				while (!scanner.hasNextInt()){
				    System.out.println("������������");
				}
				count=scanner.nextInt();
				while(count>lt3.size()||count<0) {
					System.out.printf("������0-"+lt3.size()+"�����֣�");
					while (!scanner.hasNextInt()){
					    System.out.println("������������");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("���������볷�صı�׼��ţ�");
					while (!scanner.hasNextInt()){
					    System.out.println("������������");
					}
					int index=scanner.nextInt();
					while(index>lt.size()||index<1) {
						System.out.printf("������1-"+lt3.size()+"�����֣�");
						while (!scanner.hasNextInt()){
						    System.out.println("������������");
						}
						index=scanner.nextInt();
					}
					DAOFactory .getInstance().getStandardDAO().deleteStandard(lt3.get(index-1));
					System.out.println("���سɹ���");
				}

				System.out.println("���ύ����ı�����֤�б�");
				List<Report> lt4=DAOFactory .getInstance().getReportBase().findReport(userid, 1);
				int k4=0;
				while(k4<lt4.size()) {
					System.out.println(k4+1+"\t"+lt4.get(k4));
					k4++;
				}
				System.out.printf("���������볷�صı�����֤������");
				while (!scanner.hasNextInt()){
					System.out.println("������������");
				}
				count=scanner.nextInt();
				while(count>lt4.size()||count<0) {
					System.out.printf("������0-"+lt4.size()+"�����֣�");
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("���������볷�صı�����ţ�");
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					int index=scanner.nextInt();
					while(index>lt4.size()||index<1) {
						System.out.printf("������1-"+lt4.size()+"�����֣�");
						while (!scanner.hasNextInt()){
							System.out.println("������������");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getReportBase().deleteReport(lt4.get(index-1));
					System.out.println("���سɹ���");
				}
				System.out.println("���ύ�����ר����֤�б�");
				List<Patent> lt5=DAOFactory.getInstance().getPatentBase().findPatent(userid, 1);	//��ȡ���о������µ�ר���б�
				int k5=0;
				while(k5<lt5.size()) {
					System.out.println(k5+1+"\t"+lt5.get(k5));		//���ר���б�
					k5++;
				}
				System.out.printf("���������볷�ص�ר����֤������");
				while (!scanner.hasNextInt()){					//��������п���
					System.out.println("������������");
				}
				count=scanner.nextInt();
				while(count>lt5.size()||count<0) {
					System.out.printf("������0-"+lt5.size()+"�����֣�");	 //��������п���
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("���������볷�ص�ר����ţ�");			//��������п���
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					int index=scanner.nextInt();
					while(index>lt5.size()||index<1) {
						System.out.printf("������1-"+lt5.size()+"�����֣�");	//��������п���
						while (!scanner.hasNextInt()){
							System.out.println("������������");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getPatentBase().deletePatent(lt5.get(index-1));	//����DAO�㳷������
					System.out.println("���سɹ���");
				}

				System.out.println("���ύ�������Ӳ��ƽ̨��֤�б�");
				List<Prove> lt6=DAOFactory.getInstance().getProveBase().findProve(userid, 1);
				int k6=0;
				while(k6<lt6.size()) {
					System.out.println(k6+1+"\t"+lt6.get(k6));
					k6++;
				}
				System.out.printf("���������볷�ص���Ӳ��ƽ̨��֤������");
				while (!scanner.hasNextInt()){
					System.out.println("������������");
				}
				count=scanner.nextInt();
				while(count>lt6.size()||count<0) {
					System.out.printf("������0-"+lt6.size()+"�����֣�");
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("���������볷�ص���Ӳ��ƽ̨��֤��ţ�");
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					int index=scanner.nextInt();
					while(index>lt6.size()||index<1) {
						System.out.printf("������1-"+lt6.size()+"�����֣�");
						while (!scanner.hasNextInt()){
							System.out.println("������������");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getProveBase().deleteProve(lt6.get(index-1));
					System.out.println("���سɹ���");
				}


				System.out.println("���ύ����Ľ̲���֤�б�");
				List<Textbook> lt7=DAOFactory.getInstance().getTextbookBase().findTextbook(userid, 1);
				int k7=0;
				while(k7<lt7.size()) {
					System.out.println(k7+1+"\t"+lt7.get(k7));
					k7++;
				}
				System.out.printf("���������볷�صĽ̲���֤������");
				while (!scanner.hasNextInt()){
					System.out.println("������������");
				}
				count=scanner.nextInt();
				while(count>lt7.size()||count<0) {
					System.out.printf("������0-"+lt7.size()+"�����֣�");
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					count=scanner.nextInt();
				}
				j=0;
				for(j=0;j<count;j++) {
					System.out.printf("���������볷�صĽ̲���ţ�");
					while (!scanner.hasNextInt()){
						System.out.println("������������");
					}
					int index=scanner.nextInt();
					while(index>lt7.size()||index<1) {
						System.out.printf("������1-"+lt7.size()+"�����֣�");
						while (!scanner.hasNextInt()){
							System.out.println("������������");
						}
						index=scanner.nextInt();
					}
					DAOFactory.getInstance().getTextbookBase().deleteTextbook(lt7.get(index-1));
					System.out.println("���سɹ���");
				}
			}
		}
	}
	public static void achieveMenu(String userid,int role) {
		switch(role) {
		case 1:
			//�о�������Աͨ����ܾ��϶��ɹ�
			adminMenu(userid);
			break;
		case 2:
			//ѧ�Ƹ�����
			leaderMenu(userid);
			break;
		case 3:
			//�ڿν�ʦ
			System.out.println("�ڿν�ʦ��Ȩ�޲����ɹ��϶�������");
			break;
		case 4:
			//��ʦ
			mentorMenu(userid);
			break;
		case 5:
			//�о���
			studentMenu(userid);
			break;
		}
	}
}
