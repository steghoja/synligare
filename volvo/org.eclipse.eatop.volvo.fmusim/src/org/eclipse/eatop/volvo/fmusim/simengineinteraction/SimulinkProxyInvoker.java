package org.eclipse.eatop.volvo.fmusim.simengineinteraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.CountDownLatch;

import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;

import org.eclipse.eatop.volvo.fmusim.ExtStyledText;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.FMUTaskBuilder;
import org.eclipse.eatop.volvo.fmusim.visualization.ResultVisualizer;
import org.eclipse.swt.SWT;

public class SimulinkProxyInvoker  {

	ExtStyledText logWidget;
	
	public SimulinkProxyInvoker(ExtStyledText logWidget){
	this.logWidget = logWidget; 
	}
	
	private static MatlabProxyFactory matlabProxyFactory;
	private static MatlabProxy matlabProxy;
	
	private static boolean simSuccess = false;
	
	public boolean Execute(){	
		simSuccess = true; //false;
		
		// Create a latch
		final CountDownLatch simReadyLatch = new CountDownLatch(1);
		
		Thread worker = new Thread(new Runnable() {
		     @Override
			public void run() {
		      	    	   
		    	try
		    	{
			 		if (matlabProxyFactory == null || matlabProxy == null)
					{
						// Create a matlab factory
						matlabProxyFactory = new MatlabProxyFactory();
						matlabProxy = matlabProxyFactory.getProxy();			
					}	
					
					//First delete current result file		
					File resultFile = new File("c:\\fmusim\\FMUSimResult.txt"); 
					resultFile.delete();		
		
					matlabProxy.eval("disp('Starting FMU simulation')");
					matlabProxy.eval("automatic = 1;");
					matlabProxy.eval("run('C:\\fmusim\\generateFMUSimModel');");			
					
					//The call above returns when the script has run.
					//It seems like the resultfile has not always been finished writing then,
					//so wait until we're ready to get an exclusive lock on the file.
					//Thread.sleep(2000);

					waitForFileLock();
					
					simReadyLatch.countDown(); 		
		    	}
			    catch (Exception err) {
			    	simSuccess = false;
			    	err.printStackTrace();
			    	simReadyLatch.countDown(); 			    	
			    }		    	
		    	 
		     }

			private void waitForFileLock() throws FileNotFoundException, IOException, InterruptedException {
				int tLocktimeElapsed = 0;
				boolean bLockAquired = false;
				while (tLocktimeElapsed < 25000 && !bLockAquired)
				{
					RandomAccessFile file = null;  

					FileLock fileLock = null;  
					try 
					{  
						//Gives exception when a lock cannot be aquired, i.e. another process is writing the file
						file = new RandomAccessFile("c:/fmusim/FMUSimResult.txt", "rw");  
						FileChannel fileChannel = file.getChannel();  


						fileLock = fileChannel.tryLock();  
						if (fileLock != null){  
							bLockAquired = true;
						}  

					}finally{  
						if (fileLock != null){  
							fileLock.release();  
						}  
					}  
					Thread.sleep(500);
					tLocktimeElapsed += 500;
				}
				
				if (!bLockAquired)
				{
					Utils.showErrorMessage("Failed to aquire file lock of FMUSimresult.txt");
				}
			}});
		
		worker.start();
				
		int tElapsed = 0;
		while ((tElapsed < 10*60*1000) && (simReadyLatch.getCount() != 0)) {
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tElapsed = tElapsed + 1000;
			
			if (tElapsed % 50000 == 0)
				logWidget.Write(".\n");
			else
				logWidget.Write(".");
		}	
		
		logWidget.WriteLn("");
		
		if (simReadyLatch.getCount()!=0){
			simSuccess = false;
		}
		return simSuccess;
		


//		
//		
//		//"..matlab.exe -sd c:\fmusim -r pwd" works, now it works again.
//		
//		
//		//Start matlab via command line
//	 	 
//		 String sCommand = "matlab.exe -r \"automatic=true;run('C:\\fmusim\\generateFMUSimModel');exit\"";
//	      String line;
//      Process p = Runtime.getRuntime().exec (sCommand);
//	      
//	      //must read process output stream or else it may hang
//	      //the start process terminates quite soon
//	      BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
//	      while ((line = input.readLine()) != null) {
//	        System.out.println(line);
//	      }
//	      input.close();
//    
//	
//		int tElapsed = 0;
//		boolean bResultFileComplete = false;
//		while ((tElapsed < 3*60*1000) && (!bResultFileComplete)) {
////		while ((tElapsed < 6*1000) && (!bResultFileComplete)) {
//		
//			Thread.sleep(1000);
//			tElapsed = tElapsed + 1000;
//			logWidget.Write(".");
//			if (resultFile.exists())
//			{
//		
//				//sleep another 500 ms so we are "sure" that file has been fully saved
//				Thread.sleep(500);
//				bResultFileComplete = true;
//
//			}
//		}
//	
//		logWidget.WriteLn("");
//		return bResultFileComplete;
		
//		}
//	    catch (Exception err) {
//	      err.printStackTrace();
//	      return false;
//	    }
		
	}	
}
