package grade;

public class RowData implements Comparable<RowData>{
	//955002056 ³\¤åÄÉ 88 92 88 98 91
	public String uid;
	public String name;
	public int[] score;
	public final static int size = 5;
	public RowData(String u,String n,int s[]) {
		if(s.length!=size){
			//TODO throw?
		}
		uid = u;
		name = n;
		score = s;
	}
	@Override
	public int compareTo(RowData arg0) {
		return - this.uid.compareTo(arg0.uid);
	}
}
