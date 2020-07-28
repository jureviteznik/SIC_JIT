package sic.sim.addons.jit.sic2intel;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static SimpleDateFormat sdf;
	private static Date date;
	private static PrintWriter writer;
	private static File errFile;
	
	/**
	 * Creates an error log file when needed.
	 */
	public static void initLogger() {
		sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		date = new Date();
		
		try {
            // initialise and create a new file
            
            //OUTPUT FILE NAME CHANGED BY JURE VITEZNIK!!!
            //TODO: FIX THIS
			errFile = new File("C:\\Users\\Uporabnik\\Desktop\\uni\\diplomska\\SicTools-master\\out\\jit_out\\error.log");
			errFile.createNewFile();
			
			writer = new PrintWriter(errFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param shortMsg
	 * 		A short description of the error.
	 * @return
	 * 		Returns 1 (error exit status)
	 */
	public static int error(String shortMsg) {
		if (writer == null) {
			initLogger();
		}
		
		writer.println(timestamp() + shortMsg + "\n");

		return 1;
	}
	
	/**
	 * 
	 * @param shortMsg
	 * 		A short description of the error. 
	 * @param error
	 * 		The actual error.
	 * @return
	 * 		Returns 1 (error exit status).			
	 */
	public static int error(String shortMsg, Exception error) {
		if (writer == null) {
			initLogger();
		}
		
		writer.println(timestamp() + shortMsg + "\n");
		error.printStackTrace(writer);

		return 1;
	}
	
	/**
	 * 
	 * @param shortMsg
	 * 		A short description of the error. 
	 * @param error
	 * 		The actual error.
	 * @param exitStatus
	 * 		The exit status of the application.
	 * @return
	 * 		Returns the exit status parameter.
	 */
	public static int fatalError(String shortMsg, Exception error, int exitStatus) {
		if (writer == null) {
			initLogger();
		}
		
		writer.println(timestamp() + shortMsg + "\n");
		error.printStackTrace(writer);

		return exitStatus;
	}
	
	/**
	 * Prints a warning message.
	 * 
	 * @param msg
	 * 		Warning message.
	 */
	public static void warning(String msg) {
		if (writer == null) {
			initLogger();
		}
		
		writer.println(timestamp() + msg + "\n");
	}
	
	/**
	 * Writes a warning, bound to a line and a column from the source file.
	 * 
	 * @param msg
	 *            Message.
	 * @param begLine
	 *            Line of the source file, where the reason for this warning begins.
	 * @param begColumn
	 *            Column of the source file, where the reason for this warning begins.
	 */
	public static void warning(String msg, int begLine, int begColumn) {		
		if (writer == null) {
			initLogger();
		}
		
		writer.println(timestamp() + msg + " [" + begLine + ":" + begColumn + "]." + "\n");
	}
	
	/**
	 * Closes the error log file, if it was opened.
	 */
	public static void close() {
		if (writer != null) {
			writer.close();
			writer = null;
		}
	}
	
	/**
	 * Creates a timestamp in using the following format: [dd.MM.yyyy HH:mm:ss]
	 * 
	 * @return
	 *  	Returns the created timestamp as a string.
	 */
	public static String timestamp() {
		return "[" + sdf.format(date.getTime()) + "] ";
	}
}