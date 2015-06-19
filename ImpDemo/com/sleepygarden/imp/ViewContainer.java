package com.sleepygarden.imp;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ViewContainer {

    private class IndexedViewWrapper {
        public View view;
        public int z;

        public IndexedViewWrapper(View view, int z){
            this.view = view;
            this.z = z;
        }
    }

    private PriorityQueue<IndexedViewWrapper> viewsHeap;

    public ViewContainer(){
        this.viewsHeap = new PriorityQueue<>(10, new Comparator<IndexedViewWrapper>() {
            public int compare(IndexedViewWrapper a, IndexedViewWrapper b) {
                return Integer.compare(a.z, b.z);
            }
        });
    }

    public void insert(View view, int z) {
        this.viewsHeap.add(new IndexedViewWrapper(view, z));
    }

    public View[] views(){
        PriorityQueue<IndexedViewWrapper> viewsHeapCopy = new PriorityQueue<>(this.viewsHeap);
        View[] views = new View[viewsHeapCopy.size()];
        for (int i = 0; i < views.length; i++){
            views[i] = viewsHeapCopy.poll().view;
        }
        return views;
    }

    public void draw(ImpLayer imp){
        for (View v : views()){
            v.draw(imp);
        }
    }
}
