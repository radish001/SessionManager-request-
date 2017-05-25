package TestWeb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.platform.sessionmanager.SessionManager;
import com.platform.sessionmanager.ThreadLocalUtil;



/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日上午10:36:13
 */
public class TestSet extends HttpServlet {
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
	   try {

		   sessionManager.setAttribute("1", "123");
		   sessionManager.setAttribute("2", "123");
		   sessionManager.setAttribute("3", "123");
		   sessionManager.setAttribute("4", "123");
		   sessionManager.setAttribute("5", "123");
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
	    resp.getWriter().println("设置完成！"+ThreadLocalUtil.get());
	    
	    
    }
}
