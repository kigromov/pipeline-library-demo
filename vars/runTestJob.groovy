#!/usr/bin/env groovy
def call(Map<String, String> params) {
   echo "runTestJob started" 
   def testList = params["testList"]
   echo testList
   def cBuild = build job: 'AllureTest/allure', propagate: false, parameters: [string(name: 'testList', value: testList)]
   return cBuild         
}
