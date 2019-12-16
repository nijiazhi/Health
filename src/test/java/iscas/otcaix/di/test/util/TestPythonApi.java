package iscas.otcaix.di.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class TestPythonApi {

	/**
	 * java中直接调用python代码
	 */
	public static void method1() {
		PythonInterpreter interpreter = new PythonInterpreter();
		String pythonCode = "print ('nijiazhi test pythonApi method1!');";
		interpreter.exec(pythonCode);
	}

	/**
	 * 在java中调用本机python脚本
	 */
	public static void method2() {
		PythonInterpreter interpreter = new PythonInterpreter();
		String pythonCodePath = System.getProperty("user.dir") + File.separator + "testPython.py";
		interpreter.execfile(pythonCodePath);
		PyFunction func = (PyFunction) interpreter.get("adder", PyFunction.class);
		int a = 2010, b = 7;
		PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
		System.out.println("anwser = " + pyobj.toString());
	}

	/**
	 * 使用本机环境运行python脚本
	 */
	public static boolean method3() {
		try {
			String pythonCodePath = System.getProperty("user.dir") + File.separator + "testPython.py";
			Process proc = Runtime.getRuntime().exec("python " + pythonCodePath);
			InputStream fis = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
            while((line=br.readLine())!=null){
            	System.err.println(line);
            }    
			return proc.waitFor() == 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		method1();System.out.println();
		method2();System.out.println();
		method3();
	}

}
