
package assignmentthree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class defines an estore that can be used to store any object that extends
 * the Product class.  It offers various methods for the manipulation of the 
 * inventory.  It should be used with a suitable menu method to call them.
 * @author julian
 */
public class EStoreSearch 
{
    private ArrayList<Product> inventory;
    private HashMap<String,ArrayList<Integer>> inventoryHash;
    
    /**
     * Default constructor for the EStore.
     */
    public EStoreSearch()
    {
        inventory = new ArrayList<Product>();
        inventoryHash = new HashMap<String,ArrayList<Integer>>(100);
    }
    
    /**
     * returns true if two objects are equal.
     * @param o
     * @return true if equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(o.getClass() == this.getClass())
        {
            if(this.toString() == o.toString())
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * creates and returns a string representation of the inventory.
     * @return a string representation of the inventory.
     */
    public String toString()
    {
        String inv;
        inv = "";
        for(Product p : inventory)
        {
            inv += p;
        }
        return inv;
    }
    
    /**
     * Allows the printing of the inventory in a readable form.
     * @param products 
     */
    public void printList(ArrayList<Product> products)
    {
        for(Product p : products)
        {
            System.out.println(p+"\n");
        }
    }
    
    /**
     * searchFor
     * searches through the lists for the different fields that are required.
     * Setting pid to "", syear to -1, eyear to -1, or searchTerms to null will
     * disable them in the search, causing all elements to be considered in their
     * search.
     * @param pid the product id
     * @param sYear the starting year
     * @param eYear the ending year
     * @param searchTerms the search terms that will be checked for in the products
     * @return 
     */
    public ArrayList<Product> searchFor(String pid, int sYear, int eYear, String[] searchTerms)
    {
        int i;
  
        ArrayList<Product> matches = new ArrayList<Product>();        
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        
        for(i = 0; i < inventory.size(); i++)
        {
            indexes.add(i);
        }
        if(searchTerms != null && searchTerms.length > 0) // if we should search
        {
            for(String term : searchTerms)
            {
                indexes = intersectInt(indexes, inventoryHash.get(term.toLowerCase()));
            
            }
        }
        
        if(sYear > 0 && eYear > 0)
        {
            for(i = 0; i < indexes.size(); i++)
            {
                int pYear;
                pYear = inventory.get(indexes.get(i)).getYear();
                if(pYear > eYear || pYear < sYear)
                {
                    indexes.remove(i);
                    i--;
                }
                System.out.println();
            }
        }
        
        if(pid != null && pid.length() == 6)
        {
            for(i = 0; i < indexes.size(); i++)
            {
                String ppid;
                ppid = inventory.get(indexes.get(i)).getpID();
                if(!ppid.equals(pid))
                {
                    indexes.remove(i);
                    i--;
                }
            }
        }
        
        for(int pos : indexes)
        {
            matches.add(inventory.get(pos));
        }
        
        return matches;
    }
     
    /**
     * Creates an intersection of two arraylists, allowing for the elimination
     * of non-common members.
     * @param A - the first ArrayList
     * @param B - the other ArrayList.
     * @return the intersection, (AnB)
     */
    public ArrayList<Product> intersect(ArrayList<Product> A, ArrayList<Product> B)
    {
        ArrayList<Product> intersections;
        
        intersections = new ArrayList<Product>();
        
        for(Product aProduct: A)
        {
            if(B.contains(aProduct))
            {
                intersections.add(aProduct);
            }
        }
        return intersections;
    }
    
    /**
     * Creates an intersection of two arraylists, allowing for the elimination
     * of non-common members.
     * @param A - the first ArrayList
     * @param B - the other ArrayList.
     * @return the intersection, (AnB)
     */
    public ArrayList<String> intersectStr(ArrayList<String> A, ArrayList<String> B)
    {
        ArrayList<String> intersections;
        
        intersections = new ArrayList<String>();
        
        for(String aProduct: A)
        {
            if(B.contains(aProduct))
            {
                intersections.add(aProduct);
            }
        }
        return intersections;
    }
    
    /**
     * Creates an intersection of two arraylists, allowing for the elimination
     * of non-common members.
     * @param A - the first ArrayList
     * @param B - the other ArrayList.
     * @return the intersection, (AnB)
     */
    public ArrayList<Integer> intersectInt(ArrayList<Integer> A, ArrayList<Integer> B)
    {
        ArrayList<Integer> intersections;
        
        intersections = new ArrayList<Integer>();
        if(A == null || B == null || A.isEmpty() || B.isEmpty())
        {
            return intersections;
        }
        
        for(int aProduct: A)
        {
            if(B.contains(aProduct))
            {
                intersections.add(aProduct);
            }
        }
       
        return intersections;
    }
    
    /**
     * isDuplicate
     * searches through the two array lists in order to find a duplicate product
     * id.  Returns true if a duplicate is found.
     * @param pid the product id number
     * @return true if duplicate is found, false if pid is unique.
     */
    public boolean isDuplicate(String pid)
    {
        int i;
        if(pid.length() > 6)
        {
            return false;
        }
        for(i = 0; i < inventory.size(); i++)
        {
            if(inventory.get(i).getpID().equals(pid))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * checks if i's pid is valid (addable), then adds it.
     * @param i 
     * @return true on success, false on failure.
     */
    public boolean addItem(Product i)
    {
        if(!isDuplicate(i.getpID()))
        {
            inventory.add(i);
            this.repopulateHash();
            return true;
        }
        return false;
    }
    
    /**
     * This method uses the <code>dumpContents()</code> to print the inventory
     * to the screen.  Be careful, as <code>load()</code> is destructive, and
     * can overwrite the information stored in a previous file if an empty
     * file is loaded, then saved over a previous file.
     * @param fileName 
     */
    public void save(String fileName)
    {
        String conts;
        File f;
        PrintWriter fout;
        f = new File(fileName);
        try
        {
            
            fout = new PrintWriter(f);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No file found.");
            return;
        }
        
        conts = this.dumpContents(); // writes the contents to a string
        fout.write(conts); // writes the contents to the file we specified
        
        fout.close(); // always close your files ;)
    }
    
    /**
     * this method reads in the inventory from a file named filename.
     * @param fileName 
     */
    public void load(String fileName)
    {
        File f = new File(fileName);
        Scanner fin;

        try
        {
            fin = new Scanner(f);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File was not found.");
            return;
        }

        while (fin.hasNextLine()) 
        {
            int i;
            String line;
            line = fin.nextLine();
            if(line.equals("type = \"book\""))
            {
                String data;
                
                String pid;
                String name;
                float price;
                int year;
                String authors;
                String publishers;
                
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    pid = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    name = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    price = Float.parseFloat(data.substring(data.indexOf("\"")+1, data.lastIndexOf("\"")));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    year = Integer.parseInt(data.substring(data.indexOf("\"")+1, data.lastIndexOf("\"")));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    authors = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    publishers = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
                }
                else
                {
                    fin.close();
                    return;
                }
                
                Book b;
                b = null;
                try {
                    b = new Book(pid, name, price, year, authors, publishers);
                } catch (InvalidYearException ex) {
                    Logger.getLogger(EStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidPriceException ex) {
                    Logger.getLogger(EStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidProductIDException ex) {
                    Logger.getLogger(EStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(b != null)
                {
                    addItem(b);
                }
                if(fin.hasNextLine())
                {
                    fin.nextLine();
                }
                else
                {
                    fin.close();
                }
            }
            else if(line.equals("type = \"electronic\""))
            {
                String data;
                
                String pid;
                String name;
                float price;
                int year;
                String maker;
                
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    pid = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    name = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    price = Float.parseFloat(data.substring(data.indexOf("\"")+1, data.lastIndexOf("\"")));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    year = Integer.parseInt(data.substring(data.indexOf("\"")+1, data.lastIndexOf("\"")));
                }
                else
                {
                    fin.close();
                    return;
                }
                if(fin.hasNextLine())
                {
                    data = fin.nextLine();
                    maker = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
                }
                else
                {
                    fin.close();
                    return;
                }
                
                Electronic e;
                e = null;
                try {
                    e = new Electronic(pid,name,price,year,maker);
                } catch (InvalidYearException ex) {
                    Logger.getLogger(EStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidPriceException ex) {
                    Logger.getLogger(EStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidProductIDException ex) {
                    Logger.getLogger(EStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(e != null)
                {
                    addItem(e);
                }
                if(fin.hasNextLine())
                {
                    fin.nextLine();
                }
                else
                {
                    fin.close();
                }
            }
            else
            {
                while(fin.hasNextLine())
                {
                    if(fin.nextLine().equals("\n"))
                    {
                        break;
                    }
                } // read the rest of crap
            } 
        }
        fin.close();
    }
    
    /**
     * Dumps the contents of the estore into a string, which is returned.
     * @return contents: the added-together datadump of the whole estore.
     */
    public String dumpContents()
    {
        String contents;
        contents = "";
        for(Product p : inventory)
        {
            contents += p.dataDump() + "\n";
        }
        return contents;
    }
    
      
    /**
     * populateHash
     * populates our hashmap with mapping pairs described by our arraylist.
     */
    public void repopulateHash()
    {
        this.inventoryHash.clear();
        
        String allkeys;
        ArrayList<String> keys;
        
        allkeys = "";
        
        for(Product p : inventory) // loop through the inventory
        {
            allkeys += p.getName().toLowerCase()+ " "; // concatenate all the strings
        }

        // this will end up being the array of ALL searchable terms.
        keys = new ArrayList<String>(Arrays.asList(allkeys.split("[ ]+")));
        
        for(String key : keys) // loop through the keys
        {
            int i;
            ArrayList<Integer> indexes;
            
            indexes = new ArrayList<Integer>();
            for(i = 0; i < inventory.size(); i++) // loop throught the inventory
            {
                // TODO if we actually find something...
                if(inventory.get(i).getName().toLowerCase().matches(".*\\b"+key.toLowerCase()+"\\b.*"))
                {
                    indexes.add(i); // add the index, since we do in fact have the correct search term.
                }
            }         
            this.inventoryHash.put(key, indexes); // adds the search key to the hash map.
        }
    }
}
