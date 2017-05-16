package assignmentthree;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class contains the code for a GUI interface for the EStore class.  This
 * is to make it more usable, and to make it more user friendly.
 * @author julian
 */
public class InvFrame extends JFrame{
    
    private JTextField pidField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField yearField;
    private JTextField authorField;
    private JTextField publisherField;
    private JTextField makerField;
    
    private JComboBox type;
    
    private JButton addButton;
    
    
    private JTextField pidSearch;
    private JTextField termsSearch;
    
    private JComboBox sYearSearch;
    private JComboBox eYearSearch;
    
    private JButton searchButton;
    
    private JTextArea output;
    private JComboBox sysIn;
 
    private JButton resetAddButton;
    private JButton resetSearchButton;
    
    private EStoreSearch estore;
    
    private JPanel addPanel;
    private JPanel searchPanel;
    private JPanel welcomePanel;
    private JPanel interactivePanel;
    
    private JPanel pidPanel;
    private JPanel namePanel;
    private JPanel pricePanel;
    private JPanel yearPanel;
    private JPanel authorPanel;
    private JPanel publisherPanel;
    private JPanel makerPanel;
    private JPanel typePanel;
    
    private JLabel outputLabel;
    
    private String filename;
    
    String validYears[];
    
    /**
     * Constructor for the GUI.  Takes in an estore that will be used to manage inventory.
     * @param estore 
     */
    public InvFrame(EStoreSearch estore, String fname)
    {
        super("EStore");        
        this.setLayout(new BorderLayout());
        this.setSize(900,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.estore = estore;
        this.filename = fname;
        
        String[] commands = {"Add","Search","Quit"};
        String[] types = {"Book","Electronic"};
        validYears = new String[9001];
        validYears[0] = " ";
        for(int i = 0; i < 9000; i++)
        {
            validYears[i+1] = ""+(1000+i);
        }
        
        pidField = new JTextField(40);
        nameField = new JTextField(40);
        priceField = new JTextField(40);
        yearField = new JTextField(40);
        type = new JComboBox(types);
        type.addActionListener(new ProductChangedListener());
        authorField = new JTextField();
        authorField.setEditable(type.getSelectedItem().equals("Book"));
        publisherField = new JTextField();
        publisherField.setEditable(type.getSelectedItem().equals("Book"));
        makerField = new JTextField();
        addButton = new JButton("Add Product");
        addButton.addActionListener(new AddListener());
        pidSearch = new JTextField(6);
        sYearSearch = new JComboBox(validYears);
        eYearSearch = new JComboBox(validYears);
        termsSearch = new JTextField(30);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchListener());
        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scrollable = new JScrollPane(output);
        sysIn = new JComboBox(commands);
        resetAddButton = new JButton("Reset");
        resetAddButton.addActionListener(new ResetListener());
        outputLabel = new JLabel();
        outputLabel.setText("");
        resetSearchButton = new JButton("Reset");
        resetSearchButton.addActionListener(new ResetListener());
        sysIn.addActionListener(new CommandListener());
        
        JPanel mainPanel = new JPanel();
        JPanel ASPanel = new JPanel();
        addPanel = new JPanel();
        searchPanel = new JPanel();
        welcomePanel = new JPanel();
        interactivePanel = new JPanel();
        JPanel outputPanel = new JPanel();
        JPanel addFieldsPanel = new JPanel();
        JPanel addButtonsPanel = new JPanel();
        JPanel searchFieldsPanel = new JPanel();
        JPanel searchButtonsPanel = new JPanel();
        
        typePanel = new JPanel();
        pidPanel = new JPanel();
        namePanel = new JPanel();
        pricePanel = new JPanel();
        yearPanel = new JPanel();
        authorPanel = new JPanel();
        publisherPanel = new JPanel();
        makerPanel = new JPanel();
        addPanel.setVisible(false);
        searchPanel.setVisible(false);
        welcomePanel.setVisible(true);
        
        
        addPanel.setLayout(new GridLayout(1,2,10,10));
        addFieldsPanel.setLayout(new BoxLayout(addFieldsPanel, BoxLayout.Y_AXIS));
        addButtonsPanel.setLayout(new GridLayout(2,1));
        addFieldsPanel.add(new JLabel("Adding A Product"));
        typePanel.setLayout(new GridLayout(2,1));
        typePanel.add(new JLabel("Type:"));
        typePanel.add(this.type);
        addFieldsPanel.add(typePanel);
        pidPanel.setLayout(new GridLayout(2,1));
        pidPanel.add(new JLabel("Product ID:"));
        pidPanel.add(this.pidField);
        addFieldsPanel.add(pidPanel);
        namePanel.setLayout(new GridLayout(2,1));
        namePanel.add(new JLabel("Product Name:"));
        namePanel.add(this.nameField);
        addFieldsPanel.add(namePanel);
        pricePanel.setLayout(new GridLayout(2,1));
        pricePanel.add(new JLabel("Product Price"));
        pricePanel.add(this.priceField);
        addFieldsPanel.add(pricePanel);
        yearPanel.setLayout(new GridLayout(2,1));
        yearPanel.add(new JLabel("Product Year"));
        yearPanel.add(this.yearField);
        addFieldsPanel.add(yearPanel);
        authorPanel.setLayout(new GridLayout(2,1));
        authorPanel.add(new JLabel("Product Author"));
        authorPanel.add(this.authorField);
        addFieldsPanel.add(authorPanel);
        publisherPanel.setLayout(new GridLayout(2,1));
        publisherPanel.add(new JLabel("Product Publisher"));
        publisherPanel.add(this.publisherField);
        addFieldsPanel.add(publisherPanel);
        makerPanel.setLayout(new GridLayout(2,1));
        makerPanel.add(new JLabel("Product Manufacturer"));
        makerPanel.add(this.makerField);
        addFieldsPanel.add(makerPanel);
        makerPanel.setVisible(false);
        
        JPanel upperAddButton = new JPanel();
        upperAddButton.add(addButton);
        JPanel lowerAddButton = new JPanel();
        lowerAddButton.add(resetAddButton);
        
        addButtonsPanel.add(upperAddButton);
        addButtonsPanel.add(lowerAddButton);
        addPanel.add(addFieldsPanel);
        addPanel.add(addButtonsPanel);
        
        searchPanel.setLayout(new GridLayout(1,2));
        searchFieldsPanel.setLayout(new GridLayout(9,1));
        searchButtonsPanel.setLayout(new GridLayout(2,1));
        searchFieldsPanel.add(new JLabel("Searching for Products"));
        searchFieldsPanel.add(new JLabel("Product ID"));
        searchFieldsPanel.add(this.pidSearch);
        searchFieldsPanel.add(new JLabel("Starting Year"));
        searchFieldsPanel.add(this.sYearSearch);
        searchFieldsPanel.add(new JLabel("Ending Year"));
        searchFieldsPanel.add(this.eYearSearch);
        searchFieldsPanel.add(new JLabel("Search Terms"));
        searchFieldsPanel.add(this.termsSearch);
        
        JPanel upperSearchButton = new JPanel();
        upperSearchButton.add(searchButton);
        JPanel lowerSearchButton = new JPanel();
        lowerSearchButton.add(resetSearchButton);
        
        searchButtonsPanel.add(upperSearchButton);
        searchButtonsPanel.add(lowerSearchButton);
        searchPanel.add(searchFieldsPanel);
        searchPanel.add(searchButtonsPanel);
        ASPanel.setLayout(new BoxLayout(ASPanel, BoxLayout.X_AXIS));
        ASPanel.add(addPanel);
        ASPanel.add(searchPanel);
        
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.add(outputLabel);
        outputPanel.add(scrollable);
        interactivePanel.setLayout(new GridLayout(2,1,0,0));
        interactivePanel.add(ASPanel);
        interactivePanel.add(outputPanel);
        
        welcomePanel.add(new JLabel("Welcome to the EStore.  Please select a command above."),BorderLayout.CENTER);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(welcomePanel);
        mainPanel.add(interactivePanel);
        interactivePanel.setVisible(false);
        addPanel.setVisible(false);
        searchPanel.setVisible(false);
        this.add(sysIn, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        estore.load(filename);
        this.setVisible(true);
    }
     
    /**
     * adds an item, whose information is stored in the textfields on the GUI,
     * to the estore, and then prints the contents of the add to the screen.
     */
    public void add()
    {
        String productID;
        String name;
        double price;
        int year;
     
        boolean success;
        
        success = false;
        
        productID = pidField.getText();
        if(productID.length() != 6)
        {
            println("Invalid Product ID.");
            return;
        }
        
        name = nameField.getText();
        if(name.length() < 1)
        {
            println("Invalid Product Name.");
            return;
        }
        
        try
        {
            price = Float.parseFloat(priceField.getText());
            if(price < 0)
            {
                println("Invalid price.");
                return;
            }
        }catch(NumberFormatException e)
        {
            println("Invalid price.");
            return;
        }
        
        try
        {
            year = Integer.parseInt(yearField.getText());
            if(year < 1800)
            {
                println("Invalid Year.");
                return;
            }
        }catch(NumberFormatException e)
        {
            println("Invalid Year");
            return;
        }
        
        if(this.type.getSelectedItem().equals("Book"))
        {
            Book b;
            String author;
            String publisher;
            
            b = null;
            
            author = authorField.getText();
            if(author.length() < 1)
            {
                println("Author line was left blank, so assumed Unknown Author.");
                author = "Unknown Author";
            }
            
            publisher = publisherField.getText();
            if(publisher.length() < 1)
            {
                println("Publisher line was left blank, so assumed Unknown Publisher.");
                publisher = "Unknown Publisher";
            }
            try {
                b = new Book(productID, name, price, year, author, publisher);
                println("Adding book to the inventory:\n"+b+"\n");
                success = estore.addItem(b);
            } catch (InvalidYearException ex) {
                success = false;
                println("There was an error in creating the product <InvalidYearException>");
            } catch (InvalidPriceException ex) {
                success = false;
                println("There was an error in creating the product <InvalidPriceException>");
            } catch (InvalidProductIDException ex) {
                success = false;
                println("There was an error in creating the product <InvalidProductIDException>");
            }
            
            
        }
        else if(this.type.getSelectedItem().equals("Electronic"))
        {
            Electronic e;
            String maker;
            
            e = null;
            maker = makerField.getText();
            if(maker.length() < 1)
            {
                println("Manufacturer line was left blank, so assumed Unknown Maker.");
                maker = "Unknown Maker";
            }
            try
            {
                e = new Electronic(productID, name, price, year, maker);
                println("Adding Electronic to inventory:\n"+e+"\n");
                success = estore.addItem(e);
            } catch (InvalidYearException ex) {
                success = false;
                println("There was an error in creating the product <InvalidYearException>");
            } catch (InvalidPriceException ex) {
                success = false;
                println("There was an error in creating the product <InvalidPriceException>");
            } catch (InvalidProductIDException ex) {
                success = false;
                println("There was an error in creating the product <InvalidProductIDException>");
            }
        }
        if(success)
        {
            println("Item was successfully added.\n");
        }
        else
        {
            println("The product that was added contained already existing Product ID.\n");
        }
    }
    
    /**
     * This function is an interface between the estore's searchFor function, and
     * the various textfields used in this JFrame
     */
    public void search()
    {
        String pid;
        String searchTerms[];
        int syear;
        int eyear;
        
        ArrayList<Product> found;
        
        pid = pidSearch.getText();
        if(pid.isEmpty())
        {
            pid = null;
        }
        try
        {
            syear = Integer.parseInt((String)this.sYearSearch.getSelectedItem());
        }catch(NumberFormatException e)
        {
            syear = -1;
        }
        
        try
        {
            eyear = Integer.parseInt((String)this.eYearSearch.getSelectedItem());
        }catch(NumberFormatException e)
        {
            eyear = -1;
        }
        
        if(syear > eyear)
        {
            this.println("Error: Ending year preceeds starting year.");
            return;
        }
        
        searchTerms = this.termsSearch.getText().split("[ ]+");
        if(searchTerms.length == 0 || searchTerms[0].equals(""))
        {
            searchTerms = null;
        }
        
        found = estore.searchFor(pid, syear, eyear, searchTerms);
        
        if(found.size() > 0)
        {
            for(Product p : found)
            {
                println(p.toString()+"\n");
            }
        }
        else
        {
            println("No products were found.");
        }
    }
    
    /**
     * println
     * utilizes <code>println()</code> to print a message - out - followed by a
     * newline character to the screen.
     * @param out 
     */
    public void println(String out)
    {
        print(out+"\n");
    }
    
    /**
     * print
     * prints out the message - out - to the output panel on the GUI
     * @param out 
     */
    public void print(String out)
    {
        output.append(out);
    }
    
    /**
     * Clears the fields in the GUI, so that they can easily be overwritten
     */
    public void clearFields()
    {
        this.pidField.setText("");
        this.nameField.setText("");
        this.priceField.setText("");
        this.yearField.setText("");
        this.authorField.setText("");
        this.publisherField.setText("");
        this.makerField.setText("");
        
        this.pidSearch.setText("");
        this.termsSearch.setText("");
    }
    
    /**
     * exitSystem
     * exits the system when the quit button is pressed.
     * if the filename is currently populated, we can save the estore to it.
     */
    public void exitSystem()
    {
        if(this.filename != null && !this.filename.isEmpty())
        {
            estore.save(filename);
        }
        System.exit(0);
    }
    
    /**
     * This class is the action listener for the ADD button.
     */
    public class AddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            add();
        }
    }
    
    /**
     * This class is the action listener for the search button.
     */
    public class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            search();
        }
    }
    
    /**
     * This class is the action listener for the reset button.
     */
    public class ResetListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearFields();
        }
    }
              
    /**
     * This class is the action listener for the sysEnterButton.
     */
    public class CommandListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(sysIn.getSelectedItem().equals("Add"))
            {
                interactivePanel.setVisible(true);
                addPanel.setVisible(true);
                welcomePanel.setVisible(false);
                searchPanel.setVisible(false);
                outputLabel.setText("Messages");
            }
            else if(sysIn.getSelectedItem().equals("Search"))
            {
                interactivePanel.setVisible(true);
                searchPanel.setVisible(true);
                welcomePanel.setVisible(false);
                addPanel.setVisible(false);
                outputLabel.setText("Search Results");
            }
            else if(sysIn.getSelectedItem().equals("Quit"))
            {
                exitSystem();
            }
        }
    }
    
    /**
     * This class is the action listener for the book/elec radio buttons.
     */
    public class ProductChangedListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            authorPanel.setVisible(type.getSelectedItem().equals("Book"));
            publisherPanel.setVisible(type.getSelectedItem().equals("Book"));
            makerPanel.setVisible(type.getSelectedItem().equals("Electronic"));
        }
        
    }
    
    
}
