package com.bpm.data;

import java.util.ArrayList;
import java.util.List;

public class ProcessApp {

    private String shortName ;
    private String name ;
    private String description ;
    private String richDescription ;
    private String lastModifiedBy ;
    private String defaultVersion ;
    private String defaultBranchID ;
    private List<Snapshot> installedSnapshots ;
    private String ID ;
    private String lastModified_on ;

    public ProcessApp() {
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRichDescription() {
        return richDescription;
    }

    public void setRichDescription(String richDescription) {
        this.richDescription = richDescription;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    public String getDefaultBranchID() {
        return defaultBranchID;
    }

    public void setDefaultBranchID(String defaultBranchID) {
        this.defaultBranchID = defaultBranchID;
    }

    public List<Snapshot> getInstalledSnapshots() {
        return installedSnapshots;
    }

    public void setInstalledSnapshots(List<Snapshot> installedSnapshots) {
        this.installedSnapshots = installedSnapshots;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLastModified_on() {
        return lastModified_on;
    }

    public void setLastModified_on(String lastModified_on) {
        this.lastModified_on = lastModified_on;
    }

    public boolean contain(String search){
        if(name.toLowerCase().contains(search)||shortName.toLowerCase().contains(search)){
            return true;
        }else{
            if(installedSnapshots!=null){
                for(Snapshot s:installedSnapshots){
                    if(s.contains(search)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ProcessApp copy(){
        ProcessApp result = new ProcessApp();
        result.setShortName(shortName) ;
        result.setName(name) ;
        result.setDescription(description) ;
        result.setRichDescription(richDescription) ;
        result.setLastModifiedBy(lastModifiedBy);
        result.setDefaultVersion(defaultVersion) ;
        result.setDefaultBranchID(defaultBranchID) ;
        result.setID(ID) ;
        result.setLastModified_on(lastModified_on) ;
        if(installedSnapshots!=null){
            List<Snapshot> snapshots = new ArrayList<>();
            for(Snapshot s:installedSnapshots){
                snapshots.add(s.copy());
            }
            result.setInstalledSnapshots(snapshots);
        }
        return result;
    }
}
