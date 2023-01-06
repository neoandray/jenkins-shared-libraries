
def call (String server, String username, String password){
         String command   ="""\$vmInfo = @{};
\$vmInfo["templates"] = get-template| SELECT NAME;
\$vmInfo["vms"] = get-vm| SELECT NAME;
\$vmInfo["hosts"]= get-vmhost| select NAME, @{n='CPUCores';e={\$_.Numcpu}}, @{n='FreeMemory'; e={\$_.MemoryTotalGB - \$_.MemoryUsageGB}},@{n='FreeCPU'; e={\$_.CpuTotalMhz - \$_.CpuUsageMhz}} |Sort-Object -Property FreeMemory,FreeCPU  -Descending; 
\$vmInfo["hostDatastoreMap"] =get-vmhost|foreach{\$vmhost= \$_;\$vmhost|get-datastore | select  @{n='HostName';e={\$vmhost.Name}}, @{n='DataStoreName';e={\$_.Name}}, CapacityGB, FreeSpaceGB |Sort-Object -property FreeSpaceGB -Descending }; 
\$vmInfo["hostNetworkMap"] =get-vmhost|foreach{\$vmhost= \$_;\$vmhost|get-virtualportGroup | select  @{n='HostName';e={\$vmhost.Name}}, @{n='NetworkName';e={\$_.Name}} } ; 
\$vmInfo |convertto-json"""
          
        def  rawTemplateString = ""

        def response = null
        response =  pwsh( script:"""connect-viserver -server ${server} -user ${username} -password ${password};  ${command}""", encoding: 'UTF-8',returnStdout:true)  
        def  userStringReplacement = ""
        if ("vsphere.local" in username.toLowerCase()){
             userStringReplacement ="VSPHERE.LOCAL\\"+username.split('@')[0]
          }else{
            userStringReplacement = username
          }
          def processedResponse= response.replace("""Name                           Port  User
          ----                           ----  ----
          ${server}       443   ${userStringReplacement}""",'').trim()
          rawTemplateString =  processedResponse
          println(rawTemplateString)
          def vmInfo = readJSON text : rawTemplateString
          return vmInfo
}




