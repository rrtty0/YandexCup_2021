package rrtty0.cup.yandex;

public class Matrix {

    private int rows;
    private int columns;
    private State state;
    private int stepNumber;
    private int differentNumbers;

    public Matrix(int n, int m){
        this.rows = n;
        this.columns = m;
        this.differentNumbers = n*m;
        setState();
        this.stepNumber = 0;
    }

    /**
     * Setting the initial state of the matrix
     */
    private void setState(){
        if(columns == 1 && rows == 1 && state != State.FINISH){
            state = State.FINISH;
        }
        else{
            if (rows >= columns)
                state=State.initalHor;
            else
                state=State.initalVer;
        }
    }

    /**
     * Get number of different numbers, which was at matrix
     * @return number of different numbers, which was at matrix
     */
    public int getDifferentNumbers(){
        return differentNumbers;
    }

    /**
     * Get current state of matrix
     * @return current state of matrix
     */
    public State getState(){
        return state;
    }

    /**
     * Performing one step of matrix transformation
     */
    public void nextStep(){
        switch (state){
            case initalHor:
                rows /= 2;
                if(columns > 1)
                    differentNumbers += columns/2;
                else
                    differentNumbers += 1;
                if(columns == 1 && rows == 1)
                    state = State.FINISH;
                else{
                    if(columns <= rows)
                        state = State.colROWcolEq;
                    else
                        state = State.COLrowColEq;
                }
                ++stepNumber;
                break;

            case initalVer:
                columns /= 2;
                if(rows > 1)
                    differentNumbers += rows/2;
                else
                    differentNumbers += 1;
                if(columns == 1 && rows == 1)
                    state = State.FINISH;
                else{
                    if(columns > rows)
                        state = State.COLrowRowEq;
                    else
                        state = State.colRowRowEq;
                }
                ++stepNumber;
                break;

            case colROWcolEq:
                rows /= 2;
                differentNumbers += columns;
                if(columns == 1 && rows == 1)
                    state = State.FINISH;
                else{
                    if(columns <= rows)
                        state = State.colROWcolEq;
                    else
                        state = State.COLrowColEq;
                }
                ++stepNumber;
                break;

            case COLrowColEq:
                columns /= 2;
                differentNumbers += 1;
                if(columns == 1 && rows == 1)
                    state = State.FINISH;
                else
                    state = State.colRowEq;
                ++stepNumber;
                break;

            case colRowEq:
                if(rows == columns && columns == 1){
                    state = State.FINISH;
                    break;
                }
                differentNumbers += columns;
                columns = 1;
                rows = 1;
                state = State.FINISH;
                ++stepNumber;
                break;

            case COLrowRowEq:
                columns /= 2;
                if(rows/Math.pow(2, stepNumber+1) % 1 == 0)
                    for (int i = 1; i <= stepNumber+1; ++i)
                        differentNumbers += rows/Math.pow(2, i);
                else
                    differentNumbers += rows;

                if(columns == 1 && rows == 1)
                    state = State.FINISH;
                else{
                    if(columns > rows)
                        state = State.COLrowRowEq;
                    else
                        state = State.colRowRowEq;
                }
                ++stepNumber;
                break;

            case colRowRowEq:
                if(rows == columns && columns == 1){
                    state = State.FINISH;
                    break;
                }
                rows /= 2;
                columns /= 2;
                differentNumbers += 2;
                if(columns == 1 && rows == 1)
                    state = State.FINISH;
                else
                    state = State.colRowEq;
                stepNumber += 2;
                break;

            case FINISH:
                break;
        }
    }

    /**
     * output of the current characteristics of the matrix
     */
    public void print(){
        System.out.println("");
        System.out.println(rows + " " + columns);
        System.out.println("State: " + state);
        System.out.println("StepNumber: " + stepNumber);
        System.out.println("Different numbers: " + differentNumbers);
        System.out.println("");
    }
}
