package com.example.journote.event;

public class StartUpdateJournoteEvent {
    private int position;

    public StartUpdateJournoteEvent(int position) {
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
