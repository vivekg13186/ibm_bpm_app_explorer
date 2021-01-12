package com.bpm;

import com.bpm.data.ProcessApp;
import com.bpm.data.ProcessAppTreeNode;
import com.bpm.ui.ExplorerUI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BPMService {

    private List<ProcessApp> appList;
    public BPMService(){
        appList = new ArrayList<>();
    }

    public String resourceToString(String filename){
        return new Scanner(BPMService.class.getResourceAsStream(filename), "UTF-8").useDelimiter("\\A").next();
    }
    public void loadApps(final ExplorerUI ui){
        ui.enableAction(false);
        ui.setProgress(50);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String host =ui.getURL();
                String username=ui.getUserName();
                String password=ui.getPassword();
                if(isEmpty(host)||isEmpty(username)||isEmpty(password)){
                    return;
                }

                try {
                    if(host.endsWith("/")){
                        host=host.substring(0,host.length()-1);
                    }

                    //appList = loadFromFile();
                    appList = loadFromServer(host,username,password);
                    populateTreeModel(ui.getTree(),appList);
                } catch (IOException e) {
                    e.printStackTrace();
                    ui.showError(e.getMessage());
                }finally {
                    ui.enableAction(true);
                    ui.setProgress(0);
                }
            }
        });
    }
    public boolean isEmpty(String str){
        return  str == null||str.isEmpty();
    }
    public void searchForApps(final ExplorerUI ui){
        ui.enableAction(false);
        ui.setProgress(50);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    String search = ui.getSearchTxt();
                    if(search!=null||!search.isEmpty()){
                        search = search.toLowerCase();
                        List<ProcessApp> filtered = new ArrayList<>();
                        for(ProcessApp app:appList){
                            if(app.contain(search)){
                                filtered.add(app.copy());
                            }
                        }
                        populateTreeModel(ui.getTree(),filtered);
                    }else{
                        populateTreeModel(ui.getTree(),appList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ui.showError(e.getMessage());
                }finally {
                    ui.enableAction(true);
                    ui.setProgress(0);
                }
            }
        });
    }




    private void populateTreeModel(JTree tree,List<ProcessApp> processApps){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("BPM Applications");
        for(ProcessApp processApp:processApps){
            rootNode.add(new ProcessAppTreeNode(processApp));
        }
        TreeModel treeModel = new DefaultTreeModel(rootNode);
        tree.setModel(treeModel);
    }
    private List<ProcessApp> loadFromFile() throws IOException {
        Gson gson = new Gson();
       // URL url = Resources.getResource("sample.json");
        String text  = resourceToString("sample.json");//Resources.toString(url, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<ProcessApp>>(){}.getType();
        return gson.fromJson(text,listType);
    }

    private List<ProcessApp> loadFromServer(String host ,String username,String password) throws IOException {
        Gson gson = new Gson();
        //URL url = Resources.getResource("getappdetails.groovy");
        String text  =resourceToString("getappdetails.groovy"); //Resources.toString(url, StandardCharsets.UTF_8);
        String result ="";
        Binding binding = new Binding();
        binding.setProperty("response",result);
        binding.setProperty("url",host);
        binding.setProperty("username",username);
        binding.setProperty("password",password);
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate(text);
        String response = (String)binding.getProperty("response");
        Type listType = new TypeToken<ArrayList<ProcessApp>>(){}.getType();
        return gson.fromJson(response,listType);
    }
}
