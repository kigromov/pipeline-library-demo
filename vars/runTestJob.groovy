#!/usr/bin/env groovy

def call(Map<String, String> params) {
   def testList = params["testList"]
   def cBuild = build job: 'AllureTest/allure', propagate: false, parameters: [string(name: 'testList', value: testList)]
   return cBuild         
}
