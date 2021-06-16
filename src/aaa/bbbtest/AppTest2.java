package aaa.bbbtest;

import org.junit.Test;

import junit.framework.TestCase;

public class AppTest2 extends TestCase {
    App cal=new App();
	//条件判定覆盖
    @Test
	public void testCommission1() {
		float except=-1;
		float actual=cal.commission(0,0,0);
		assertEquals(except,actual);
	}
    @Test
	public void testCommission2() {
		float except=(float)9.8;
		float actual=cal.commission(1,1,1);
		assertEquals(except,actual);
	}
    @Test
	public void testCommission3() {
		float except=(float)101.2;
		float actual=cal.commission(10,20,1);
		assertEquals(except,actual);
	}
    @Test
	public void testCommission4() {
		float except=(float)1463.6;
		float actual=cal.commission(100,1,1);
		assertEquals(except,actual);
	}
    public void testCommission5() {
		float except=-1;
		float actual=cal.commission(0,1,1);
		assertEquals(except,actual);
	}
    public void testCommission6() {
		float except=-1;
		float actual=cal.commission(1,-1,1);
		assertEquals(except,actual);
	}
    public void testCommission7() {
		float except=-1;
		float actual=cal.commission(1,1,-1);
		assertEquals(except,actual);
	}
    public void testCommission8() {
		float except=-1;
		float actual=cal.commission(0,-1,1);
		assertEquals(except,actual);
	}
    public void testCommission9() {
		float except=-1;
		float actual=cal.commission(0,1,-1);
		assertEquals(except,actual);
	}
    public void testCommission10() {
		float except=-1;
		float actual=cal.commission(1,-1,-1);
		assertEquals(except,actual);
	}
    public void testCommission11() {
		float except=-1;
		float actual=cal.commission(0,-1,-1);
		assertEquals(except,actual);
	}
}
