package iscas.otcaix.di.scriptExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class AbstractScripyExecutor implements IScriptExecutor {

	protected Process proc = null;
	protected String scriptName;
	
	public AbstractScripyExecutor(String scriptName) {
		super();
		this.scriptName = scriptName;
	}

	public boolean exe(String cmd) {
		try {
			proc = Runtime.getRuntime().exec(cmd);
			InputStream fis = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.err.println(line);
			}
			return proc.waitFor() == 0;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

}
