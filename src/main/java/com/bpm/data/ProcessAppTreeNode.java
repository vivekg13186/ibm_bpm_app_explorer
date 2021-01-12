package com.bpm.data;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProcessAppTreeNode extends DefaultMutableTreeNode {
    private ProcessApp processApp;

    public ProcessAppTreeNode(ProcessApp processApp){
        this.processApp = processApp;
        if(processApp.getInstalledSnapshots()!=null){
            for(Snapshot s:processApp.getInstalledSnapshots()){
                add(new SnapshotTreeNode(s));
            }
        }
    }

    public String toString(){
        return  String.format("%s (%s) (Default : %s)",processApp.getName(),processApp.getShortName(),processApp.getDefaultVersion());
    }
}
