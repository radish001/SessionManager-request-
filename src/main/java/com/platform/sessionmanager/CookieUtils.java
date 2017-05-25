package com.platform.sessionmanager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日上午9:54:51
 */
@Component
public class CookieUtils {
	 private static final Logger LOG=Logger.getLogger(CookieUtils.class);
	    /**
	     * 添加cookie
	     * @param request 
	     * @param response
	     * @param cookieName   cookie的名字
	     * @param cookieValue  cookie的值
	     * @param cookieMaxage  cookie的最大生存时间
	     * @throws Exception
	     */
     public static void setCookie(HttpServletRequest request,HttpServletResponse response,
     		String cookieName,String cookieValue,int cookieMaxage) {

             if (cookieValue == null) {
                 cookieValue = "";
             } 
             Cookie cookie = new Cookie(cookieName, cookieValue);
             cookie.setMaxAge(cookieMaxage);
             if (null != request)               // 设置域名的cookie
                 //cookie.setDomain(getDomainName(request));		引发无法设置cookie
             cookie.setPath("/");
             response.addCookie(cookie);
         
         }
     
     
     
     /**
      * 根据指定的cookie名字获得cookie的值
      * @param request
      * @param cookieName
      * @return
      */
    public static  String getCookieValue(HttpServletRequest request,String cookieName) {
 	  Cookie [] cookieList=request.getCookies();
 	  if(cookieList == null || cookieName == null){
 		  return null;
 	  }
 	  String cookieValue=null;
 	  for(int i=0; i<cookieList.length; i++){
            if(cookieList[i].getName().equals(cookieName)){
         	     cookieValue=cookieList[i].getValue();
         	     break;
         	  }
            }
		     return cookieValue;
      }
    
    /**
     * 设置指定cookie最大生存时间的方法
     * @param request
     * @param cookieName
     * @param maxAge   cookie的最大生存时间  单位：秒
     */
    public static void setMaxAge(HttpServletRequest request,String cookieName,int maxAge){
 	   Cookie [] cookieList=request.getCookies();
 	   if(cookieList != null || cookieName != null){
 		   for(int i=0; i<cookieList.length; i++){
 			   if(cookieName.equals(cookieList[i].getName())){
 				   cookieList[i].setMaxAge(maxAge);
 				   break;
 			   }
 		   }
 	   }
    }
    
    
    
   /**
    * 判断是否有指定名字的cookie
    * @param request
    * @param cookieName
    * @return
    */
     public static boolean containCookie(HttpServletRequest request,String cookieName){
         boolean b = false;
     	Cookie [] cookieList=request.getCookies();
  	    if(cookieList != null && cookieName != null){
  		   for(int i=0; i<cookieList.length; i++){
  			   if(cookieName.equals(cookieList[i].getName())){
  				   b = true;
  				   break;
  			   }
  		   }
  	   }
		  return b;
     }	
}
