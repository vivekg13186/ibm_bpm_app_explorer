package com.bpm.data;

import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private String name;
    private String acronym;
    private boolean  active;
    private String activeSince;
    private String createdOn;
    private boolean snapshotTip;
    private String branchID;
    private String branchName;
    private String ID;
    private List<Toolkit> projDeps;

    public Snapshot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(String activeSince) {
        this.activeSince = activeSince;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isSnapshotTip() {
        return snapshotTip;
    }

    public void setSnapshotTip(boolean snapshotTip) {
        this.snapshotTip = snapshotTip;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Toolkit> getProjDeps() {
        return projDeps;
    }

    public void setProjDeps(List<Toolkit> projDeps) {
        this.projDeps = projDeps;
    }

    public boolean contains(String search){
        if(name.toLowerCase().contains(search)){
            return true;
        }else{
            if(projDeps!=null){
                for(Toolkit tk :projDeps){
                    if(tk.contains(search)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Snapshot copy(){
        Snapshot result =new Snapshot();
        result.setName(name);
        result.setAcronym(acronym);
        result.setActive(active);
        result.setActiveSince(activeSince);
        result.setCreatedOn(createdOn);
        result.setSnapshotTip(snapshotTip);
        result.setBranchID(branchID);
        result.setBranchName(branchName);
        result.setID(ID);
        if(projDeps!=null){
            List<Toolkit> toolkits = new ArrayList<>();
            for(Toolkit tk : projDeps){
                toolkits.add(tk.copy());
            }
            result.setProjDeps(toolkits);
        }
        return result;
    }
}
