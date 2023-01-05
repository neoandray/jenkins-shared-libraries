def call (String  environment){

                   def rawVmData = null
                   if (environment.toString().toLowerCase() == "test"){
                       echo "connecting to ${vCenterServer7}"
                        rawVmData=  pwsh( script:"""connect-viserver -server ${vCenterServer7} -user ${vCenterCred7_USR} -password ${vCenterCred7_PSW}; get-vm| select *,@{N="IP_Address";E={@(\$_.Guest.IPAddress)| WHERE -filter{\$_ -notmatch ':' }  }}, @{N="VMX_File";E={\$_.Extensiondata.Summary.Config.VmPathName}}""", encoding: 'UTF-8',returnStdout:true)  
                    }else{
                         echo "connecting to ${vCenterServer6}"
                         rawVmData=  pwsh( script:"""connect-viserver -server ${vCenterServer6} -user ${vCenterCred6_USR} -password ${vCenterCred6_PSW}; get-vm| select *,@{N="IP_Address";E={@(\$_.Guest.IPAddress)| WHERE -filter{\$_ -notmatch ':' }  }}, @{N="VMX_File";E={\$_.Extensiondata.Summary.Config.VmPathName}}""", encoding: 'UTF-8',returnStdout:true)  
                   }
                                   
                  def vmList  =  rawVmData.split('\n\n\n\n');
                  def vms      = []
                  vmList.toList().subList(1, vmList.length-1).each{ 
                    
                    def tempVmInfo = it.toString().split('\n')
                    def line = 1
                    def key = null
                    def value = null
                    def vm=[:]
                    tempVmInfo.each{

                           def data = it.split(':')
                           line+=1
                           key   = data[0]
                           value = data[1]
                           vm[key]=value
                           
                       }
                 
                       vms.add(vm) 
               
                  }
                  def outFileName = "vm_inventory_"+new Date().format("yyyyMMdd_hh_mm_ss")
                  //writeCSV file: outFileName, records: vms, format: CSVFormat.EXCEL
                  //writeCSV file: outFileName, records: vms, format: CSVFormat.DEFAULT
                  writeJSON  file: outFileName, json: vms, pretty:1 //, returnText: true

}
