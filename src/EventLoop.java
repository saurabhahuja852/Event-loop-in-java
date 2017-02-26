package EventLoop.trunk.src;


import java.util.LinkedList;
import java.util.Queue;

public class EventLoop implements Runnable {

	
	public EventLoop() {
		// TODO Auto-generated constructor stub
	}
	private boolean loopStarted =false;
	private boolean loopEnd =false;
	
	private Queue<Event> eventQueue =new LinkedList<Event>();
	public void start(){
		if(!loopStarted){
			loopStarted =true;
			new Thread(this).start();
		}
		
	}
	public void add(Event e) {
	  if(!loopEnd) {
		synchronized (eventQueue) {
			if(!loopEnd){
				eventQueue.add(e);
				eventQueue.notify();
			}
		 }
	  }else{
		  System.out.println("not added as loop is stopped");
	  }
	}
	
	public void stopLoop(){
    	if(!loopEnd){
		synchronized (eventQueue) {
			if(!loopEnd){
				eventQueue.notify();
				loopEnd =true;
			}
		 }
		}
	}
	
	
	@Override
	public void run() {
		while(true){
			Event t=null;
			synchronized(eventQueue){
					if(eventQueue.isEmpty()){
						try {
							if(!loopEnd){
								System.out.println("Going to wait");
								eventQueue.wait();
							}else{
								break;
							}
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(!eventQueue.isEmpty()){
						t= eventQueue.poll();
					}
				
			}
			if(t!=null){
				System.out.println("Executing");
				t.execute();
			}
		}
		System.out.println("***Event loop stoped after finishing all jobs***");
	}
	
		
	

}
