package iscas.otcaix.di.scriptExecutor;

import javax.persistence.Entity;

@Entity
public class RScripyExecutor extends AbstractScripyExecutor {
	private static final String NAME = "R";

	public RScripyExecutor() {
		super(NAME);
	}
	
	public boolean exe(String RSciptyPath){
		return super.exe("R "+RSciptyPath);
	}
}
