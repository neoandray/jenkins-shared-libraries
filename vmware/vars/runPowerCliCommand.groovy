
def call(String environment, String command){
    
        def response = null
        if (environment.toString().toLowerCase() == "test"){
        
            response =  pwsh( script:"""connect-viserver -server ${vCenterServer7} -user ${vCenterCred7_USR} -password ${vCenterCred7_PSW};  ${command}""", encoding: 'UTF-8',returnStdout:true)  
        } else{
         
            response =  pwsh( script:"""connect-viserver -server ${vCenterServer6} -user ${vCenterCred6_USR} -password ${vCenterCred6_PSW};  ${command}""", encoding: 'UTF-8',returnStdout:true)  
        }
        
        def processedResponse= response.replace("""Name                           Port  User
----                           ----  ----
172.22.38.56                   443   VSPHERE.LOCAL\\gsp_devops""",'').trim()
        processedResponse= processedResponse.replace("""Name                           Port  User
----                           ----  ----
172.22.38.50                   443   VSPHERE.LOCALCM\\gsp_devops""",'').trim()
        return processedResponse
}