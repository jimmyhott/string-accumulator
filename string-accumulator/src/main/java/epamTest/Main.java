package epamTest;

public class Main {

	public static void main(String[] args) {
		
		StringAccumulator stringAccumulator = new StringAccumulator();
		
		try {
			System.out.println(stringAccumulator.add("//$$$|xxx\n1$$$20$$$3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
