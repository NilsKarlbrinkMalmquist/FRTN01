// Code skeleton for the OpCom class in the Buttons exercise

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpCom extends Thread {
	private Regul regul;
	
	// Constructor
	public OpCom(Regul regul, int priority) {
        //TODO C2.E10: Store variables and set priority //
    }
	
	// run method
	public void run() {
        //NOTE: Make sure you understand what is happening //
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (!interrupted()) {
            System.out.print("K = ");
            try {
                regul.setK(Double.parseDouble(in.readLine()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }

        }
        System.out.println("OpCom stopped.");
    }
}
