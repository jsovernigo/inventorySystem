
package assignmentthree;

/**
 * This is simply the "main class" of the program.  It invokes the constructor for
 * both of the other classes used in this program.
 * @author julian
 */
public class AssignmentThree {

    /**
     * invokes the constructor for the estore, then passes it to the GUI interface.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InvFrame frame;
        EStoreSearch estore;
        String fname;
        
        if(args.length != 1)
        {
            fname = "inventory.txt";
        }
        else
        {
            fname = args[0];
        }
        
        estore = new EStoreSearch();
        frame = new InvFrame(estore,fname);
        
        return;
    }
    
}
