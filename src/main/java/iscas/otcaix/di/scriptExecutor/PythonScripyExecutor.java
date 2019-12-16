package iscas.otcaix.di.scriptExecutor;

import javax.persistence.Entity;

@Entity
public class PythonScripyExecutor extends AbstractScripyExecutor{
	
	private static final String NAME = "python";
	
	public PythonScripyExecutor() {
		super(NAME);
	}
	
	public boolean exe(String pythonSciptyPath){
		return super.exe("python "+pythonSciptyPath);
	}

	

}
