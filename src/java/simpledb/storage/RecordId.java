package simpledb.storage;

import java.io.Serializable;

/**
 * A RecordId is a reference to a specific tuple on a specific page of a
 * specific table.
 */
public class RecordId implements Serializable {

    private static final long serialVersionUID = 1L;

    private PageId pageID;
    private int tupleNO;

    /**
     * Creates a new RecordId referring to the specified PageId and tuple
     * number.
     * 
     * @param pid
     *            the pageid of the page on which the tuple resides
     * @param tupleno
     *            the tuple number within the page.
     */
    public RecordId(PageId pid, int tupleno) {
        this.pageID = pid;
        this.tupleNO = tupleno;
    }

    /**
     * @return the tuple number this RecordId references.
     */
    public int getTupleNumber() {
        return this.tupleNO;
    }

    /**
     * @return the page id this RecordId references.
     */
    public PageId getPageId() {
        return pageID;
    }

    /**
     * Two RecordId objects are considered equal if they represent the same
     * tuple.
     * 
     * @return True if this and o represent the same tuple
     */
    @Override
    public boolean equals(Object o) {
        try {
            RecordId r = (RecordId)o;
            if (this.tupleNO != r.tupleNO) {
                return false;
            }

            return this.pageID.equals(r.pageID);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * You should implement the hashCode() so that two equal RecordId instances
     * (with respect to equals()) have the same hashCode().
     * 
     * @return An int that is the same for equal RecordId objects.
     */
    @Override
    public int hashCode() {
        return (int) (this.pageID.hashCode() * Math.pow(10, count(this.tupleNO + 1)) + this.tupleNO);
    }

    private int count(int a) {
        int c = 0;
        while (a > 0) {
            a /= 10;
            c++;
        }
        return c;
    }

}
