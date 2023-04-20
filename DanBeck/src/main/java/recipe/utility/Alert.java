package recipe.utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class Alert {
	public static void alertLocation(String msg, String url, JspWriter out) {
		String script = "<script>\r\n"
				+ "		alert('"+msg+"');\r\n"
				+ "		location.href='"+url+"';\r\n"
				+ "	</script>\r\n";
		try {
			out.println(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void alertBack(String msg, JspWriter out) {
		String script = "<script>\r\n"
				+ "		alert('"+msg+"');\r\n"
				+ "		history.back();\r\n"
				+ "	</script>";
		try {
			out.println(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void alertLocation(HttpServletResponse resp,String msg,String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer=resp.getWriter();
			String script ="<script>"
				+ "	alert('"+msg+"');"
				+ "	location.href='"+url+"';"
				+ " </script>";
			writer.print(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void alertBack(HttpServletResponse resp, String msg) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            String script = "<script>"
                          + "    alert('" + msg + "');"
                          + "    history.back();"
                          + "</script>";
            writer.print(script);
        }
        catch (Exception e) {}
    }
	 
	
}	
