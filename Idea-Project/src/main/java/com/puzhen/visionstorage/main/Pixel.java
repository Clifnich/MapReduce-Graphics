package com.puzhen.visionstorage.main;

import java.util.Arrays;

public class Pixel {

    private int row;
    private int column;
    private int[] contents;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int[] getContents() {
        return contents;
    }

    public void setContents(int[] contents) {
        this.contents = contents;
    }

    public Pixel(int row, int column, int[] dimensions) {
        this.row = row;
        this.column = column;
        this.contents = dimensions;
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "row=" + row +
                ", column=" + column +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }

    public Pixel() {}
}
