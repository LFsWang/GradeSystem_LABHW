package grade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Vector;
import java.io.IOException;

public class Reader {
	static final String dafault_file = "gradeinput.txt";
	static private BufferedReader getReader(String file) throws IOException
	{
		return new BufferedReader(
				new InputStreamReader(
						new FileInputStream(file),"UTF8"));
	}
	
	static private RowData parser(String s)
	{
		s=s.replace("  ","　");//No double blanks @@
		String v[] = s.split(" ");
		int score[] = new int[RowData.size];
		for(int i=0;i<RowData.size;++i)
		{
			score[i] = Integer.parseInt(v[2+i]);
		}
		return new RowData( v[0],v[1],score );
	}
	
	/* method load
	 * 載入預設的檔案(default)，並回傳為Vector
	 * @return 讀取完畢的資料
	 * @throws IOException IO異常時丟出
	 */
	static public Vector<RowData> load() throws IOException
	{
		return load(dafault_file);
	}
	
	/* method load
	 * 載入指定的檔案(default)，並回傳為Vector
	 * @param url 指定的檔案位置
	 * @return 讀取完畢的資料
	 * @throws IOException IO異常時丟出
	 * @TODO need fix BOM problem
	 */
	static public Vector<RowData> load(String url) throws IOException
	{
		BufferedReader reader = getReader(url);
		String str;
		boolean first = true;
		Vector<RowData> vc = new Vector<RowData>() ;
		while( (str=reader.readLine())!=null )
		{
			if(first){ //TODO : Maybe BOM Cause problem?
				str=str.substring(1);//BOM?
				first = false;
			}
			if(str.length()==0)continue;
			vc.add(parser(str));
		}
		return vc;
	}
}
