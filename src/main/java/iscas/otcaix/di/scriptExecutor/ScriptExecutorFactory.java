package iscas.otcaix.di.scriptExecutor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 组合模式（静态工厂模式）
 * 模仿java.util.concurrent.Executors包，提供一系列静态方法
 */
public class ScriptExecutorFactory {
	@Autowired
	private static RScripyExecutor rScripyExecutor;
	
	@Autowired
	private static PythonScripyExecutor pythonScripyExecutor;
	
	@Autowired
	private static MatlabScripyExecutor matlabScripyExecutor;
	
	public static RScripyExecutor newRExecutor(){
		return rScripyExecutor;
	}
	
	public static RScripyExecutor getrScripyExecutor() {
		return rScripyExecutor;
	}

	public static void setrScripyExecutor(RScripyExecutor rScripyExecutor) {
		ScriptExecutorFactory.rScripyExecutor = rScripyExecutor;
	}

	public static PythonScripyExecutor getPythonScripyExecutor() {
		return pythonScripyExecutor;
	}

	public static void setPythonScripyExecutor(PythonScripyExecutor pythonScripyExecutor) {
		ScriptExecutorFactory.pythonScripyExecutor = pythonScripyExecutor;
	}

	public static MatlabScripyExecutor getMatlabScripyExecutor() {
		return matlabScripyExecutor;
	}

	public static void setMatlabScripyExecutor(MatlabScripyExecutor matlabScripyExecutor) {
		ScriptExecutorFactory.matlabScripyExecutor = matlabScripyExecutor;
	}

	public static PythonScripyExecutor newPythonExecutor(){
		return pythonScripyExecutor;
	}
	
	public static MatlabScripyExecutor newMatlabExecutor(){
		return matlabScripyExecutor;
	}
	
}
