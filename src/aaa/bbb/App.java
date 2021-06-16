package aaa.bbb;
public class App 
{
   public float commission(int headphones,int shells,int protectors) {
	   float salary;  //佣金
	   float total;   //总销售额
	   if(headphones<=0||shells<0||protectors<0)  //判断数量的合法性
		   return(float)-1;
	   total=headphones*80+shells*10+protectors*8;
	   if(total<1000)  //计算佣金
		   salary=total*(float)0.1;
	   else if(total<1800)
		   salary=1000*(float)0.1+(total-1000)*(float)0.15;
	   else
		   salary=1000*(float)0.1+(800)*(float)0.15+(total-1800)*(float)0.2;
	   return salary;
   }
   public static void main(String[] args) {
	App a = new App();
	System.out.println(a.commission(10, 20, 1));
}
}
