package TestWeb;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.platform.sessionmanager.SessionException;
import com.platform.sessionmanager.SessionHandle;
import com.platform.sessionmanager.SessionManager;
import com.platform.sessionmanager.ThreadLocalUtil;




/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日上午10:39:50
 */
public class TestGet extends HttpServlet {
	private SessionManager sessionManager;
	private ApplicationContext applicationContext;
    
	
	@Override
	public void init() throws ServletException {
		super.init();
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());  
		sessionManager=(SessionManager)applicationContext.getBean("sessionManager");
	}
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//RequestContext.get().begin(req, resp); 
		String value=null;
		try {
		    value=sessionManager.getAttribute("haha");
	         } catch (Exception e) {	
	       } 
		
		/*Enumeration<String> e=null;
		try {
			e = (Enumeration<String>) sessionManager.getAttributeNames();
		} catch (SessionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(e.hasMoreElements()){
			String key=e.nextElement();
			System.out.println(key);
		}*/
	      resp.getWriter().println("得到数据:"+value+"   使用类型："+sessionManager.getClass()+"   sessionid:"+ThreadLocalUtil.get());
    }
}
