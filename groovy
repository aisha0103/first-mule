//defining common properties like username, password, ordId, envID
 def init ()
  {
    def props = ['username':System.properties.'aisha_naeem', 
           'password': System.properties.'Aishapccw@123',
           'exchangeFileName': System.properties.'hello-world',
           'orgId': System.properties.'e0cda975-e4f3-44a1-83ee-d14b91bd3a7e',
           'envId': System.properties.'73e78bfe11674c0cbbd5054e54e43b80',
           'targetPropFile' : System.properties.'/greeting',
          'userId':System.properties.'73e78bfe11674c0cbbd5054e54e43b80'
          ]
    return props;
  }
  
  //Method for http callouts
static def doRESTHTTPCall(urlString, method, payload, headers)
  {
    log(DEBUG,  "START doRESTHTTPCall")
    log(INFO, "requestURl is " + urlString)
    def url = new URL(urlString)
    def connection = url.openConnection()
    headers.keySet().each {
      log(INFO, it + "-&gt;" + headers.get(it))
      connection.setRequestProperty(it, headers.get(it))
    }
    connection.doOutput = true
    if (method == "POST")
    {
      connection.setRequestMethod("POST")
      def writer = new OutputStreamWriter(connection.outputStream)
      writer.write(payload)
      writer.flush()
      writer.close()
    }
    else if (method == "GET")
    {
      connection.setRequestMethod("GET")
    }
    connection.connect();
    log(DEBUG,  "END doRESTHTTPCall")
    return connection
  }
  
  //method to iterate over a collection
 def collectMaps(e) {
  e.with{
      if (it instanceof Map) {
          [it] + it.values().collect{ collectMaps(it) }
      } else if (it instanceof Collection) {
          it.collect{ collectMaps(it) }
      } else {
          []
      }
  }.flatten()
}
