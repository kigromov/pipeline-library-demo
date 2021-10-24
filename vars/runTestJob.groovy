#!/usr/bin/env groovy
def call(Map<String, String> params) {
   echo "runTestJob started" 
   def testList = params["testList"] ?: "*Test"
   echo testList
   stage("start test job") 
    {
       def cBuild = build job: 'AllureTest/allure', propagate: false, wait: true, parameters: [string(name: 'testList', value: testList)]
       return cBuild
    }
            
}
