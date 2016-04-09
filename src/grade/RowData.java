package grade;

public class RowData implements Comparable<RowData>{
	public String uid;
	public String name;
	public int[] score;
	public final static int size = 5;
	
	/* �غc�l
	 * @param u uid
	 * @param n �W�l
	 * @param s ����
	 */
	public RowData(String u,String n,int s[]) {
		if(s.length!=size){
			//TODO throw?
		}
		uid = u;
		name = n;
		score = s;
	}
	
	/* method compareTo
	 * �̷�uid���r��Ǭ���ǡA����t�@�����A�ϥμзǪ�String.compareTo
	 * @param arg0 �����������
	 * @return uid�r��ǰf��
	 */
	@Override
	public int compareTo(RowData arg0) {
		return - this.uid.compareTo(arg0.uid);
	}
}
