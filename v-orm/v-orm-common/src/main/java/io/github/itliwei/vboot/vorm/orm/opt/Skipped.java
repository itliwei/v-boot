package io.github.itliwei.vboot.vorm.orm.opt;

/**
 * Skip
 * Created by liwei on 2016/5/20.
 */
public class Skipped {

    public static final int DEFAULT_COUNT = 20;
    public static final int MAX_COUNT = 1000;

    private int skip;
    private int count = DEFAULT_COUNT;

    public Skipped(int skip) {
        this.skip = skip;
    }

    public Skipped(int skip, int count) {
        this.skip = skip;
        setCount(count);
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count > MAX_COUNT) {
            count = MAX_COUNT;
        }
        this.count = count;
    }
}
