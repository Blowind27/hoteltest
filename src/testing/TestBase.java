package testing;
import static org.junit.Assert.*;
import org.jmock.Mockery;
import org.junit.Test;
import hotel.IData;
import hotel.InHotel;
public abstract class TestBase {
	//建立一个test上下文对象
	protected Mockery context=new Mockery();
	//生成一个mock对象
	protected final IData idata=context.mock(IData.class);
	
	/*
	 * 实例化要测试的类
	 */
     protected InHotel ih;
     
     /*
      * 设置测试类，并设置Mock的接口
      */
     private void setUpMockInterface(){
    	 ih=new InHotel(idata);
    	 
     }
     /**
      * 调用Mock的接口，并且返回验证值
      * @param  roomNo,name
      * @param  expected
      * 
      */
     protected void assertState(int roomNo,String name,String expected){
    	     	 assertEquals(expected,ih.in(roomNo, name));
     }
     
     /**
      * 调用iData，并验证方法抛出异常
      */
     protected void  assertStateFail(int roomNo,String name){
    	 try{
    		 ih.in(roomNo, name);
    		 fail("发生错误");
    	 }catch(Throwable t){}
     }

@Test
public final void test(){
 setUpExpectations();
 setUpMockInterface();
 invokeAndVerify();
	}
/*
 * 期望
 */
protected abstract void setUpExpectations();
/**
 * 调用方法并验证
 */
protected abstract void invokeAndVerify();
}
