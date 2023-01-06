
def call(String environment, String command){
    
        def response = null
        if (environment.toString().toLowerCase() == "test"){

         response =  pwsh( script:"""connect-viserver -server ${vCenterServer7} -user ${vCenterCred7_USR} -password ${vCenterCred7_PSW};  ${command}""", encoding: 'UTF-8',returnStdout:true)  
        
        } else{

                response =  pwsh( script:"""connect-viserver -server ${vCenterServer6} -user ${vCenterCred6_USR} -password ${vCenterCred6_PSW};  ${command}""", encoding: 'UTF-8',returnStdout:true)  
        }
        def processedResponse=  null
        def jsonStart  = response.indexOf('{')
        if (jsonStart){ 
         processedResponse = response.substring(jsonStart) 

        }
        return processedResponse
}