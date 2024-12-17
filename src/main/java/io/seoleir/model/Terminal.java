package io.seoleir.model;

public class Terminal {

    private Integer width;

    private Integer height;

    public Terminal(Integer width, Integer height) {
        this.width = width;
        this.height = height;
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

    @Override
    public String toString() {
        return "Terminal{" +
               "width=" + width +
               ", height=" + height +
               '}';
    }
}
