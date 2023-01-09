def call (def pipeline, def deviceType, def parameterName){
    def devIndex = params[parameterName];
      
    def  optionsMap = null;
    if (deviceType=="drives"){
       optionsMap = pipeline.hostDatastoreMap;
    }else if(deviceType=="nics"){
       optionsMap = pipeline.hostNetworkMap;
    }
    return optionsMap["172.22.38.37"]
}