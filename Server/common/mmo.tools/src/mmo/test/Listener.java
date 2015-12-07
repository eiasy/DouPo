package mmo.test;

import java.util.Observable;
import java.util.Observer;

public class Listener implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("RunThread死机");
		RunThread run = new RunThread();
		run.addObserver(this);
		new Thread(run).start();
		System.out.println("RunThread重启");
	}
}