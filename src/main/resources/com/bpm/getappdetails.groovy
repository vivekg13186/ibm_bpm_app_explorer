package com.bpm
//out response
//in username,password,url



import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import kong.unirest.HttpResponse
import kong.unirest.Unirest
response=""
Unirest.config().verifySsl(false)

def json(str){
    def jsonSluper = new JsonSlurper();
    if(str==null){
        return jsonSluper.parseText("{}")
    }
    return  jsonSluper.parseText(str)
}

def listProcessApp(){
    HttpResponse<String> response = Unirest.get(url+"/rest/bpm/wle/v1/processApps")
    .basicAuth(username,password)
    .asString();
    return json(response.body)
}

def listAppSnapshots(snapshotId){
    HttpResponse<String> response = Unirest.get(url+"/rest/bpm/wle/v1/processAppSettings")
    .queryString("snapshotId",snapshotId)
            .basicAuth(username,password)
            .asString();
    return json(response.body)
}

def result = listProcessApp();
def processAppList = result?.data?.processAppsList
if(processAppList!=null){
    for(app in processAppList){
        for(ss in app.installedSnapshots){
            if(ss.active){
                def temp = listAppSnapshots(ss.ID)
                ss.projDeps = temp?.data?.projDeps
            }
        }
    }
}

response = new JsonBuilder(result.data.processAppsList).toPrettyString()
