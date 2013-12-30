package SimpleHttpServer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	
	public static final String SHUT_DOWN = "/shutdown";
	
	private boolean isShutDown = false;
	
	public static void main(String[] args) {
		
		HttpServer httpServer = new HttpServer();
		httpServer.await();
	}

	private void await() {
		ServerSocket serverSocket = null;
		try {
			 serverSocket = new ServerSocket(8080,1,InetAddress.getByName("127.0.0.1"));
			 System.out.println("server is serving");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		while(!isShutDown) {
			Socket socket = null;
			InputStream inputStream;
			OutputStream outputStream;
			try {
			    socket = serverSocket.accept();
			    System.out.println("a connect is coming named " + socket.getRemoteSocketAddress());
			    inputStream = socket.getInputStream();
			    outputStream = socket.getOutputStream();
			    Request request = new Request(inputStream);
			    request.parse();
			    Response response = new Response(outputStream);
			    response.setRequest(request);
			    
			    if(request.getUri().startsWith("/servlet"))
			    {
			    	ServletProcess servletProcess = new ServletProcess();
			    	servletProcess.process(request,response);
			    }
			    else 
			    {
			    	StatisResourceProcess startProcess = new StatisResourceProcess();
			    	startProcess.process(request,response);
			    }
			    socket.close();
			    
			    isShutDown = request.getUri().equalsIgnoreCase(SHUT_DOWN);
			    
			} catch (IOException e) {
				e.printStackTrace();
				 
			}
			
		}
		
	}

}
