package grade;

import java.util.Collections;
import java.util.Vector;

public class DataManager {
	private Vector<RowData> vc;
	private int[] weight = {10,10,10,30,40};
	static public int nid = -1;
	public DataManager(Vector<RowData> v)
	{
		vc = v;
		Collections.sort(vc);
	}
	public Vector<RowData> getData()
	{
		return vc;
	}
	
	public int[] getWeight()
	{
		return weight;
	}
	
	public void setWeight(int w[])
	{
		weight = w;
	}
	
	public int FindIndexByID(String uid)
	{
		int L = 0;
		int R = vc.size()-1;
		int M,comp;
		while( L<=R )
		{
			M = (L+R)/2;
			comp = vc.get(M).uid.compareTo(uid);
			if( comp==0 )return M;
			if( comp <0 )R=M-1;
			else L=M+1;
		}
		return nid;
	}
	
	public boolean isIDExist(String uid)
	{
		return FindIndexByID(uid)!=nid;
	}
	
	public RowData getDataByID(String uid)
	{
		int index = FindIndexByID(uid);
		return vc.get(index);
	}
	
	public double avg(RowData r)
	{
		int sum = 0;
		for(int i=0;i<RowData.size;++i)
		{
			sum += r.score[i]*weight[i];
		}
		//TODO remove magic 100
		return sum/100.0;
	}
}
