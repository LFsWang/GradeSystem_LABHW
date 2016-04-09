package grade;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Jtest {
	private DataManager DM;
	private UI ui;
	@Before
	public void setUp() throws Exception {
		DM = new DataManager(Reader.load("test.txt"));
		ui = new UI(DM);
	}

	@After
	public void tearDown() throws Exception {
	}

	//RowData
	@Test
	public void RowData_SizeTest()
	{
		assertEquals(5,RowData.size);
	}
	
	@Test
	public void RowData_Compless()
	{
		int tmp[] = new int[RowData.size];
		RowData a = new RowData("0001","A",tmp);
		RowData b = new RowData("0002","B",tmp);
		assertEquals(true,a.compareTo(b) >0);
		assertEquals(true,b.compareTo(a) <0);
	}
	
	@Test
	public void RowData_Compsame()
	{
		int tmp[] = new int[RowData.size];
		RowData a = new RowData("0001","A",tmp);
		RowData b = new RowData("0001","B",tmp);
		assertEquals(true,a.compareTo(b)==0);
		assertEquals(true,b.compareTo(a)==0);
	}
	
	@Test
	public void RowData_getData()
	{
		assertEquals(63,DM.getData().size());
	}
	
	@Test
	public void DataManager_getDefaultWeight()
	{
		DataManager t = new DataManager(new Vector<RowData>());
		int defaultw[] = {10,10,10,30,40};
		assertArrayEquals(defaultw,t.getWeight());
	}
	
	@Test
	public void DataManager_getsetWeight()
	{
		int bck[] = DM.getWeight();
		int nw [] = {20,20,20,20,20};
		int defaultw[] = {10,10,10,30,40};
		DM.setWeight(nw);
		assertArrayEquals(nw,DM.getWeight());
		DM.setWeight(bck);
		assertArrayEquals(defaultw,DM.getWeight());
	}
	
	@Test
	public void DataManager_nid()
	{
		assertEquals(-1,DataManager.nid);
	}
	
	@Test
	public void DataManager_FindIndexByIDFail()
	{
		assertEquals(DataManager.nid,DM.FindIndexByID("1x6s"));
		assertEquals(DataManager.nid,DM.FindIndexByID("1656"));
	}
	
	@Test
	public void DataManager_FindIndexByIDSucc()
	{
		assertNotEquals(DataManager.nid,DM.FindIndexByID("985002007"));
		assertNotEquals(DataManager.nid,DM.FindIndexByID("985002011"));
	}
	
	@Test
	public void DataManager_isIDExistFail()
	{
		assertEquals(false,DM.isIDExist("1x6s"));
		assertEquals(false,DM.isIDExist("1656"));
	}
	
	@Test
	public void DataManager_isIDExistSucc()
	{
		assertEquals(true,DM.isIDExist("985002007"));
		assertEquals(true,DM.isIDExist("985002011"));
	}
	
	@Test 
	public void DataManager_getDataByID()
	{
		assertEquals(true,DM.isIDExist("985002007"));
		assertEquals(true,DM.isIDExist("985002011"));
		assertEquals("985002007",DM.getDataByID("985002007").uid);
		assertEquals("985002011",DM.getDataByID("985002011").uid);
	}
	
	@Test
	public void DataManager_avg()
	{
		int s[] = {60,60,60,60,60};
		RowData r = new RowData("","",s);
		assertEquals(60.0,DM.avg(r),0.0001);
	}
	
	@Test
	public void DataManager_avg2()
	{
		int s[] = {18,56,30,20,10};
		RowData r = new RowData("","",s);
		assertEquals(20.4,DM.avg(r),0.0001);
	}
	
	@Test
	public void UI_isExitString()
	{
		assertEquals(true,ui.isExitString("e"));
		assertEquals(true,ui.isExitString("E"));
		assertEquals(false,ui.isExitString("1"));
		assertEquals(false,ui.isExitString("g"));
	}
	
	@Test
	public void UI_getFormatedScore()
	{
		assertEquals("100",ui.getFormatedScore(100));
		assertEquals("60",ui.getFormatedScore(60));
	}
	
	@Test
	public void UI_getFormatedScoreStared()
	{
		assertEquals("59*",ui.getFormatedScore(59));
		assertEquals("0*",ui.getFormatedScore(0));
	}
}
