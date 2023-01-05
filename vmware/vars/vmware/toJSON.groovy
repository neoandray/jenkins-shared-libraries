    import groovy.json.*
    def call (Map jsonMap){
            return   JsonOutput.prettyPrint(JsonOutput.toJson(jsonMap));
     }