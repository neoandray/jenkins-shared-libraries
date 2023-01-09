def call (def deviceType, def parameterName){
    def devIndex = params[parameterName];
      
    def  optionsMap = null;
    if (deviceType=="drives"){
       optionsMap = hostDatastoreMap;
    }else if(deviceType=="nics"){
       optionsMap = hostNetworkMap;
    }
    return optionsMap[devIndex]
}