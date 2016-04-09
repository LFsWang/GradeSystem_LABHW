package grade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import grade.GradeSystem.NoSuchCommandExceptions;
import grade.GradeSystem.NoSuchIDExceptions;

public class UI
{
	public static class UserQuit extends Exception { }
	private DataManager dm;
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private String now_uid;
	private RowData cached_data;
	private long cached_avg;
	
	private String item_list[] = {
			"lab1      ",
			"lab2      ",
			"lab3      ",
			"mid-term  ",
			"final exam"
	};
	
	public UI(DataManager _dm)
	{
		dm=_dm;
		update();
	}
	
	public boolean isExitString(String s)
	{
		return s.toUpperCase().equals("E");
	}
	
	private boolean isQuitString(String s)
	{
		return s.toUpperCase().equals("Q");
	}
	
	private boolean YesNo(String s) throws NoSuchCommandExceptions
	{
		s = s.toUpperCase();
		if( s.equals("Y") )return true;
		if( s.equals("N") )return false;
		throw new NoSuchCommandExceptions();
	}
	
	public String getInputLine() throws IOException
	{
		return input.readLine();
	}
	
	public String getFormatedScore(int s)
	{
		return Integer.toString(s) + (s<60?"*":"");
	}
	
	public String getInputID() throws NoSuchIDExceptions, IOException, UserQuit
	{
		System.out.println("��JID�� Q (�����ϥ�)?");
		String in = getInputLine();
		if( isQuitString(in) )
			throw new UserQuit();
		if( !dm.isIDExist(in) )
			throw new NoSuchIDExceptions();
		return in;
	}
	
	private void update()
	{
		cached_avg = 0;
		double sum = 0;
		for(RowData r:dm.getData())
			sum+=dm.avg(r);
		cached_avg = Math.round(sum/dm.getData().size());
	}
	
	private void UIView() throws NoSuchIDExceptions, IOException
	{
		RowData d  = cached_data;
		System.out.format(       "%s:lab1        :%s\n",d.name+"���Z",getFormatedScore(d.score[0]));
		System.out.format("         :lab2        :%s\n",getFormatedScore(d.score[1]));
		System.out.format("         :lab3        :%s\n",getFormatedScore(d.score[2]));
		System.out.format("         :mid-term    :%s\n",getFormatedScore(d.score[3]));
		System.out.format("         :final exam  :%s\n",getFormatedScore(d.score[4]));
		System.out.format("         :total grade :%d\n",Math.round(dm.avg(d)));
	}
	
	private void UIRank()
	{
		int greater = 0;
		for(RowData d:dm.getData())
		{
			if( Math.round(dm.avg(d)) > Math.round(dm.avg(cached_data)) )
			{
				greater++;
			}
		}
		System.out.format("%s�ƦW��%d�W\n",cached_data.name,greater+1);
	}
	
	private void UIAvg()
	{
		System.out.format("���Z����%d\n",cached_avg);
	}
	
	private void UIshowWeight(int w[],String title)
	{
		if( title != null )
			System.out.println(title);
		for(int i=0;i<w.length;++i)
			System.out.format("%s %d%%\n",item_list[i],w[i]);
	}
	
	private int[] UIgetNewWeight() throws IOException
	{
		int nw[] = new int [RowData.size];
		for(int i=0;i<RowData.size;++i)
		{
			System.out.format("%s ",item_list[i]);
			nw[i] = Integer.parseInt(getInputLine());
		}
		return nw;
	}
	
	private void UIModifyWeight() throws IOException, NoSuchCommandExceptions
	{
		UIshowWeight(dm.getWeight(),"�°t��");
		System.out.println("��J�s�t��");
		int nw[] = UIgetNewWeight();
		UIshowWeight(nw,"�нT�{�t��");
		System.out.println("�H�W���T��? Y (Yes) �� N (No)");
		if( YesNo(getInputLine()) )
			dm.setWeight(nw);
	}
	
	public void UImain()
	{
		System.out.format("Welcome %s\n",cached_data.name);
		System.out.println("��J���O 1) G ��ܦ��Z (Grade)");
		System.out.println("        2) R ��ܱƦW (Rank) ");
		System.out.println("        3) A ��ܥ��� (Average)");
		System.out.println("        4) W ��s�t�� (Weight)");
		System.out.println("        5) E ���}��� (Exit)");
	}
	
	public void UIExit()
	{
		System.out.println("�����F");
	}
	
	public void method(String method_name) throws NoSuchCommandExceptions, NoSuchIDExceptions, IOException
	{
		switch(method_name.toUpperCase())
		{
		case "G":
			UIView();
			break;
		case "R":
			UIRank();
			break;
		case "A":
			UIAvg();
			break;
		case "W":
			UIModifyWeight();
			break;
		default:
			throw new NoSuchCommandExceptions();
		}
	}

	public void setUser(String uid) {
		now_uid = uid;
		cached_data = dm.getDataByID(now_uid);
	}
}
