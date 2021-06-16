package testing;
import org.jmock.Expectations;
import org.jmock.Sequence;
public class MockIteratorTest extends TestBase{
	final Sequence sequence = context.sequence("mySeq_01");  
	/*
	 * 期望
	 */
	@Override
	protected void setUpExpectations(){
		context.checking(new Expectations(){{
			oneOf(idata).in_Out_Room(901,"Marry");
			inSequence(sequence);
			will(returnValue("Marry成功入住901房间！"));
			oneOf(idata).in_Out_Room(901,"Marry");
			inSequence(sequence);
			will(returnValue("已经有人咯。"));
		}});
	}
	/**
	 * 调用方法并验证
	 */
	@Override
	protected void invokeAndVerify(){
		//assertState(901,"Marry","Marry成功入住901房间！");
		assertState(901,"Marry","已经有人咯。");
	}
}
