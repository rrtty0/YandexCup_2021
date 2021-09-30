package rrtty0.cup.yandex;

/**
 * Matrix state
 */
public enum State {

    /**
     * 1 - Inital state of matrix
     * 2 - Next step is Horizontal flip
     */
    initalHor,

    /**
     * 1 - Inital state of matrix
     * 2 - Next step is Vertical flip
     */
    initalVer,

    /**
     * 1 - Number of Columns <= Number of Rows
     * 2 - Each column contains the same numbers, but the numbers between the columns are different
     */
    colROWcolEq,

    /**
     * 1 - Number of Columns > Number of Rows
     * 2 - Each column contains the same numbers, but the numbers between the columns are different
     */
    COLrowColEq,

    /**
     * 1 - Number of Columns = Number of Rows
     * 2 - All cells of the matrix contain one number
     */
    colRowEq,

    /**
     * 1 - Number of Columns > Number of Rows
     * 2 - Each row contains the same numbers, but the numbers between the rows are different
     */
    COLrowRowEq,

    /**
     * 1 - Number of Columns = Number of Rows
     * 2 - Each row contains the same numbers, but the numbers between the rows are different
     */
    colRowRowEq,

    /**
     * 1 - Finish state
     */
    FINISH

}
