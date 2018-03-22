package service;

import homework.httphelp;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class allFilter implements Filter {
	FilterConfig config;
	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		String path=config.getInitParameter("loginPath");
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String isLogin=(String )request.getSession().getAttribute("isLogin");
		if("TRUE".equals(isLogin))//����ѵ�¼
			arg2.doFilter(request, response);//����
		else{
			if(path.equals(request.getRequestURI())||"/homework/login".equals(request.getRequestURI())){
				arg2.doFilter(request, response);return;
				}
			else{
				response.sendRedirect("/homework/login.jsp");
				httphelp.log("δ��½�û����Է��ʱ�������Դ--����");
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		this.config=arg0;
	}

}
