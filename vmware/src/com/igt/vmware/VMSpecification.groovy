package com.igt.vmware
import groovy.json.*


class  VMSpecification{
        
        enum IPMODE{
            STATIC
            ,DYNAMIC
        }
        String name;
        int nmCpu =1;
        int memoryGb =1;
        String networkName ='VM Network';  
        Boolean hasCD =true;
        Boolean hasFloppy =false;
        String cluster= "";
        String osDistribution = "Linux";
        String vmHost=""
        ArrayList<String> diskSummary=[]
        String ipMode = IPMODE.DYNAMIC;
        String ip =""
        String mask=""
        String gateway=""
        ArrayList<String> dns=[]
  
        public VMSpecification(){


        }
        public VMSpecification(Map specificationMap){
        
        this.name 		      =   specificationMap["name"];
        this.nmCpu 		      =   specificationMap["nmCpu"]?:this.nmCpu;             
        this.memoryGb	      =   specificationMap["memoryGb"] ?:this.memoryGb ;        
        this.networkName  	  =   specificationMap["networkName"]?: this.networkName;        
        this.hasCD            =   specificationMap["hasCD"]?: this.hasCD;             
        this.hasFloppy        =   specificationMap["hasFloppy"]?: this.hasFloppy;       
        this.cluster          =   specificationMap["cluster"]?: this.cluster; 
        this.osDistribution   =   specificationMap["osDistribution"]?: "Linux";
        this.vmHost           =   specificationMap["vmHost"]?: this.vmHost;
        this.diskSummary      =   specificationMap["diskSummary"]?: this.diskSummary;
        this.ipMode           =   specificationMap["ipMode"]?:this.ipMode;
        this.ip               =   specificationMap["ip"]?:this.ip;
        this.mask             =   specificationMap["mask"]?:this.mask;
        this.gateway          =   specificationMap["gateway"]?:this.gateway;
        this.dns              =   specificationMap["dns"]?:this.dns;

        }

        public JsonBuilder  toJSON(){
            def jsonMap =[:]
            this.properties.each{
                if (it.key!="class"){ 
                    jsonMap[it.key]=it.value
                }
            }
            return new JsonBuilder(jsonMap);//.toPrettyString()
        }

  /*      public static void main (String [] args){

             println(new VMSpecification().toJSON());
        }
        */
}