package EventLoop.trunk.src;

public class Main {
	
	public static void main(String args[]) throws InterruptedException{
		final EventLoop loop =new EventLoop();
		loop.start();
		Thread.sleep(1000);
		final Event t =new Event(){
			@Override
			public void execute() {
				 System.out.println("Finally Event Execute");
				  
			}
		};
		loop.add(t);
		Thread.sleep(1000);
		loop.add(t);
		Thread.sleep(1000);
		loop.add(t);
		
		loop.add(t);
		loop.stopLoop();
		loop.add(t);
		loop.add(t);
	}
}
