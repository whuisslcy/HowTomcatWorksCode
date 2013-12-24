package SimpleHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Response {
	
	private OutputStream outputStream;
	
	private Request request;

	public Response(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void sendStatisResource() {
		try {
			File file = new File(HttpServer.WEB_ROOT,request.getUri());
			System.out.println(file.getAbsolutePath());
			if(file.exists() && file.isFile())
			{
				byte[] b = new byte[2048];
				InputStream fileOutputStream = new FileInputStream(file);
				int n = fileOutputStream.read(b);
				while(n != -1)
				{
					outputStream.write(b,0,n);
					n = fileOutputStream.read(b);
				}
				fileOutputStream.close();
				
			}
			else {
				String errorStr = "HTTP/1.1 404 File Not Found\r\n" + 
			                    "Context-Type: text/html \r\n" +
						        "Content-length:21\r\n" + 
			                    "\r\n" +
						        "<h1>文件没有找到</h1>";
				outputStream.write(errorStr.getBytes());
			}
			outputStream.flush();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}
