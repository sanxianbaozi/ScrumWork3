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

public class kb extends HttpServlet {


	public kb() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cookie =(String)request.getSession().getAttribute("jssionid");
		System.out.println("cookiedezhi"+cookie);
		String re = httphelp.doGet("http://jwcxxcx.ccsu.cn/jwxt/tkglAction.do?method=goListKbByXs&istsxx=no&xnxqh=2017-2018-2&zc=&xs0101id="+request.getSession().getAttribute("stid")
				, cookie);
		Document doc =  Jsoup.parse(re.toString());
		//先确定自己要获取的条目数
		//Element ele=doc.getElementById("kbtable");
		request.getSession().setAttribute("context", doc.html());
		System.out.println(doc.html());
		response.sendRedirect("/homework/showkb.jsp");
		return;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
