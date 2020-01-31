/* FIO04-J. Release resources when they are no longer needed */
/* RESOURCE_LEAK */
package cert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FIO04_J {
	
	public String testComplicate(String filename) throws Exception
    {
        BufferedReader in = null;
        StringBuffer strbuf = null;
        try {
            File sourceFile = new File(filename);
            in = new BufferedReader(new FileReader(sourceFile)); 
            strbuf = new StringBuffer(2048);
            String str = "";
            
            while((str = in.readLine()) != null) { 
                strbuf.append(str+"\n");
            }
        }
        catch(IOException e) {
            throw e;                 /* BUG */ //@violation RESOURCE_LEAK
        }
        
        if (in != null) {
        	in.close();
        }
        
        return "S";
    }
	
	public String testComplicateSafe(String filename) throws Exception
    {
        BufferedReader in = null;
        StringBuffer strbuf = null;
        try {
            File sourceFile = new File(filename);
            in = new BufferedReader(new FileReader(sourceFile)); 
            strbuf = new StringBuffer(2048);
            String str = "";
            
            while((str = in.readLine()) != null) { 
                strbuf.append(str+"\n");
            }
        }
        catch (IOException e) {
            throw e;                /* SAFE */ 
        }
        finally {
        	if (in != null) {
        		in.close();
        	}
        }
        
        if (in != null) {
        	in.close();
        }
        
        return "S";
    }
	
	StreamPipe createFilePipe(String inFile, String outFile) throws FileNotFoundException {
		FileInputStream fis = null; 
		FileOutputStream fos = null;
	
		try {
			fis = new FileInputStream(inFile);
			fos = new FileOutputStream(outFile);
		}
		catch(FileNotFoundException e) {
			if (fis != null) try { fis.close(); } catch (IOException ioe) {}
			if (fos != null) try { fis.close(); } catch (IOException ioe) {}
			throw e;
		}
		
		StreamPipe pipe = new StreamPipe(fis, fos);
		
		return pipe;
	}
	
	void testStreamPipe(String inFile, String outFile) throws FileNotFoundException {
		StreamPipe pipe = createFilePipe(inFile, outFile);
	
		pipe.flow();
	} //@violation RESOURCE_LEAK
	
	void testStreamPipeBadSafe(String inFile, String outFile) throws FileNotFoundException {
		StreamPipe pipe = createFilePipe(inFile, outFile);
	
		try {
			pipe.flow();
		}
		finally {
			pipe.closeStreams();
		}
		
	} /* SAFE */
	
}

class StreamPipe {
	private InputStream in;
	private OutputStream out;
	
	public StreamPipe(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}
	
	public void flow() {
	}
	
	public void closeStreams() {
		if (in != null) {
			try { in.close(); } catch (IOException e) {}
		}
		
		if (out != null) {
			try { out.close(); } catch (IOException e) {}
		}
	}
}
