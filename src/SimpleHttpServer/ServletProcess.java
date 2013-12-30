package SimpleHttpServer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;


public class ServletProcess {

	public void process(Request request, Response response)  {	
		int index = request.getUri().lastIndexOf('/');
		String servletName = request.getUri().substring(index + 1);
		System.out.println(servletName);
		URLStreamHandler streamHandler = null;
		File path = new File(HttpServer.WEB_ROOT);
		try {
			String spec = new URL("file", null, path.getCanonicalPath() + File.pathSeparator).toString();
			URL url = new URL(null, spec, streamHandler);
			URL[] urls = new URL[1];
			urls[0] = url;
			URLClassLoader loader = new URLClassLoader(urls);
			Class clasz = loader.loadClass(servletName);
			Servlet servlet = (Servlet)clasz.newInstance();
			servlet.init(null);
			servlet.service(request, response);
			servlet.destroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
