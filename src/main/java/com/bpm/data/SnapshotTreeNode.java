package com.bpm.data;

import javax.swing.tree.DefaultMutableTreeNode;

public class SnapshotTreeNode extends DefaultMutableTreeNode {
    private Snapshot snapshot;

    public SnapshotTreeNode(Snapshot snapshot){
        this.snapshot=snapshot;
        if (snapshot.getProjDeps()!=null) {
            for(Toolkit tk : snapshot.getProjDeps()){
                add(new ToolkitTreeNode(tk));
            }
        }
    }
    public String toString(){
        if(snapshot.isActive()){
            return String.format("%s(Active)",snapshot.getName());
        }
        return String.format("%s",snapshot.getName());
    }
}
