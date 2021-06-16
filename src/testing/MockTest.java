package testing;
import org.jmock.Expectations;
import org.jmock.States;
public class MockTest extends TestBase{
	final States states = context.states("sm").startsAs("s1");
	/*
	 * 期望
	 */
	@Override
	protected void setUpExpectations(){
		context.checking(new Expectations(){{
			allowing(idata).in_Out_Room(901, "allen");
			when(states.is("s1"));
			will(returnValue("allen成功入住901房间！"));
			allowing(idata).in_Out_Room(901,"Marry");
			when(states.is("s1"));
			will(returnValue("Marry成功入住901房间！"));
			allowing(idata).in_Out_Room(901,"Tom");
			then(states.is("s2"));
			when(states.is("s2"));
			will(returnValue("Tom成功入住901房间！"));
		}});
	}
	/**
	 * 调用方法并验证
	 */
	@Override
	protected void invokeAndVerify(){
		//状态1
		assertState(901,"allen","allen成功入住901房间！");
		assertState(901,"allen","allen成功入住901房间！");
		//跳转到状态2
		assertState(901,"Marry","Marry成功入住901房间！");
		assertState(901,"Marry","Marry成功入住901房间！");
		//状态2
		assertState(901,"Tom","Tom成功入住901房间！");
	}
}
