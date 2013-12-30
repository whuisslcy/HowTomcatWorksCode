package SimpleHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.WritableByteChannel;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;

public class Response implements ServletResponse {
	
	private OutputStream outputStream;
	
	private Request request;
	
	private PrintWriter wirter;

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

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if(wirter == null) 
		{
			wirter = new PrintWriter(outputStream,true);
		}
		return wirter;
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub
		
	}

}
