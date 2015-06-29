package com.opskuba.debugger.configuration.banner;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

/**
 * 自定义项目banner.
 */
public class OpsDebuggerBanner implements Banner {

	private static final String[] BANNER = { "----------","----------" };
	
	private static final String OPS_DEBUGGER = " :: Ops Debugger :: ";

	@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
		
		for (String line : BANNER) {
			printStream.println(line);
		}
		
		printStream.println(OPS_DEBUGGER);
	}

}
