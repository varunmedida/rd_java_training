package com.epam.singleton;

public class ThreadSafeInitialization {

	private static ThreadSafeInitialization instance;
	private ThreadSafeInitialization() {}
	public static ThreadSafeInitialization getInstance() {
		if(instance==null) {
			synchronized (ThreadSafeInitialization.class) {
				instance=new ThreadSafeInitialization();
			}
		}
		return instance;
	}
}
