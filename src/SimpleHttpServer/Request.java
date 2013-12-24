package SimpleHttpServer;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	
	private InputStream inputStream;
	
	private String uri;

	public String getUri() {
		return uri;
	}

	public Request(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void parse() {
		 StringBuffer buffer = new StringBuffer();
		 byte[] bytes = new byte[2048];
		 try {
			int i = inputStream.read(bytes);
			for(int j = 0; j < i; j++)
			{
				buffer.append((char)bytes[j]);
			}
			System.out.println(buffer.toString());
			parseUri(buffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void parseUri(String input) {
		input = input.split("\r\n")[0];
		int start = input.indexOf("/");
		int end = input.lastIndexOf("/");
		if(end - 5 > start)
		    uri = input.substring(start,end - 5);
		if(uri.equals("/"))
			uri = "index.html";
		System.out.println(uri);
		
	}

}
