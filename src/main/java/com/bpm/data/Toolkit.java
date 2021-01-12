package com.bpm.data;

public class Toolkit {
    private String projectId;
    private String projectName;
    private String snapshotId;
    private String snapshotName;

    public Toolkit() {
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public String getSnapshotName() {
        return snapshotName;
    }

    public void setSnapshotName(String snapshotName) {
        this.snapshotName = snapshotName;
    }

    public boolean contains(String search){
        if(projectName.toLowerCase().contains(search)||snapshotName.toLowerCase().contains(search)){
            return true;
        }
        return false;
    }

    public Toolkit copy(){
        Toolkit result = new Toolkit();
        result.setProjectId(projectId);
        result.setProjectName(projectName);
        result.setSnapshotId(snapshotId);
        result.setSnapshotName(snapshotName);
        return result;
    }
}
