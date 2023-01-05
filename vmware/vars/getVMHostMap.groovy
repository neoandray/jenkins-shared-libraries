def call(String  vmName, int vmCount, ArrayList physicalHosts){
      
      def  vmHostMapParameters = []
      def  vmNameTemplate      = "Server"

      for (def i=0; i< vmCount; i++){
                    def indexNatural = i+1
                    def vmIndex= indexNatural
                    def vmSpecsMap = [:]
                    def vmID = "${vmNameTemplate}${indexNatural}"
                    def serverName = indexNatural <9 ?"${vmName}00${indexNatural}":"${vmName}0${indexNatural}"

        vmHostMapParameters.add(
          choice(name:"${vmID}", choices:phyHosts  ,description:"Please choose a physical host for ${serverName}")

        )
      }

     return  input(id:'vmHostMapInput', parameters:  vmHostParameters, description: 'Please choose the physical hosts for your servers')


}