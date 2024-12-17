package io.seoleir.model;

public class TerminalLine {

    private Integer width;

    private Integer height;

    private String value;

    private Boolean isTreeArea;

    public TerminalLine() {
    }

    public TerminalLine(Integer width, Integer height, String value, Boolean isTreeArea) {
        this.width = width;
        this.height = height;
        this.value = value;
        this.isTreeArea = isTreeArea;
    }

    public TerminalLine(String value, Boolean isTreeArea) {
        this.value = value;
        this.isTreeArea = isTreeArea;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getTreeArea() {
        return isTreeArea;
    }

    public void setTreeArea(Boolean treeArea) {
        isTreeArea = treeArea;
    }

    @Override
    public String toString() {
        return "TerminalLine{" +
               "width=" + width +
               ", height=" + height +
               ", value='" + value + '\'' +
               ", isTreeArea=" + isTreeArea +
               '}';
    }
}
