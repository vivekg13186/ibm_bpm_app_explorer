package com.bpm.data;

import javax.swing.tree.DefaultMutableTreeNode;

public class ToolkitTreeNode extends DefaultMutableTreeNode {
    private Toolkit toolkit;

        public ToolkitTreeNode(Toolkit toolkit){
            this.toolkit=toolkit;
        }
    public String toString(){
        return String.format("%s(%s)",toolkit.getProjectName(),toolkit.getSnapshotName());
    }
}
