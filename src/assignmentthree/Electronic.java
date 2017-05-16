/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentthree;

/**
 * this is an electronic object that can be used in the eStore
 * @author Julian Sovernigo
 */
public class Electronic extends Product
{
    private String maker;
    
    public Electronic(String pID, String name, double price, int year, String maker) throws InvalidYearException, InvalidPriceException, InvalidProductIDException
    {
        super(pID, name, price, year);
        this.maker = maker;
    }
    
    public Electronic(Electronic other) throws InvalidYearException, InvalidPriceException, InvalidProductIDException
    {
        this(other.getpID(), other.getName(), other.getPrice(), other.getYear(), other.getMaker());
    }
    
    /**
     * returns a csv form of this object
     * @return a csv form of this object
     */
    @Override
    public String dataDump()
    {
        return super.dataDump()+"maker = \""+this.getMaker() + "\"\n";
    }
    
    /**
     * toString() 
     * returns a string when the object is attempted to be treated as a string in a print statement.
     * @return the string that will represent the object.
     */
    @Override
    public String toString()
    {
        return super.toString() + "Maker: " + maker;
    }
    
    /**
     * returns true if the two objects are equal to one another.
     * @param other
     * @return 
     */
    @Override
    public boolean equals(Object other)
    {
        if(this.getClass() == other.getClass())
        {
            if( this.getName().equals(((Product)other).getName())
                    && this.getPrice() == ((Product)other).getPrice()
                    && this.getpID().equals(((Product)other).getpID()) 
                    && this.getYear() == ((Product)other).getYear()) 
            {
                if(this.getMaker().equals(((Electronic)other).getMaker()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the maker
     */
    public String getMaker() 
    {
        return maker;
    }

    /**
     * @param maker the maker to set
     */
    public void setMaker(String maker) 
    {
        this.maker = maker;
    }
    
}
