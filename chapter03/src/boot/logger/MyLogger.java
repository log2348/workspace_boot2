package boot.logger;

import java.util.logging.Logger;

public class MyLogger {
	
	public static void printLog(String str) {
		Logger logger = Logger.getLogger("myCustonLog");
		logger.warning(str);
	}

}
