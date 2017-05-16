
package assignmentthree;

/**
 * This is a Book object that is used in the eStore.
 * @author julian
 */
public class Book extends Product
{
    private String author;
    private String publisher;
    
    /**
     * This is the constructor for the Book item.  Book extends
     * Item, hence the call to super();
     * @param pID the product id
     * @param name the name of the product
     * @param price the price of the product
     * @param year the year it was produced
     * @param author the author of the book
     * @param publisher the publisher
     * @throws InvalidYearException
     * @throws InvalidPriceException
     * @throws InvalidProductIDException 
     */
    public Book(String pID, String name, double price, int year, String author, String publisher) throws InvalidYearException, InvalidPriceException, InvalidProductIDException
    {
        super(pID, name, price, year);
        this.author = author;
        this.publisher = publisher;
    }

    /**
     * This is the copy constructor
     */
    public Book(Book other) throws InvalidYearException, InvalidPriceException, InvalidProductIDException
    {
        this(other.getpID(), other.getName(), other.getPrice(), other.getYear(), other.getAuthor(), other.getPublisher());
    }
    
    /**
     * returns the string csv form of this object.
     * @return a csv form of the object.
     */
    @Override
    public String dataDump()
    {
        return super.dataDump()+"author = \""+this.getAuthor()+"\"\npublisher = \""+this.getPublisher()+"\"\n";
    }
    
    /**
     * toString() returns a string when the object is attempted to be treated as a string in a print statement.
     * @return the string that will represent the object.
     */
    @Override
    public String toString()
    {
        return super.toString() + "Author: " + author + "\nPublisher: " + publisher;
    }
    
    /**
     * returns true if the two objects are the same type.
     * @param other
     * @return 
     */
    @Override
    public boolean equals(Object other)
    {
        if(other.getClass() == getClass())
        {
            if(this.getName().equals(((Product)other).getName())
                    && this.getPrice() == ((Product)other).getPrice()
                    && this.getpID().equals(((Product)other).getpID()) 
                    && this.getYear() == ((Product)other).getYear())
            {
                if(((Book)other).getAuthor().equals(this.getAuthor()) && ((Book)other).getPublisher().equals(this.getPublisher()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the author
     */
    public String getAuthor() 
    {
        return author;
    }
    
    /**
     * @return the publisher
     */
    public String getPublisher() 
    {
        return publisher;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }
    
}
