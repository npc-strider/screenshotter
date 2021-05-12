package io.github.npc_strider.screenshotter.client;

public class ResizeHandler {
    private Change change;
    public ResizeHandler() {}

    public void queueChange(int width, int height) {
        this.change = new Change(width, height);
    }

    public Change getChange() {
        Change change = this.change;
        this.change = null;
        return change;
    }

    public class Change {
        public int width = 0;
        public int height = 0;
        public Change(int width, int height) {
            this.height = height;
            this.width = width;
        }
    }
}
