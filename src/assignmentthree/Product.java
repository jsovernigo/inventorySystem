package assignmentthree;

/**
 * This is the encapsulation of an Item for the eStore.
 * It is extended by book and electronic.
 * @author julian
 */
public abstract class Product 
{
    private String pID; // Please note, due to inconsistency in the spec requiring integer storage AND leading zeroes, pID was changed to type STRING.
    private String name;
    private double price;
    private int year;
    
    /**
     * This is the Constructor for the Item class.
     * @param pID the product id
     * @param name the name of the product
     * @param price the price of the object
     * @param year the year that it was produced
     */
    public Product(String pID, String name, double price, int year) throws InvalidYearException, InvalidPriceException, InvalidProductIDException
    {
        if(pID.length() >= 6)
        {   
            this.pID = pID;
        }
        else
        {
            throw new InvalidProductIDException();
        }
        
        this.name = name;
         
        if(price > 0)
        {
            this.price = price;
        }
        else
        {
            throw new InvalidPriceException();
        }
        if(year >= 1000 && year <= 9999)
        {
            this.year = year;
        }
        else
        {
            throw new InvalidYearException();
        }
    }
    
    /**
     * Default Constructor.  Initializes the values to default values.
     */
    public Product(Product other) throws InvalidYearException, InvalidPriceException, InvalidProductIDException
    {
        this(other.getpID(), other.getName(), other.getPrice(), other.getYear());
    }
    
    /**
     * returns a csv form of the object
     * @return a csv form of the object
     */
    public String dataDump()
    {
        String type;
        if(this instanceof Book)
        {
            type = "book";
        }
        else if (this instanceof Electronic)
        {
            type = "electronic";
        }
        else
        {
            type = "unknown";
        }
        return "type = \""+type+ "\"\nproductID = \""+this.getpID()+"\"\nname = \""+this.getName()+"\"\nprice = \""+this.getPrice()+"\"\nyear = \""+this.getYear()+"\"\n";
    }
    
    /**
     * toString() 
     * returns a string when the object is attempted to be treated as a string in a print statement.
     * @return the string that will represent the object.
     */
    @Override
    public String toString()
    {
        return("Product ID: " + pID + "\nName: " + name + "\nPrice: " + price + "\nYear: " + year + "\n");
    }
    
    /**
     * returns true if the two objects are equal to one another.
     * Otherwise, return false.
     * @param other
     * @return 
     */
    @Override
    public boolean equals(Object other)
    {
        if(other.getClass() ==this.getClass())
        {       
            if(this.getName().equals(((Product)other).getName())
                    && this.getPrice() == ((Product)other).getPrice()
                    && this.getpID().equals(((Product)other).getpID()) 
                    && this.getYear() == ((Product)other).getYear())
            {
                return true;
            }
        }
        
        return false;
    }

    /**
     * @return the pID
     */
    public String getpID() 
    {
        return pID;
    }

    /**
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @return the price
     */
    public double getPrice() 
    {
        return price;
    }

    /**
     * @return the year
     */
    public int getYear() 
    {
        return year;
    }

    /**
     * @param pID the pID to set
     */
    public void setpID(String pID) 
    {
        if(pID.length() > 6)
        {
            this.pID = pID;
        }
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) throws InvalidYearException 
    {
        if(year < 1000 || year > 999)
        {
            throw new InvalidYearException();
        }
        this.year = year;
    }
    
}
