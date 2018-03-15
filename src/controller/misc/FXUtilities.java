package controller.misc;

import javafx.application.Platform;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * General JavaFX utilities.
 *
 * @author hendrikebbers
 */
public class FXUtilities {

	/**
	 * Simple Helper class.
	 *
	 * @author hendrikebbers
	 */
	private static class ThrowableWrapper{
		Throwable t;
	}

	public static void runAndWait(final Runnable run) throws ExecutionException, InterruptedException {
		if (Platform.isFxApplicationThread()){
			try{
				run.run();
			} catch (Exception e){
				throw new ExecutionException(e);
			}
		}
		else {
			final Lock lock = new ReentrantLock();
			final Condition condition = lock.newCondition();
			final ThrowableWrapper throwableWrapper = new ThrowableWrapper();
			lock.lock();
			try{
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						lock.lock();
						try {
							run.run();
						}catch (Throwable e){
							throwableWrapper.t = e;
						} finally {
							lock.unlock();
						}
					}
				});
				condition.await();
				if (throwableWrapper.t != null){
					throw new ExecutionException(throwableWrapper.t);
				}
			} finally {
				lock.unlock();
			}
		}
	}
}
