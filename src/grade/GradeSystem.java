package grade;

import java.io.IOException;
import grade.UI.UserQuit;

public class GradeSystem {
	public static class NoSuchIDExceptions extends Exception { }
	public static class NoSuchCommandExceptions extends Exception { }
	
	/*
	 * main()
	 * 程式進入點
	 */
	public static void main(String[] args) throws IOException
	{	
		String tarcet_method,uid;
		UI ui = new UI(new DataManager(Reader.load()));
		while(true)
		{
			try{
				try{
					uid = ui.getInputID();
					ui.setUser(uid);
				}
				catch(UserQuit e)
				{
					break;
				}
				ui.UImain();
				while(true)
				{
					tarcet_method = ui.getInputLine();
					if( ui.isExitString(tarcet_method) )
						break;
					ui.method(tarcet_method);
				}
			}
			catch(NoSuchCommandExceptions e)
			{
				System.out.println("指令錯了");
				break;
			}
			catch(NoSuchIDExceptions e)
			{
				System.out.println("ID錯了");
				break;
			}
		}
		ui.UIExit();
	}
}
