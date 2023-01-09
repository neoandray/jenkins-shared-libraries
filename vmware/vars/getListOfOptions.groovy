def call (def deviceType, def devIndex){
    def  optionsMap = null;
    if (deviceType=="drives"){
       optionsMap = hostDatastoreMap;
    }else if(deviceType=="nics"){
       optionsMap = hostNetworkMap;
    }
    return optionsMap[devIndex]
}