package com.refresh.pos.app03;

public class SingleData {
    private static volatile SingleData instance;
    private static int cnt = 0;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public static SingleData getInstance() {
        SingleData localInstance = instance;
        if (localInstance == null) {
            synchronized (SingleData.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SingleData();
                }
            }
        }
        return localInstance;
    }
}