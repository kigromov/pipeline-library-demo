#!/usr/bin/env groovy
import hudson.tasks.test.AbstractTestResultAction
import hudson.tasks.junit.CaseResult

def call(Map<String, String> params) {
   def testList = params["testList"]

       echo testList
       echo "START 1 JOB"
   def currentBuild
  for (int i = 0; i < 2; i++) {
      stage("start test job ${i}") 
    {
       currentBuild = runTestJob(testList: testList)
    
       echo "END 1 JOB"
       
       def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
       def failedTests = testResultAction.getFailedTests()
       for(CaseResult cr : failedTests) {
           testList = testList + "${cr.getFullDisplayName()},"
        }
       echo testList
       
    }
  }
}

