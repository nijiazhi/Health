package iscas.otcaix.di.scriptExecutor;

import javax.persistence.Entity;

@Entity
public class MatlabScripyExecutor extends AbstractScripyExecutor {
	private static final String NAME = "Matlab";

	public MatlabScripyExecutor() {
		super(NAME);
	}
	
	public boolean exe(String RSciptyPath){
		return super.exe("Matlab "+RSciptyPath);
	}
}
