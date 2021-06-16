package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import hotel.DP;
import hotel.IData;
import hotel.Search;

public class SearchTest {
	IData id;
	DP dp=new DP();
	Search search=new Search();
	
	@Test
	public void test_Empty_Room() {
		id=dp;
		id.iniRooms();
		assertEquals("该房间为空",search.status(801));
	}
	
	@Test
	public void test_NotEmpty_Room() {
		id=dp;
		id.iniRooms();
		id.in_Out_Room(901, "Marry");
		assertEquals("Marry",search.status(901));
	}
	
	@Test
	public void test_Wrong_RoomNumber() {
		assertEquals("请输入正确的房间号",search.status(915));
	}
}
