package grade;

import java.util.Collections;
import java.util.Vector;

public class DataManager {
	private Vector<RowData> vc;
	private int[] weight = {10,10,10,30,40};
	static public int nid = -1;
	
	/* 建構子
	 * 需傳入資料的Vector
	 * @param v 要管理的資料
	 */
	public DataManager(Vector<RowData> v)
	{
		vc = v;
		Collections.sort(vc);
	}
	
	/* method getData
	 * 取得所有儲存的資料
	 * @return 內部Vector
	 */
	public Vector<RowData> getData()
	{
		return vc;
	}
	
	/* method getWeight
	 * 取得權重陣列
	 * @return 內部權重陣列
	 */
	public int[] getWeight()
	{
		return weight;
	}
	
	/* method setWeight
	 * 設定權重陣列
	 * @param 新的權重陣列
	 */
	public void setWeight(int w[])
	{
		weight = w;
	}
	
	/* method FindIndexByID
	 * 根據uid尋找在Vector中存放的位置，找不到時回傳nid
	 * 使用二分搜尋法
	 * Time order : O( logN )
	 * @param uid 搜尋的uid
	 * @return Index，找不到時回傳nid
	 */
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
	
	/* method isIDExist
	 * 回傳該uid是否存在於Vector
	 * Time order : O( logN )
	 * @param uid 搜尋的uid
	 * @return 是否存在的boolean值
	 */
	public boolean isIDExist(String uid)
	{
		return FindIndexByID(uid)!=nid;
	}
	
	/* method getDataByID
	 * 根據uid取出資料，該方法沒有驗證uid的正確性
	 * Time order : O( logN )
	 * @param uid 搜尋的uid
	 * @return 該uid的完整資料
	 */
	public RowData getDataByID(String uid)
	{
		int index = FindIndexByID(uid);
		return vc.get(index);
	}
	
	/* method avg
	 * 計算傳入資料的加權平均值
	 * @param uid 要計算的資料
	 * @return 加權平均值
	 */
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
