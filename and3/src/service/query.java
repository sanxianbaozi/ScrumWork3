package service;

import homework.httphelp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class query extends HttpServlet {


	public query() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpResponse myresponse;
		Map<String, String> params = new HashMap<String, String> ();
		params.put("kksj",request.getParameter("kksj").toString().trim());
		httphelp.log("重要参数："+request.getParameter("kksj").toString().trim());
		params.put("kcxz", request.getParameter("kcxz"));
		params.put("kcmc", request.getParameter("kcmc"));
		params.put("xsfs", request.getParameter("xsfs"));
		httphelp.log("重要参数:"+request.getParameter("xsfs"));
		params.put("kcdl", request.getParameter("kcdl"));
		params.put("kssj", request.getParameter("kssj"));
		params.put("ok", "");
		myresponse = httphelp.doPost("http://jwcxxcx.ccsu.cn/jwxt/xszqcjglAction.do?method=queryxscj",params,(String)request.getSession().getAttribute("jssionid"));
		if(myresponse!=null)
			httphelp.log("查询请求成功");
		else
			httphelp.log("查询请求失败");
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
		httphelp.log(elenum.html());
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
		httphelp.log("用户此次查询了  "+table.length+"条信息");
		//到此数据全部存储在table中了，列十七，行视情况而定
	    List<score> li=new LinkedList<score>();
		for(int i=0;i<num;i++){
			score sc = new score();
			sc.setId(table[i][1]);
			sc.setXuehao(table[i][2]);
			sc.setName(table[i][3]);
			sc.setKkxq(table[i][4]);
			sc.setKcmc(table[i][5]);
			sc.setScore(table[i][6]);
			sc.setCjbz(table[i][7]);
			sc.setKcxz(table[i][8]);
			sc.setKclb(table[i][9]);
			sc.setXs(table[i][10]);
			sc.setXf(table[i][11]);
			sc.setKsxz(table[i][12]);
			sc.setBcxq(table[i][13]);
			sc.setBz(table[i][14]);
			sc.setKcdl(table[i][15]);
			sc.setTsxlb(table[i][16]);
			sc.setKssj(table[i][17]);
			li.add(sc);
		}
		request.getSession().setAttribute("scoreList", li);
		response.sendRedirect("/homework/showScore.jsp");
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
