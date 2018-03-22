package service;
import homework.httphelp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.score;
public class login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.sendRedirect("/homework/login.jsp");
			httphelp.log("重定向到登陆");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jssionid = httphelp.get();
		httphelp.log("当前cookie："+jssionid);
		httphelp.jsessionid=jssionid;
		request.getSession().setAttribute("jssionid", jssionid);
		Map<String,String> params=new HashMap<String,String>();
		params.put("USERNAME", request.getParameter("userName"));
		params.put("PASSWORD", request.getParameter("userPassword"));
		HttpResponse myresponse = httphelp.doPost("http://jwcxxcx.ccsu.cn/jwxt/Logon.do?method=logon",params,jssionid);
	    //如果登录成功
		if (myresponse.getStatusLine().getStatusCode() == 200) {
				if(myresponse.getFirstHeader("Transfer-Encoding")!=null){
					httphelp.log("Login Success!");						
					request.getSession().setAttribute("isLogin", "TRUE");
					request.getSession().setAttribute("userID", request.getParameter("userName"));
					int start=Integer.parseInt((String)request.getParameter("userName").subSequence(1, 5));
					//httphelp.log(start);
					//获取时间线
					Calendar c = Calendar.getInstance(); 
					int year = c.get(Calendar.YEAR); 
					int month = c.get(Calendar.MONTH);
					List<String> timeList=new LinkedList<String>();
					
					//StringBuilder timeList=new StringBuilder();
					int cha=year-start;//年份差值
					int xiu=0;//修正指数
					if(month<2)//开学第一期没过完
						cha-=1;
					else if(month>=3&month<7)//正在第二期
						xiu=-1;
					else//第二期开学但未结束
						;
					System.out.println(2*cha+xiu);
					for(int i=0;i<2*cha+xiu;i++){
						String s;
						if(i%2==0){
						    s=(start+i/2)+"-"+(start+i/2+1)+"-1";
						}
						else{
							s=(start+(i-1)/2)+"-"+(start+(i-1)/2+1)+"-2";	
						}
						System.out.println("add-"+s+"----"+i);
						timeList.add(s);
						//timeList.append(s+"|");
						//time[i]=s;
					}
					//上面的代码还需测试！！！
					request.getSession().setAttribute("timeList", timeList);
					request.getSession().setAttribute("stid",request.getParameter("userName") );
					response.sendRedirect("/homework/index.jsp");
				}
				
			}
		
		else
		{
			httphelp.log("Login defeated");
			return;
		}
		params.clear();
		
		//下面的是query中处理的
		/*
		params.put("kksj","2016-2017-1");
		params.put("kcxz", "");
		params.put("kcmc", "");
		params.put("xsfs", "zhcj");
		params.put("kcdl", "");
		params.put("kssj", "");
		params.put("ok", "");
		myresponse = httphelp.doPost("http://jwcxxcx.ccsu.cn/jwxt/xszqcjglAction.do?method=queryxscj",params,jssionid);
		InputStream in=myresponse.getEntity().getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(in,"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=br.readLine())!=null)
			sb.append(line);
		//log(sb.toString());
		//解析html获得自己想要的内容
		Document doc =  Jsoup.parse(sb.toString());
		//先确定自己要获取的条目数
		Element elenum=doc.getElementById("PageNavigation");
		int num=Integer.parseInt( elenum.getElementsByTag("font").text());
		String [][] table=new String[num][18];
		for(int i=1;i<=num;i++){
			Element ele = doc.getElementById(new Integer(i).toString());
			Elements eles=ele.getElementsByTag("td");
			int j=0;
			for(Element e:eles)
			{
				e.getElementsByTag("input").remove();//移除其中的input选项
				table[i-1][j++]=e.html().replace("&nbsp;", "");

			}
				
		}
		//到此数据全部存储在table中了，列十八，行视情况而定
	    List<score> li=new LinkedList<score>();
		for(int i=0;i<num;i++){
			score sc = new score();
			sc.id=table[i][1];
			sc.xuehao=table[i][2];
			sc.name=table[i][3];
			sc.kkxq=table[i][4];
			sc.kcmc=table[i][5];
			sc.score=table[i][6];
			sc.cjbz=table[i][7];
			sc.kcxz=table[i][8];
			sc.kclb=table[i][9];
			sc.xs=table[i][10];
			sc.xf=table[i][11];
			sc.ksxz=table[i][12];
			sc.bcxq=table[i][13];
			sc.bz=table[i][14];
			sc.kcdl=table[i][15];
			sc.tsxlb=table[i][16];
			sc.kssj=table[i][17];
			li.add(sc);
			//for(int j=0;j<18;j++)
			//{
				//httphelp.log("测试输出 "+i+j+"---"+table[i][j]);
			//}
		}*/
			
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
