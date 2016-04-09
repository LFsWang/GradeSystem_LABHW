package grade;

import java.util.Collections;
import java.util.Vector;

public class DataManager {
	private Vector<RowData> vc;
	private int[] weight = {10,10,10,30,40};
	static public int nid = -1;
	
	/* �غc�l
	 * �ݶǤJ��ƪ�Vector
	 * @param v �n�޲z�����
	 */
	public DataManager(Vector<RowData> v)
	{
		vc = v;
		Collections.sort(vc);
	}
	
	/* method getData
	 * ���o�Ҧ��x�s�����
	 * @return ����Vector
	 */
	public Vector<RowData> getData()
	{
		return vc;
	}
	
	/* method getWeight
	 * ���o�v���}�C
	 * @return �����v���}�C
	 */
	public int[] getWeight()
	{
		return weight;
	}
	
	/* method setWeight
	 * �]�w�v���}�C
	 * @param �s���v���}�C
	 */
	public void setWeight(int w[])
	{
		weight = w;
	}
	
	/* method FindIndexByID
	 * �ھ�uid�M��bVector���s�񪺦�m�A�䤣��ɦ^��nid
	 * �ϥΤG���j�M�k
	 * Time order : O( logN )
	 * @param uid �j�M��uid
	 * @return Index�A�䤣��ɦ^��nid
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
	 * �^�Ǹ�uid�O�_�s�b��Vector
	 * Time order : O( logN )
	 * @param uid �j�M��uid
	 * @return �O�_�s�b��boolean��
	 */
	public boolean isIDExist(String uid)
	{
		return FindIndexByID(uid)!=nid;
	}
	
	/* method getDataByID
	 * �ھ�uid���X��ơA�Ӥ�k�S������uid�����T��
	 * Time order : O( logN )
	 * @param uid �j�M��uid
	 * @return ��uid��������
	 */
	public RowData getDataByID(String uid)
	{
		int index = FindIndexByID(uid);
		return vc.get(index);
	}
	
	/* method avg
	 * �p��ǤJ��ƪ��[�v������
	 * @param uid �n�p�⪺���
	 * @return �[�v������
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
