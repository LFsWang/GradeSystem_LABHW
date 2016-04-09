package grade;

public class RowData implements Comparable<RowData>{
	public String uid;
	public String name;
	public int[] score;
	public final static int size = 5;
	
	/* 建構子
	 * @param u uid
	 * @param n 名子
	 * @param s 分數
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
	 * 依照uid的字典序為基準，比較另一元素，使用標準的String.compareTo
	 * @param arg0 欲比較的元素
	 * @return uid字典序逆序
	 */
	@Override
	public int compareTo(RowData arg0) {
		return - this.uid.compareTo(arg0.uid);
	}
}
