def call (def deviceType, def parameterName){
    def devIndex = params[parameterName];
      
    def  optionsMap = null;
    if (deviceType=="drives"){
       optionsMap = this.hostDatastoreMap;
    }else if(deviceType=="nics"){
       optionsMap = this.hostNetworkMap;
    }
    return optionsMap[devIndex]
}