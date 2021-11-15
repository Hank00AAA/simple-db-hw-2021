package simpledb.storage;

/** Unique identifier for HeapPage objects. */
public class HeapPageId implements PageId {

    private int tableID;
    private int pgNO;

    /**
     * Constructor. Create a page id structure for a specific page of a
     * specific table.
     *
     * @param tableId The table that is being referenced
     * @param pgNo The page number in that table.
     */
    public HeapPageId(int tableId, int pgNo) {
        this.tableID = tableId;
        this.pgNO = pgNo;
    }

    /** @return the table associated with this PageId */
    public int getTableId() {
        return this.tableID;
    }

    /**
     * @return the page number in the table getTableId() associated with
     *   this PageId
     */
    public int getPageNumber() {
        return this.pgNO;
    }

    /**
     * @return a hash code for this page, represented by a combination of
     *   the table number and the page number (needed if a PageId is used as a
     *   key in a hash table in the BufferPool, for example.)
     * @see BufferPool
     */
    public int hashCode() {
        return (int) (this.tableID * Math.pow(10, count(this.pgNO + 1)) + this.pgNO);
    }

    private int count(int a) {
        int c = 0;
        while (a > 0) {
            a /= 10;
            c++;
        }
        return c;
    }

    /**
     * Compares one PageId to another.
     *
     * @param o The object to compare against (must be a PageId)
     * @return true if the objects are equal (e.g., page numbers and table
     *   ids are the same)
     */
    public boolean equals(Object o) {
        try {
            HeapPageId pageID = (HeapPageId)o;
            if (this.pgNO != pageID.pgNO) {
                return false;
            }

            return this.tableID == pageID.tableID;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     *  Return a representation of this object as an array of
     *  integers, for writing to disk.  Size of returned array must contain
     *  number of integers that corresponds to number of args to one of the
     *  constructors.
     */
    public int[] serialize() {
        int[] data = new int[2];

        data[0] = getTableId();
        data[1] = getPageNumber();

        return data;
    }

}
