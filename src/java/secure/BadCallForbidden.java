/**
 * Option file used:
 *
 * <?xml version="1.0" encoding="utf-8"?>
 * <option>
 *   <category name="BAD_CALL.FORBIDDEN">
 *     <item name="forbidden_method" type="string" required="false" variable="true" regexp="true">
 *       <value>.+\.forbidden</value>
 *     </item>
 *   </category>
 * </option>
 */

package java.secure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BadCallForbidden {

    public void bad1() throws Throwable
    {
    	/* FLAW */
        new ThreadRun("KISA").run();
        new ThreadRun("ABC").run();
    }

    public void bad2()
    {
        FileAccessThread fileAccessThread = new FileAccessThread();
        fileAccessThread.start();
		/* FLAW */
        fileAccessThread.stop();
    }
}

class ThreadRun extends Thread
{
    public ThreadRun(String str)
    {
        super(str);
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++)
        {
            try
            {
                sleep(1000);
            }
            catch(InterruptedException e)
            {
            }
        }
    }
}

class FileAccessThread extends Thread
{
    public synchronized void run()
    {
        BufferedReader br = null;
        try
        {
            File f = new File("c:\\data.txt");
            if(f.exists())
            {
                br = new BufferedReader(new FileReader(f));
            }
        }catch(IOException e)
        {
            System.err.println("IOException occured");
        }
        finally
        {
            try
            {
                if(br != null)
                {
                    br.close();
                }
            }catch(IOException e)
            {
                System.err.println("Error closing BufferedReader");
            }
        }
    }
}
